package com.example.demo.designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * 结构型模式 07 —— 享元模式（Flyweight）
 * ============================================================
 * 【一句话定义】
 *   共享大量细粒度的相同对象，避免重复创建，节省内存。
 *   "享元" = 共享的单元（内部状态共享，外部状态分离）。
 *
 * 【经典场景】
 *   - Integer.valueOf(127) 会缓存（-128~127 之间的值共享！面试考点）
 *   - String 常量池
 *   - 围棋/五子棋：棋子只有黑白两种，共享"颜色"，位置作为外部状态传入
 *   - 文字编辑器：每个字只是位置不同，字形对象可以共享
 *
 * 【核心概念（面试重点）】
 *   内部状态（ intrinsic ）：不变的、可共享的 —— 棋子的颜色
 *   外部状态（ extrinsic ）：变化的、不可共享的 —— 棋子的位置
 *   享元工厂：用一个 Map 缓存已创建的享元对象，用的时候先查缓存。
 *
 * 【本质】
 *   对象池 + 工厂 + 缓存。你平时写的"单例缓存 Map"其实就是享元思想。
 *
 * 【注意】
 *   - 享元对象必须不可变，否则共享会被污染
 *   - 只有当对象数量大、且大量重复时才值得用
 */
public class FlyweightPattern {

    // ==========================================================
    // 享元对象：棋子
    // 内部状态 = 颜色（固定不变，可以被所有棋盘位置共享）
    // 注意：不保存位置！位置是外部状态，由调用方传入
    // ==========================================================
    static class GoStone {
        private final String color; // final 保证不可变，共享才安全

        public GoStone(String color) {
            this.color = color;
            // 构造器的打印用于观察：每种颜色只创建一次！
            System.out.println("  [工厂] 真正创建了新棋子: " + color);
        }

        /**
         * 内部状态共享，外部状态（位置）作为参数传入
         * 同一个对象可以同时"扮演"棋盘上任意位置的黑棋
         */
        public void place(int x, int y) {
            System.out.println("  在 (" + x + "," + y + ") 落了一颗" + color + "子"
                    + "（对象@" + Integer.toHexString(hashCode()) + "）");
        }
    }

    // ==========================================================
    // 享元工厂：核心！用 Map 做缓存池
    // ==========================================================
    static class StoneFactory {
        // 缓存池：key = 内部状态（颜色），value = 享元对象
        private static final Map<String, GoStone> POOL = new HashMap<>();

        public static GoStone getStone(String color) {
            // 先查缓存，没有才创建 —— 这就是享元的精髓
            // 等价于 Integer.valueOf() 的实现思路
            return POOL.computeIfAbsent(color, GoStone::new);
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 享元模式演示 =====");

        // 下棋：一盘棋可能落几百颗子，但对象只创建 2 个（黑、白）
        GoStone black1 = StoneFactory.getStone("黑色");
        black1.place(4, 4);
        GoStone white1 = StoneFactory.getStone("白色");
        white1.place(16, 4);
        GoStone black2 = StoneFactory.getStone("黑色"); // 不会再创建，命中缓存！
        black2.place(4, 16);

        // 验证共享：两个"黑棋"是同一个 Java 对象
        System.out.println("黑棋1 和 黑棋2 是同一个对象吗? " + (black1 == black2));

        System.out.println("\n>>> 一整盘棋几百手，内存里只存在 2 个棋子对象。");

        // 顺便验证 JDK 中真实的享元：Integer 缓存
        Integer a = 127, b = 127;
        System.out.println("Integer(127)==Integer(127) ? " + (a == b) + "（命中缓存池，true）");
        Integer c = 128, d = 128;
        System.out.println("Integer(128)==Integer(128) ? " + (c == d) + "（超出缓存范围，false！）");
    }
}
