package com.example.demo.designpatterns.creational;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 创建型模式 05 —— 原型模式（Prototype）
 * ============================================================
 * 【一句话定义】
 *   通过"克隆"一个已有对象来创建新对象，而不是重新 new。
 *
 * 【经典场景】
 *   - 创建对象成本很高（要查数据库、读大文件、复杂计算）
 *   - 需要大量相似对象，只有个别字段不同
 *   - 框架中保存对象的历史快照（如撤销功能）
 *   - Spring 的 prototype 作用域 Bean
 *
 * 【核心技术：Cloneable 接口 + Object.clone()】
 *   Java 的 clone() 是浅拷贝：只复制基本类型和引用（地址），
 *   不复制引用指向的对象。所以有了"浅拷贝 vs 深拷贝"问题。
 *
 * 【浅拷贝 vs 深拷贝（本案例的核心考点）】
 *   浅拷贝：克隆人和原人共用同一个"大脑"（引用同一块内存）
 *           —— 改一个，另一个也跟着变！
 *   深拷贝：克隆人拥有自己独立的大脑
 *           —— 互不影响，这才是大多数业务想要的。
 *
 * 【深拷贝的两种实现】
 *   1. 手动递归克隆：把每个引用类型字段也 clone 一遍
 *   2. 序列化法：对象写成字节流再读回来（最省事，但性能低）
 *
 * 【优缺点】
 *   + 绕过构造过程的昂贵开销，性能好
 *   + 隐藏创建细节，客户端不关心对象内部结构
 *   - 每个类都要实现克隆逻辑，深拷贝写起来容易出错
 */
public class PrototypePattern {

    // ==========================================================
    // 辅助类：大脑。用于演示浅拷贝和深拷贝的区别
    // ==========================================================
    static class Brain implements Serializable {
        String region; // 负责的区域，比如"语言区"

        public Brain(String region) {
            this.region = region;
        }
    }

    // ==========================================================
    // 原型类：实现 Cloneable 表示"我可以被克隆"
    // ==========================================================
    static class Person implements Cloneable, Serializable {
        String name;
        int age;
        List<String> hobbies; // 引用类型字段，浅拷贝的"坑"就在这
        Brain brain;          // 深层引用对象

        public Person(String name, int age, List<String> hobbies, Brain brain) {
            System.out.println("  [Person 构造器被调用] —— 原型模式克隆时不会调用构造器！");
            this.name = name;
            this.age = age;
            this.hobbies = hobbies;
            this.brain = brain;
        }

        /**
         * 浅拷贝：直接调用 super.clone()
         * 特点：基本类型（age）会复制值，但 hobbies 和 brain 只复制引用
         */
        @Override
        public Person clone() {
            try {
                return (Person) super.clone(); // JVM 的 native 方法，很快
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * 深拷贝方式一：手动递归克隆
         * 把每个引用字段也复制一份新对象
         */
        public Person deepCloneByManual() {
            Person copy = this.clone();
            // 手动复制 hobbies 列表，让新对象有自己独立的列表
            copy.hobbies = new ArrayList<>(this.hobbies);
            // 手动复制 brain 对象
            copy.brain = new Brain(this.brain.region);
            return copy;
        }

        /**
         * 深拷贝方式二：序列化 / 反序列化
         * 把整个对象图写进字节流再读出来，所有引用对象都会被完整复制
         * 注意：要求所有涉及的类都实现 Serializable
         */
        public Person deepCloneBySerialize() {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(this); // 写出：整个对象图变成字节

                ObjectInputStream ois = new ObjectInputStream(
                        new ByteArrayInputStream(bos.toByteArray()));
                return (Person) ois.readObject(); // 读回：完整的新对象
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age
                    + ", hobbies=" + hobbies
                    + ", brain=" + Integer.toHexString(brain.hashCode()) + "}";
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 原型模式演示 =====");

        // 1. 创建一个昂贵的原型对象（假设构造过程要查库、读文件，很耗时）
        // Arrays.asList 兼容 JDK8（List.of 是 JDK9+ 才有的）
        Person prototype = new Person("模板人", 25,
                new ArrayList<>(java.util.Arrays.asList("读书", "跑步")), new Brain("语言区"));

        // 2. 浅拷贝演示：暴露问题
        Person shallow = prototype.clone();
        shallow.hobbies.add("游泳");         // 修改克隆体的爱好
        shallow.brain.region = "运动区";     // 修改克隆体的大脑

        System.out.println("\n--- 浅拷贝（有坑）---");
        System.out.println("原型: " + prototype);
        System.out.println("克隆体: " + shallow);
        System.out.println(">>> 注意：原型的爱好也多了'游泳'，大脑也变成了运动区！因为共用同一个对象");

        // 3. 深拷贝演示：解决问题
        Person deep = prototype.deepCloneByManual();
        deep.hobbies.add("游戏");
        deep.brain.region = "数学区";

        System.out.println("\n--- 深拷贝（手动递归）---");
        System.out.println("原型: " + prototype);
        System.out.println("克隆体: " + deep);
        System.out.println(">>> 原型不受影响，因为克隆体拥有自己独立的引用对象");

        // 4. 序列化深拷贝
        Person deep2 = prototype.deepCloneBySerialize();
        System.out.println("\n--- 深拷贝（序列化法）---");
        System.out.println("克隆体: " + deep2);
        System.out.println(">>> 效果一样，适合引用层级深的场景");
    }
}
