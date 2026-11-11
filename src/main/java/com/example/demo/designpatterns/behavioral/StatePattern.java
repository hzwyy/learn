package com.example.demo.designpatterns.behavioral;

/**
 * ============================================================
 * 行为型模式 06 —— 状态模式（State）
 * ============================================================
 * 【一句话定义】
 *   对象的行为随内部状态改变而改变，像"换了个类"一样。
 *   把每种状态封装成独立的类，状态切换时替换掉当前状态对象。
 *
 * 【经典场景】
 *   - 订单状态：待支付 → 已支付 → 已发货 → 已收货，每个状态可做的事不同
 *   - 电梯：开门/关门/上升/下降，规则复杂且互相牵制
 *   - 线程状态机：Thread 的 NEW/RUNNABLE/BLOCKED...
 *   - 游戏角色：正常/中毒/无敌 状态下行为不同
 *
 * 【解决什么痛点？消灭"状态 + 行为"的双重 if-else！】
 *   坏味道：
 *     if(state == 待支付) { 能取消, 不能收货 }
 *     else if(state == 已发货) { 不能支付, 能收货 }
 *     ...
 *   状态多、动作多时，矩阵爆炸且极易漏改。
 *   状态模式：每个状态一个类，行为内聚在自己身上，加状态=加类。
 *
 * 【与策略模式的区别（结构像，意图不同）】
 *   策略：客户端主动选择算法，策略之间互不知道
 *   状态：状态自己知道下一步切到哪个状态，自动流转
 *
 * 【角色】
 *   环境类（Context）—— 持有当前状态，把请求转发给状态对象
 *   抽象状态（State）—— 定义该状态下所有可能的行为
 *   具体状态 —— 实现行为 + 决定状态怎么流转
 */
public class StatePattern {

    // ==========================================================
    // 环境类：订单。自己不写 if-else，行为全部委托给状态对象
    // ==========================================================
    static class Order {
        private OrderState state; // 当前状态对象

        public Order() {
            // 初始状态：待支付
            this.state = new UnpaidState();
            System.out.println("  [新订单] 当前状态: 待支付");
        }

        // 供状态类回调：切换到下一个状态（状态流转的关键通道）
        public void setState(OrderState state, String stateName) {
            this.state = state;
            System.out.println("  [订单] 状态流转为: " + stateName);
        }

        // 三个动作全部转发给当前状态对象处理 —— 多态分派，告别 if-else
        public void pay() {
            state.pay(this);
        }

        public void ship() {
            state.ship(this);
        }

        public void receive() {
            state.receive(this);
        }
    }

    // ==========================================================
    // 抽象状态：定义订单在任意状态下可能收到的所有动作
    // 默认实现 = "这个状态下做这个动作是非法的"
    // ==========================================================
    abstract static class OrderState {
        protected Order order; // 回头能拿到订单（用于切换状态）

        void pay(Order order) {
            System.out.println("  [×] 当前状态不支持支付");
        }

        void ship(Order order) {
            System.out.println("  [×] 当前状态不支持发货");
        }

        void receive(Order order) {
            System.out.println("  [×] 当前状态不支持收货");
        }
    }

    // ==========================================================
    // 具体状态 1：待支付
    // ==========================================================
    static class UnpaidState extends OrderState {
        @Override
        void pay(Order order) {
            System.out.println("  [✓] 支付成功！");
            // 核心：支付这个动作导致状态流转 —— 状态自己决定下一步是谁
            order.setState(new PaidState(), "已支付");
        }
    }

    // ==========================================================
    // 具体状态 2：已支付
    // ==========================================================
    static class PaidState extends OrderState {
        @Override
        void ship(Order order) {
            System.out.println("  [✓] 商品已发出！");
            order.setState(new ShippedState(), "已发货");
        }
    }

    // ==========================================================
    // 具体状态 3：已发货（终态）
    // ==========================================================
    static class ShippedState extends OrderState {
        @Override
        void receive(Order order) {
            System.out.println("  [✓] 交易完成，欢迎评价！");
            order.setState(new FinishedState(), "已完成");
        }
    }

    // ==========================================================
    // 具体状态 4：已完成（终态，所有动作都拒绝）
    // ==========================================================
    static class FinishedState extends OrderState {
        // 不覆盖任何方法：全部走父类的默认"拒绝"实现
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 状态模式演示 =====");

        Order order = new Order();

        System.out.println("--- 尝试非法操作：直接发货 ---");
        order.ship(); // 待支付状态不能发货

        System.out.println("--- 正常流程：支付 → 发货 → 收货 ---");
        order.pay();
        order.ship();
        order.receive();

        System.out.println("--- 终态后再试支付 ---");
        order.pay(); // 已完成，拒绝
    }
}
