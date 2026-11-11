package com.example.demo.designpatterns;

/**
 * ============================================================
 * 设计模式总目录（运行我，查看全部 23 个模式的中文名索引）
 * ============================================================
 * 【这个类是干嘛的】
 *   相当于整本"设计模式教材"的目录页：
 *   - 按 创建型 / 结构型 / 行为型 三大类列出全部 23 个模式
 *   - 每个模式标注中文名、经典场景、一句话记忆点
 *   - 学习时可以对着这张表，看完一个划掉一个
 *
 * 【学习方法建议】
 *   1. 先运行本类，浏览目录建立全局印象
 *   2. 按顺序打开对应 Pattern 文件，读注释 + 看 main 演示
 *   3. 重点模式（单例/工厂/代理/策略/观察者/模板方法）建议手写一遍
 *
 * 【三大类怎么区分（面试口诀）】
 *   创建型：管"对象怎么生出来"（5 个）
 *   结构型：管"类和对象怎么组装"（7 个）
 *   行为型：管"对象之间怎么交互"（11 个）
 */
public class DesignPatternsIndex {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║          GoF 设计模式 23 种 · 中文总目录          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        System.out.println("\n【一、创建型模式】—— 管对象怎么生出来（5 个）");
        menu("SingletonPattern",         "单例模式",   "全局唯一实例",           "保证一个类只有一个实例，如数据库连接池");
        menu("SimpleFactoryPattern",     "简单工厂",   "一个工厂造所有产品",     "★不属于 GoF 23 种★ 但实战超常用，Integer.valueOf 就是它");
        menu("FactoryMethodPattern",     "工厂方法",   "子类决定生产什么",       "把 new 延迟到子类，加产品不改老代码");
        menu("AbstractFactoryPattern",   "抽象工厂",   "生产一族配套产品",       "宜家：一次买齐成套同风格家具");
        menu("BuilderPattern",           "建造者模式", "分步装配复杂对象",       "链式 .a().b().build()，参数多时神器");
        menu("PrototypePattern",         "原型模式",   "克隆代替 new",           "深浅拷贝是核心考点");

        System.out.println("\n【二、结构型模式】—— 管类和对象怎么组装（7 个）");
        menu("AdapterPattern",           "适配器模式", "转换不兼容接口",         "转换插头：老系统对接新系统");
        menu("DecoratorPattern",         "装饰器模式", "不改类动态加功能",       "奶茶加料，Java IO 流层层套");
        menu("ProxyPattern",             "代理模式",   "控制访问的替身",         "明星经纪人，Spring AOP 的根基");
        menu("FacadePattern",            "外观模式",   "复杂系统的简化入口",     "电脑开机键，一个按钮搞定一切");
        menu("BridgePattern",            "桥接模式",   "两个维度独立变化",       "用组合代替继承，防类爆炸");
        menu("CompositePattern",         "组合模式",   "树形结构统一处理",       "文件夹里可以有文件和文件夹");
        menu("FlyweightPattern",         "享元模式",   "共享细粒度对象",         "Integer 缓存、String 常量池");

        System.out.println("\n【三、行为型模式】—— 管对象之间怎么交互（11 个）");
        menu("StrategyPattern",          "策略模式",   "算法可替换",             "消灭 if-else 利器，促销/支付场景");
        menu("ObserverPattern",          "观察者模式", "一对多自动通知",         "公众号推送，事件监听的底层");
        menu("TemplateMethodPattern",    "模板方法",   "父类定骨架子类填空",     "流程锁死细节开放，JdbcTemplate");
        menu("ChainOfResponsibilityPattern", "责任链模式", "请求沿链传递",       "请假审批：组长→经理→总监");
        menu("CommandPattern",           "命令模式",   "请求封装成对象",         "可排队可撤销，Ctrl+Z 的原理之一");
        menu("StatePattern",             "状态模式",   "行为随状态改变",         "订单状态机，双重 if-else 灭火器");
        menu("IteratorPattern",          "迭代器模式", "统一遍历方式",           "for-each 的底层真身");
        menu("MediatorPattern",          "中介者模式", "网状依赖变星型",         "聊天室/塔台，谁也不认识谁");
        menu("MementoPattern",           "备忘录模式", "保存快照随时恢复",       "游戏存档、编辑器撤销");
        menu("InterpreterPattern",       "解释器模式", "定义文法并解释执行",     "正则/SQL/SpEL 的底层思想");
        menu("VisitorPattern",           "访问者模式", "不改元素加新操作",       "双分派，编译器 AST 常客");
        System.out.println("\n【四、附录】—— 不在 GoF 23 种，但实战必会");
        System.out.println("  ◆ 简单工厂（不是设计模式，是编程惯用法）");
        System.out.println("      为什么不算：违反开闭原则，加产品必须改工厂里的 switch");
        System.out.println("      为什么必学：JDK 里到处都是 —— Integer.valueOf / Calendar.getInstance");
        System.out.println("                    / Executors.newFixedThreadPool 全是简单工厂");
        System.out.println("      和工厂方法的关系：简单工厂用 if/switch 造产品，工厂方法用多态造产品");
        System.out.println("      代码: designpatterns/creational/  SimpleFactoryPattern.java");
    }

    /** 打印一行目录条目 */
    private static void menu(String file, String cnName, String keyword, String memo) {
        System.out.printf("  ◆ %-14s（%s）—— %s%n", cnName, keyword, memo);
        System.out.printf("      代码: designpatterns/.../  %s.java%n", file);
    }
}
