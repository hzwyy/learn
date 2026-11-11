package com.example.demo.designpatterns.structural;

/**
 * ============================================================
 * 结构型模式 02 —— 装饰器模式（Decorator）
 * ============================================================
 * 【一句话定义】
 *   在不改变原类、不使用继承的前提下，动态地给对象增加新功能。
 *
 * 【经典场景】
 *   - Java IO 流：new BufferedReader(new FileReader(f)) 一层层包
 *   - 奶茶加料：奶茶 + 珍珠 + 椰果，每加一层价格叠加
 *   - Spring 的事务、日志增强本质也是装饰思想
 *
 * 【核心思想】
 *   继承是"编译期固定"的扩展，类多了会爆炸
 *   （奶茶 + 3 种配料用继承要写 7 个类：纯奶茶、+珍珠、+椰果、+双加...）
 *   装饰器是"运行时任意组合"，一个装饰类搞定所有组合。
 *
 * 【四个角色】
 *   抽象组件（Component）     —— 奶茶接口
 *   具体组件（ConcreteComponent）—— 纯奶茶
 *   抽象装饰器（Decorator）   —— 实现/继承组件，并持有一个组件引用
 *   具体装饰器（ConcreteDecorator）—— 珍珠、椰果
 *
 * 【与代理模式的区别】
 *   装饰器：目的是"增强功能"，客户端主动组合（我来加珍珠）
 *   代理：目的是"控制访问"，客户端无感知（代理说了算）
 */
public class DecoratorPattern {

    // ==========================================================
    // 抽象组件：所有奶茶都要能算钱、报名字
    // ==========================================================
    interface MilkTea {
        String desc();

        double price();
    }

    // ==========================================================
    // 具体组件：一杯纯奶茶，最原始的样子
    // ==========================================================
    static class PureMilkTea implements MilkTea {
        @Override
        public String desc() {
            return "纯奶茶";
        }

        @Override
        public double price() {
            return 8.0;
        }
    }

    // ==========================================================
    // 抽象装饰器：关键设计！
    // 1. 自己也是 MilkTea（实现接口，可以冒充奶茶）
    // 2. 内部持有一个 MilkTea（把功能"转发+增强"给它）
    // ==========================================================
    static abstract class Topping implements MilkTea {
        protected final MilkTea inner; // 被装饰的对象（里面那层奶茶）

        public Topping(MilkTea inner) {
            this.inner = inner;
        }
    }

    // ==========================================================
    // 具体装饰器 1：珍珠 —— 在原有基础上"加料加价"
    // ==========================================================
    static class Pearl extends Topping {
        public Pearl(MilkTea inner) {
            super(inner);
        }

        @Override
        public String desc() {
            return inner.desc() + "+珍珠"; // 先调里层的描述，再追加
        }

        @Override
        public double price() {
            return inner.price() + 3.0; // 先算里层的钱，再加价
        }
    }

    // ==========================================================
    // 具体装饰器 2：椰果 —— 结构和珍珠完全一样
    // ==========================================================
    static class Coconut extends Topping {
        public Coconut(MilkTea inner) {
            super(inner);
        }

        @Override
        public String desc() {
            return inner.desc() + "+椰果";
        }

        @Override
        public double price() {
            return inner.price() + 2.5;
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 装饰器模式演示 =====");

        // 一层装饰：纯奶茶 + 珍珠
        MilkTea tea1 = new Pearl(new PureMilkTea());
        System.out.println(tea1.desc() + " = " + tea1.price() + " 元");

        // 多层嵌套装饰：和 IO 流的写法一模一样！
        // new BufferedReader(new FileReader(file)) 就是这样层层包的
        MilkTea tea2 = new Coconut(new Pearl(new Pearl(new PureMilkTea())));
        System.out.println(tea2.desc() + " = " + tea2.price() + " 元");

        // 关键优势：不用写"双珍珠奶茶"类，运行时想怎么组合就怎么组合
        // 如果用继承：2 种配料就有 4 种组合，3 种配料 8 种组合 —— 类爆炸！
    }
}
