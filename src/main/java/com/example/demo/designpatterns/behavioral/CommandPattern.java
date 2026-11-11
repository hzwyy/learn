package com.example.demo.designpatterns.behavioral;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================
 * 行为型模式 05 —— 命令模式（Command）
 * ============================================================
 * 【一句话定义】
 *   把"一个请求"封装成对象，从而可以排队、记录日志、撤销重做。
 *
 * 【经典场景】
 *   - 撤销 / 重做：编辑器 Ctrl+Z、数据库事务回滚
 *   - 任务队列：线程池里的 Runnable 就是命令对象！
 *   - 遥控器 / 菜单按钮：按钮不关心背后执行什么
 *   - 游戏回放：把所有操作命令存起来按顺序重放
 *
 * 【四个角色】
 *   命令接口（Command）—— 统一的 execute() / undo()
 *   具体命令（ConcreteCommand）—— 绑定接收者，定义"做什么"
 *   接收者（Receiver）—— 真正干活的
 *   调用者（Invoker）—— 持有命令，负责触发（它不认识接收者！）
 *
 * 【解耦链条】
 *   调用者 → 命令对象 → 接收者
 *   调用者根本不知道命令背后是谁在干活，只知道"执行命令"。
 *
 * 【价值】
 *   请求被"物化"成对象后，就能像普通对象一样：
 *   存进队列、写进日志、序列化传输 —— 这是一切的基础。
 */
public class CommandPattern {

    // ==========================================================
    // 接收者：厨师，真正做菜的人
    // ==========================================================
    static class Chef {
        public void cook(String dish) {
            System.out.println("  [厨师] 正在烹制: " + dish);
        }

        public void cancel(String dish) {
            System.out.println("  [厨师] 取消了: " + dish);
        }
    }

    // ==========================================================
    // 命令接口：execute 执行，undo 撤销
    // ==========================================================
    interface Command {
        void execute();

        void undo();
    }

    // ==========================================================
    // 具体命令：一份"订单"，绑定了要做的菜和执行者
    // ==========================================================
    static class OrderCommand implements Command {
        private final Chef chef;       // 命令知道找谁执行
        private final String dish;     // 命令携带的参数

        public OrderCommand(Chef chef, String dish) {
            this.chef = chef;
            this.dish = dish;
        }

        @Override
        public void execute() {
            chef.cook(dish); // 命令只是"转达"，真正干活的是厨师
        }

        @Override
        public void undo() {
            chef.cancel(dish); // 撤销能力就是命令模式的杀手锏
        }
    }

    // ==========================================================
    // 调用者：服务员
    // 关键：服务员不认识厨师！只认识"命令" —— 彻底解耦
    // ==========================================================
    static class Waiter {
        // 存放待执行命令的队列 —— 请求物化成对象后就可以排队了
        private final Deque<Command> orders = new ArrayDeque<>();
        // 存放已执行命令，用于撤销
        private final Deque<Command> history = new ArrayDeque<>();

        // 接单：只管把命令放进队列
        public void takeOrder(Command command) {
            orders.addLast(command);
            System.out.println("  [服务员] 已记录订单");
        }

        // 把所有命令依次执行 —— 支持批量、延后执行
        public void submitOrders() {
            System.out.println("  [服务员] 把订单递给后厨：");
            while (!orders.isEmpty()) {
                Command cmd = orders.pollFirst();
                cmd.execute();
                history.push(cmd); // 记入历史，供撤销
            }
        }

        // 撤销最近一道菜 —— 因为命令是对象，可以"反着执行"
        public void undoLast() {
            if (!history.isEmpty()) {
                System.out.println("  [服务员] 顾客改主意了，撤销最后一单：");
                history.pop().undo();
            }
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 命令模式演示 =====");

        Chef chef = new Chef();       // 接收者
        Waiter waiter = new Waiter(); // 调用者

        // 顾客点菜：生成命令对象（订单）
        waiter.takeOrder(new OrderCommand(chef, "宫保鸡丁"));
        waiter.takeOrder(new OrderCommand(chef, "鱼香肉丝"));
        waiter.takeOrder(new OrderCommand(chef, "酸辣土豆丝"));

        // 服务员统一提交 —— 和"线程池提交 Runnable"是同一个思想
        waiter.submitOrders();

        // 顾客说最后那道不要了 —— 撤销
        waiter.undoLast();
    }
}
