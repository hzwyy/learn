package com.example.demo.designpatterns.behavioral;

/**
 * ============================================================
 * 行为型模式 03 —— 模板方法模式（Template Method）
 * ============================================================
 * 【一句话定义】
 *   父类定义算法的"骨架"（步骤和顺序），子类实现具体步骤，
 *   整体流程由父类锁死，细节由子类填充。
 *
 * 【经典场景】
 *   - JdbcTemplate / RedisTemplate：Spring 全家桶的底层套路
 *   - Servlet 的 service() → doGet/doPost
 *   - 钩子方法（hook）：父类留一个开关让子类控制流程走向
 *
 * 【解决什么痛点？】
 *   多个类的流程"90% 相同，10% 不同"。
 *   相同部分提到父类（复用），不同部分做成抽象方法（扩展）。
 *   避免每个类都复制粘贴一遍流程。
 *
 * 【三个要点】
 *   1. 模板方法用 final 修饰：流程不许子类乱改
 *   2. 具体公共步骤：父类直接实现（所有人一样的部分）
 *   3. 抽象步骤：子类必须实现（每个人不一样的部分）
 *   加分项：钩子方法 —— 默认实现返回 true/false，子类可覆盖来
 *           控制模板中的某个步骤是否执行。
 *
 * 【与策略模式的区别】
 *   模板方法：用继承，父类定骨架，"编译期"决定行为
 *   策略模式：用组合，整体算法可替换，"运行时"切换行为
 */
public class TemplateMethodPattern {

    // ==========================================================
    // 抽象父类：定义"泡一杯饮品"的固定流程
    // ==========================================================
    abstract static class BeverageMaker {

        /**
         * 模板方法：锁死整个流程的步骤和顺序。
         * final 修饰 —— 子类只能填空，不能改流程！这是模板方法的灵魂。
         */
        public final void makeBeverage() {
            boilWater();        // 步骤 1：所有人一样，父类直接实现
            brew();             // 步骤 2：不一样的部分，交给子类
            pourInCup();        // 步骤 3：所有人一样
            if (wantCondiments()) {  // 步骤 4：钩子控制是否执行
                addCondiments();
            }
        }

        // 公共步骤（具体方法）：烧水，谁来做都一样
        private void boilWater() {
            System.out.println("  步骤1: 把水烧开");
        }

        // 差异步骤（抽象方法）：泡什么？子类决定
        protected abstract void brew();

        // 公共步骤：倒入杯中
        private void pourInCup() {
            System.out.println("  步骤3: 倒进杯子");
        }

        // 差异步骤（抽象方法）：加什么调料？子类决定
        protected abstract void addCondiments();

        /**
         * 钩子方法（Hook）：默认返回 true。
         * 子类可以选择覆盖它来控制"加调料"这一步是否执行。
         * 这给了子类在"不改流程"前提下的额外自由度。
         */
        protected boolean wantCondiments() {
            return true;
        }
    }

    // ==========================================================
    // 具体子类 1：咖啡
    // ==========================================================
    static class CoffeeMaker extends BeverageMaker {
        @Override
        protected void brew() {
            System.out.println("  步骤2: 用开水冲泡咖啡粉");
        }

        @Override
        protected void addCondiments() {
            System.out.println("  步骤4: 加糖和牛奶");
        }
    }

    // ==========================================================
    // 具体子类 2：茶（顺便演示钩子：喝茶不加糖）
    // ==========================================================
    static class TeaMaker extends BeverageMaker {
        @Override
        protected void brew() {
            System.out.println("  步骤2: 浸泡茶叶 5 分钟");
        }

        @Override
        protected void addCondiments() {
            System.out.println("  步骤4: 加柠檬片");
        }

        // 覆盖钩子：喝茶不加调料，流程中的第 4 步被跳过
        @Override
        protected boolean wantCondiments() {
            return false;
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 模板方法模式演示 =====");

        System.out.println("--- 泡咖啡 ---");
        new CoffeeMaker().makeBeverage();

        System.out.println("\n--- 泡茶（钩子生效：跳过调料步骤）---");
        new TeaMaker().makeBeverage();
    }
}
