package com.example.demo.designpatterns.behavioral;

import java.util.List;

/**
 * ============================================================
 * 行为型模式 07 —— 迭代器模式（Iterator）
 * ============================================================
 * 【一句话定义】
 *   提供一种方法顺序访问容器中的元素，而不暴露容器的内部结构。
 *
 * 【经典场景】
 *   - JDK 的 Iterator / Iterable：List、Set 都能 for-each，
 *     就是因为它们实现了 Iterable
 *   - 你自研一个自定义容器，想让它也支持 for-each
 *
 * 【解决什么痛点？】
 *   容器千差万别：数组用下标、链表用 next 指针、二叉树要遍历。
 *   如果客户端要针对每种容器写一套遍历逻辑，耦合太深。
 *   迭代器统一了"怎么走"，客户端只管"要走"。
 *
 * 【两个角色】
 *   Iterable（可迭代容器）—— 提供 iterator() 返回迭代器
 *   Iterator（迭代器）     —— hasNext() / next()
 *
 * 【和 for-each 的关系】
 *   for(Student s : classRoom) 编译后就是：
 *     Iterator it = classRoom.iterator(); while(it.hasNext()){ s = it.next(); }
 *   所以只要实现 Iterable，你的类就能用 for-each！本案例就演示这个。
 */
public class IteratorPattern {

    // ==========================================================
    // 元素类：学生
    // ==========================================================
    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }
    }

    // ==========================================================
    // 自定义容器：班级（底层用数组存，但外部不用关心！）
    // 实现 Iterable<Student>，就能直接用 for-each 遍历
    // ==========================================================
    static class ClassRoom implements Iterable<Student> {
        // 内部用数组 —— 这是"不暴露"的内部结构，随便换成链表都不影响客户端
        private final Student[] students = new Student[10];
        private int count = 0; // 当前人数

        public void add(Student s) {
            students[count++] = s;
        }

        public int size() {
            return count;
        }

        /**
         * 迭代器模式的核心方法：返回一个专门的迭代器对象
         */
        @Override
        public java.util.Iterator<Student> iterator() {
            return new ClassRoomIterator();
        }

        // ======================================================
        // 具体迭代器（内部类）：知道怎么走这个数组
        // 内部类的好处：直接访问外部类的私有字段 students
        // ======================================================
        private class ClassRoomIterator implements java.util.Iterator<Student> {
            private int cursor = 0; // 当前游标位置

            @Override
            public boolean hasNext() {
                return cursor < count; // 还有元素没遍历完吗
            }

            @Override
            public Student next() {
                // 先取当前元素，再后移游标
                return students[cursor++];
            }
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 迭代器模式演示 =====");

        ClassRoom classRoom = new ClassRoom();
        classRoom.add(new Student("小明"));
        classRoom.add(new Student("小红"));
        classRoom.add(new Student("小刚"));

        // 客户端完全不知道底层是数组，反正能遍历 —— 结构被隐藏了
        System.out.println("--- for-each 遍历（因为实现了 Iterable）---");
        for (Student s : classRoom) {
            System.out.println("  学生: " + s.name);
        }

        // 也可以用显式迭代器写法（for-each 的底层真身）
        System.out.println("--- 显式 Iterator 遍历 ---");
        java.util.Iterator<Student> it = classRoom.iterator();
        while (it.hasNext()) {
            System.out.println("  学生: " + it.next().name);
        }

        System.out.println(">>> 班级人数（不用暴露内部数组也能查询）: " + classRoom.size());
    }
}
