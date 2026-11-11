package com.example.demo.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 行为型模式 04 —— 责任链模式（Chain of Responsibility）
 * ============================================================
 * 【一句话定义】
 *   多个处理者串成一条链，请求沿着链传递，
 *   每个处理者决定"处理它"还是"传给下一个"。
 *
 * 【经典场景】
 *   - Web 过滤器链：Filter → Filter → Servlet
 *   - Spring Interceptor 拦截器链
 *   - 请假审批：组长(1天) → 经理(3天) → 总监(7天) → 老板(7天以上)
 *   - Netty 的 Pipeline、MyBatis 的插件
 *
 * 【解决什么痛点？】
 *   审批逻辑写成 if-else 时，所有规则堆在一个方法里。
 *   责任链把每个规则拆成独立节点，链的顺序、增删节点都灵活可调。
 *
 * 【两种链式写法】
 *   1. 每个节点持有 next 引用（本案例）
 *   2. 节点都放进 List，由统一调度器遍历（Filter 链的做法）
 *
 * 【注意】
 *   请求不一定被处理 —— 链尾没人管就丢弃了；
 *   也可能多个节点都处理（过滤器链就是全部都过的）。
 */
public class ChainOfResponsibilityPattern {

    // ==========================================================
    // 抽象处理者：请假审批人
    // ==========================================================
    abstract static class Approver {
        protected final String name;
        protected Approver next; // 链上的下一个节点

        public Approver(String name) {
            this.name = name;
        }

        // 组装链：设置下一个处理者，返回 next 支持链式编程
        public Approver setNext(Approver next) {
            this.next = next;
            return next;
        }

        // 核心抽象方法：子类实现各自的审批逻辑
        public abstract void handle(int days);
    }

    // ==========================================================
    // 具体处理者 1：组长 —— 只能批 1 天以内
    // ==========================================================
    static class TeamLeader extends Approver {
        public TeamLeader(String name) {
            super(name);
        }

        @Override
        public void handle(int days) {
            if (days <= 1) {
                System.out.println("  [组长" + name + "] 批准 " + days + " 天请假");
            } else if (next != null) {
                // 超出我的权限，传给链上的下一个人
                System.out.println("  [组长" + name + "] 超过我的权限，上报...");
                next.handle(days);
            }
        }
    }

    // ==========================================================
    // 具体处理者 2：经理 —— 只能批 3 天以内
    // ==========================================================
    static class Manager extends Approver {
        public Manager(String name) {
            super(name);
        }

        @Override
        public void handle(int days) {
            if (days <= 3) {
                System.out.println("  [经理" + name + "] 批准 " + days + " 天请假");
            } else if (next != null) {
                System.out.println("  [经理" + name + "] 超过我的权限，上报...");
                next.handle(days);
            }
        }
    }

    // ==========================================================
    // 具体处理者 3：总监 —— 兜底，啥都能批
    // ==========================================================
    static class Director extends Approver {
        public Director(String name) {
            super(name);
        }

        @Override
        public void handle(int days) {
            System.out.println("  [总监" + name + "] 批准 " + days + " 天请假（总监兜底）");
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 责任链模式演示 =====");

        // 1. 创建三个处理者
        Approver leader = new TeamLeader("老王");
        Approver manager = new Manager("老李");
        Approver director = new Director("老张");

        // 2. 组装链条：组长 → 经理 → 总监
        leader.setNext(manager);
        manager.setNext(director);
        // 链式写法等价于: leader.setNext(manager).setNext(director);

        // 3. 客户端只需要把请求扔给链头，后面的事不用管
        System.out.println("--- 请假 1 天 ---");
        leader.handle(1);

        System.out.println("--- 请假 3 天 ---");
        leader.handle(3);

        System.out.println("--- 请假 10 天 ---");
        leader.handle(10);
    }
}
