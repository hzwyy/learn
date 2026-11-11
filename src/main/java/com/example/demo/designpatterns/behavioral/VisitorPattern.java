package com.example.demo.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 行为型模式 11 —— 访问者模式（Visitor）
 * ============================================================
 * 【一句话定义】
 *   把作用于某对象结构中各元素的操作封装成"访问者"，
 *   元素不改变自身，就能接受新操作。
 *
 * 【经典场景】
 *   - 编译器 AST：同一棵语法树，可以做类型检查、代码生成、格式化
 *   - 财务系统：同一批员工数据，财务算工资、HR 算考勤
 *   - 文件树操作：统计大小 / 查毒 / 打包，都是"访问"同一棵树
 *
 * 【解决什么痛点？】
 *   一个稳定的对象结构（元素类型很少变）+ 频繁新增操作。
 *   不用访问者：每种操作要写进每个元素类里（一堆耦合）；
 *   用访问者：新操作 = 新写一个访问者类，元素类一行不改！
 *
 * 【双分派（面试难点）】
 *   Java 的方法调用只按"运行时类型"分派一次（多态）。
 *   访问者模式通过两次调用实现"双分派"：
 *     ① element.accept(visitor)  —— 按元素的实际类型分派
 *     ② visitor.visit(this)      —— 在对应元素类里调用，
 *        按访问者的重载精确匹配到 visit(Book)/visit(Fruit)
 *   结果：方法的行为同时由"元素类型 × 访问者类型"决定。
 *
 * 【代价】
 *   元素结构一旦要增删类型，所有访问者都得改 —— 适合结构稳定的场景。
 */
public class VisitorPattern {

    // ==========================================================
    // 抽象元素 + 访问者接口（互相依赖，这是访问者模式的标志）
    // ==========================================================
    interface Item {
        // accept 就是一扇门："访问者请进来操作我"
        void accept(Visitor visitor);
    }

    // 访问者接口：每种元素类型对应一个重载方法
    interface Visitor {
        void visit(Book book);
        void visit(Fruit fruit);
    }

    // ==========================================================
    // 具体元素 1：图书
    // ==========================================================
    static class Book implements Item {
        double price;
        String name;

        public Book(String name, double price) {
            this.name = name;
            this.price = price;
        }

        /**
         * 双分派第一步：把"自己"交给访问者。
         * 因为 this 的类型是 Book，所以精确调用 visit(Book)。
         */
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    // ==========================================================
    // 具体元素 2：水果（有自己的特有字段）
    // ==========================================================
    static class Fruit implements Item {
        double weightKg;
        String name;

        public Fruit(String name, double weightKg) {
            this.name = name;
            this.weightKg = weightKg;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this); // this 是 Fruit，精确调用 visit(Fruit)
        }
    }

    // ==========================================================
    // 具体访问者 1：结算员 —— 算这车商品总共多少钱
    // ==========================================================
    static class CheckoutVisitor implements Visitor {
        private double total = 0;

        @Override
        public void visit(Book book) {
            total += book.price; // 图书按定价
            System.out.println("  [结算] 图书《" + book.name + "》" + book.price + " 元");
        }

        @Override
        public void visit(Fruit fruit) {
            double cost = fruit.weightKg * 10; // 水果按 10 元/公斤
            total += cost;
            System.out.println("  [结算] 水果" + fruit.name + " " + fruit.weightKg + "kg = " + cost + " 元");
        }

        double getTotal() {
            return total;
        }
    }

    // ==========================================================
    // 具体访问者 2：盘点员 —— 同一批数据，完全不同的另一种操作
    // 新增操作不需要动 Book/Fruit 任何代码！
    // ==========================================================
    static class InventoryVisitor implements Visitor {
        private int count = 0;

        @Override
        public void visit(Book book) {
            count++;
            System.out.println("  [盘点] 图书《" + book.name + "》计入库存");
        }

        @Override
        public void visit(Fruit fruit) {
            count++;
            System.out.println("  [盘点] 水果" + fruit.name + "计入库存（生鲜）");
        }

        int getCount() {
            return count;
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 访问者模式演示 =====");

        // 一个稳定的对象结构：购物车
        List<Item> cart = new ArrayList<>();
        cart.add(new Book("Java 编程思想", 108.0));
        cart.add(new Fruit("苹果", 2.5));
        cart.add(new Book("算法导论", 128.0));

        // 操作 1：结算 —— 一个访问者遍历访问所有元素
        CheckoutVisitor checkout = new CheckoutVisitor();
        for (Item item : cart) {
            item.accept(checkout); // 双分派第一步
        }
        System.out.println("总计: " + checkout.getTotal() + " 元\n");

        // 操作 2：盘点 —— 换一个访问者，元素代码零改动
        InventoryVisitor inventory = new InventoryVisitor();
        for (Item item : cart) {
            item.accept(inventory);
        }
        System.out.println("商品件数: " + inventory.getCount() + " 件");
    }
}
