package com.example.demo.designpatterns.creational;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Supplier;

/**
 * ============================================================
 * 附录（非 GoF 23 种）—— 简单工厂模式（Simple Factory）
 * ============================================================
 * 【先明确：为什么它不在 23 种里？】
 *   简单工厂【不是】GoF 收录的 23 种模式之一，业界更准确的叫法是
 *   "静态工厂方法（Static Factory Method）"，属于编程惯用法（idiom）。
 *   被排除的理由：
 *   1. 违反开闭原则：加新产品往往要改工厂里的判断逻辑。
 *   2. 工厂类职责过重：产品一多就成巨型 switch，单点故障。
 *   3. GoF 认为它没解决"创建逻辑可扩展"的本质问题。
 *
 * 【★本文件的核心：简单工厂有三种变体，别只认 switch ★】
 *   很多人以为"没有 switch 就不是简单工厂"—— 这是误解。
 *   判别简单工厂的标准是这三条：
 *     ① 有一个专门的"工厂"角色（不是产品自己造自己）
 *     ② 客户端不直接 new，只告诉工厂"我要什么"
 *     ③ 由工厂决定实例化哪个具体类
 *
 *   变体 1：经典 switch 判断版   —— 教科书版，考试最爱考
 *   变体 2：多方法静态工厂版     —— JDK 最常见的形态！没有 switch
 *   变体 3：登记式（Map 注册表）  —— 最灵活，无 switch，可动态注册
 *
 *   三者本质相同：都是"集中创建 + 客户端不 new"，
 *   区别只是"选择具体产品"这一步发生在哪里。
 */
public class SimpleFactoryPattern {

    // ==========================================================
    // 抽象产品：所有运算的共同接口
    // ==========================================================
    interface Operation {
        double calculate(double a, double b);
    }

    static class AddOperation implements Operation {
        @Override
        public double calculate(double a, double b) {
            return a + b;
        }
    }

    static class SubOperation implements Operation {
        @Override
        public double calculate(double a, double b) {
            return a - b;
        }
    }

    // ============================================================
    // 变体 1：经典 switch 判断版（教科书 / 软考 / 面试简答题标准答案）
    // ============================================================
    static class OperationFactoryV1 {
        public static Operation create(String operator) {
            // 这个 switch 就是"违反开闭原则"的伤疤：
            // 想加乘法？必须回来改这里 —— 对修改开放了，不满足开闭原则
            switch (operator) {
                case "+":
                    return new AddOperation();
                case "-":
                    return new SubOperation();
                default:
                    throw new IllegalArgumentException("不支持的运算符: " + operator);
            }
        }
    }

    // ============================================================
    // 变体 2：多方法静态工厂版（JDK 里最普遍的形态）
    // ------------------------------------------------------------
    // ★这就是 Executors / NumberFormat / LocalDate 的写法★
    // 没有 switch！因为"选哪个产品"这件事，交给了"调用哪个方法"：
    //   Executors.newFixedThreadPool()     → ThreadPoolExecutor(固定核心数)
    //   Executors.newCachedThreadPool()    → ThreadPoolExecutor(核心0，可扩容)
    //   Executors.newSingleThreadExecutor()→ ThreadPoolExecutor(核心1)
    //
    // 区别在于：
    //   变体 1 是"运行时"根据参数选产品（一个方法，多分支）
    //   变体 2 是"编译期"根据方法名选产品（多个方法，各管一摊）
    //   两者都是"工厂集中创建 + 客户端不 new"，所以都算简单工厂家族，
    //   只是《Effective Java》第 1 条更愿意叫它"静态工厂方法"。
    // ============================================================
    static class OperationFactoryV2 {
        public static Operation plus() {
            return new AddOperation();
        }

        public static Operation minus() {
            return new SubOperation();
        }

        // 加乘法只需在这里加一个方法，不影响 plus/minus ——
        // 比变体 1 好，但客户端仍要"知道调哪个方法名"
        public static Operation times() {
            return new Operation() {
                @Override
                public double calculate(double a, double b) {
                    return a * b;
                }
            };
        }
    }

    // ============================================================
    // 变体 3：登记式（Map 注册表）—— 彻底消灭 switch，还能动态扩展
    // ------------------------------------------------------------
    // JDK 实际应用：
    //   Charset.forName("UTF-8")       —— 按名字从注册表查编码实现
    //   DriverManager.getConnection()  —— 驱动注册表
    // ============================================================
    static class OperationFactoryV3 {
        private static final Map<String, Supplier<Operation>> REGISTRY = new HashMap<>();

        // 静态块里完成注册（真实项目中常改成 @PostConstruct 或 Bean 收集）
        static {
            REGISTRY.put("+", AddOperation::new);   // 方法引用，JDK8+ 写法
            REGISTRY.put("-", SubOperation::new);
        }

        // 对外开放注册入口：新增产品连工厂类都不用改，外部注册即可！
        public static void register(String operator, Supplier<Operation> supplier) {
            REGISTRY.put(operator, supplier);
        }

        public static Operation create(String operator) {
            Supplier<Operation> supplier = REGISTRY.get(operator);
            if (supplier == null) {
                throw new IllegalArgumentException("未注册的运算符: " + operator);
            }
            return supplier.get(); // 真正 new 的地方
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 简单工厂（非 GoF 23 种）三种变体演示 =====");

        // ---------- 变体 1 ----------
        System.out.println("\n--- 变体1：switch 判断版 ---");
        System.out.println("  10 + 3 = " + OperationFactoryV1.create("+").calculate(10, 3));
        System.out.println("  10 - 3 = " + OperationFactoryV1.create("-").calculate(10, 3));

        // ---------- 变体 2 ----------
        System.out.println("\n--- 变体2：多方法静态工厂版（Executors 就是这个形态）---");
        System.out.println("  8 * 5  = " + OperationFactoryV2.times().calculate(8, 5));

        // ---------- 变体 3 ----------
        System.out.println("\n--- 变体3：登记式（Map 注册表）---");
        System.out.println("  6 + 4  = " + OperationFactoryV3.create("+").calculate(6, 4));
        // 运行时动态注册"除法" —— 工厂类一行都不用改
        OperationFactoryV3.register("/", () -> (a, b) -> a / b);
        System.out.println("  9 / 3  = " + OperationFactoryV3.create("/").calculate(9, 3) + "（动态注册成功）");

        // ---------- 三者对比 ----------
        System.out.println("\n  【三种变体对比】");
        System.out.println("  变体1 switch  ：1 个方法 + 参数分支 → 考试标准答案，加产品要改老代码");
        System.out.println("  变体2 多方法  ：N 个静态方法        → JDK 主流，加产品只加方法");
        System.out.println("  变体3 注册表  ：Map + 动态注册      → 最灵活，Spring 的 Bean 工厂思想同源");

        // ============================================================
        // 【JDK 里的真实案例，逐个对上号】
        // ============================================================
        System.out.println("\n  【JDK 真实案例归类】");

        // ① 真正带 switch 的简单工厂：Calendar.createCalendar()
        //    JDK 内部按 Locale 的日历类型 switch 出不同的 Calendar 子类，
        //    泰国地区会返回 BuddhistCalendar（佛历），这是教科书级简单工厂。
        Calendar thai = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"),
                new Locale("th", "TH"));
        System.out.println("  ① Calendar.getInstance(th-TH) → " + thai.getClass().getName());
        System.out.println("     （JDK 内部 switch 判断地区，返回不同日历子类 = 经典简单工厂）");

        // ② 变体 3 登记式的代表：Charset.forName()
        System.out.println("  ② Charset.forName(\"UTF-8\") → "
                + java.nio.charset.Charset.forName("UTF-8").getClass().getName());
        System.out.println("     （按名字从注册表查实现 = 登记式简单工厂）");

        // ③ 变体 2 多方法静态工厂的代表：Executors / Integer.valueOf
        System.out.println("  ③ Executors.newFixedThreadPool(1) → "
                + java.util.concurrent.Executors.newFixedThreadPool(1).getClass().getSimpleName());
        System.out.println("     （没有 switch！靠\"调哪个方法\"决定产品 = 多方法静态工厂）");
        System.out.println("  ④ Integer.valueOf(127) → 命中缓存，返回同一对象（静态工厂 + 享元）");

        // 【补充提醒】Executors 被《阿里巴巴 Java 开发手册》禁用，
        // 原因是它把队列长度、最大线程数、拒绝策略这些关键参数"藏起来"了，
        // 用起来爽，但在高并发下容易 OOM。这正好暴露了简单工厂的通病：
        // 封装过度 → 使用者失去对关键参数的控制。
        // 所以手册要求手写 new ThreadPoolExecutor(核心数, 最大数, 存活时间, 单位, 队列, 工厂, 拒绝策略)。
    }
}
