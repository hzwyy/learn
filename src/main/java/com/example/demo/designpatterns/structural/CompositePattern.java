package com.example.demo.designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 结构型模式 06 —— 组合模式（Composite）
 * ============================================================
 * 【一句话定义】
 *   把对象组成"树形结构"，让客户端用同样的方式处理
 *   单个对象和对象容器（整体与部分一致对待）。
 *
 * 【经典场景】
 *   - 文件系统：文件夹里可以有文件，也可以有文件夹（递归！）
 *   - 公司组织架构：部门下有员工，也有子部门
 *   - Swing/AWT 的容器与组件
 *   - 菜单与子菜单
 *
 * 【核心思想】
 *   "文件"和"文件夹"都实现同一个接口，
 *   客户端调用 folder.show() 时，文件夹自动递归调用所有孩子的 show()。
 *   客户端根本不用判断"这是文件还是文件夹"，写代码超级清爽。
 *
 * 【两个角色】
 *   抽象组件（Component）：文件和文件夹的公共接口
 *   叶子节点（Leaf）：文件 —— 没有孩子
 *   容器节点（Composite）：文件夹 —— 可以装叶子，也可以装容器
 *
 * 【通俗理解】
 *   树形结构的万能钥匙。凡是你看到"整体-部分"、层级嵌套的需求，
 *   基本就是它。
 */
public class CompositePattern {

    // ==========================================================
    // 抽象组件：让"文件"和"文件夹"长着同一张脸
    // ==========================================================
    abstract static class Node {
        protected String name;

        public Node(String name) {
            this.name = name;
        }

        // 统一的展示方法 —— 客户端只认这个方法
        abstract void show(int depth);

        // 打印缩进，让树形结构好看
        // 手动拼接缩进（String.repeat 是 JDK9+ 才有的）
        protected void indent(int depth) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                sb.append("  ");
            }
            System.out.print(sb);
        }
    }

    // ==========================================================
    // 叶子节点：文件（不能有孩子）
    // ==========================================================
    static class FileNode extends Node {
        public FileNode(String name) {
            super(name);
        }

        @Override
        void show(int depth) {
            indent(depth);
            System.out.println("├─ 文件: " + name);
            // 文件是叶子，到此为止，不再往下递归
        }
    }

    // ==========================================================
    // 容器节点：文件夹（核心！持有孩子列表）
    // ==========================================================
    static class FolderNode extends Node {
        // 文件夹里可以装文件、也可以装文件夹 —— 所以列表类型是抽象的 Node
        private final List<Node> children = new ArrayList<>();

        public FolderNode(String name) {
            super(name);
        }

        // 添加孩子（文件或文件夹都行，因为都是 Node）
        public FolderNode add(Node node) {
            children.add(node);
            return this; // 返回 this 支持链式添加
        }

        @Override
        void show(int depth) {
            indent(depth);
            System.out.println("├─ 文件夹: " + name);
            // 关键：递归调用每个孩子的 show，整棵树就被遍历了
            for (Node child : children) {
                child.show(depth + 1); // 深度+1，缩进更深一层
            }
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 组合模式演示 =====");

        // 搭建这棵树：
        // 项目根目录/
        //   ├── src/
        //   │    ├── main/
        //   │    │    └── App.java
        //   │    └── pom.xml
        //   └── README.md

        // 先建最深的文件夹，再一层层往外组装 —— 树的构建套路
        FolderNode main = new FolderNode("main").add(new FileNode("App.java"));
        FolderNode src = new FolderNode("src").add(main).add(new FileNode("pom.xml"));
        FolderNode root = new FolderNode("项目根目录").add(src).add(new FileNode("README.md"));

        // 客户端只调一个 show()，文件、文件夹、无限深度的嵌套全搞定
        // 如果不用组合模式，你得手写"先遍历文件夹，如果是文件夹再遍历..."的判断逻辑
        root.show(0);
    }
}
