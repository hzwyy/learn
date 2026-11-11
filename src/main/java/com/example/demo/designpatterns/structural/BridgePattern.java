package com.example.demo.designpatterns.structural;

/**
 * ============================================================
 * 结构型模式 05 —— 桥接模式（Bridge）
 * ============================================================
 * 【一句话定义】
 *   把"抽象部分"和"实现部分"分离到两个独立的继承体系中，
 *   中间用组合（桥）连接，让它们可以各自独立变化。
 *
 * 【解决什么痛点？类爆炸问题！】
 *   假设手机有 2 种品牌（小米/苹果），功能有 3 种（打电话/拍照/游戏）。
 *   用普通继承：需要 2 × 3 = 6 个类；
 *   加一个品牌变 9 个类，加一个功能变 12 个类 —— 组合爆炸！
 *   桥接模式：2 + 3 = 5 个类，相加而不是相乘！
 *
 * 【经典场景】
 *   - JDBC：DriverManager（抽象）连接各种 Driver（实现），
 *     这就是桥接，你换数据库驱动不用改上层代码
 *   - 消息系统：消息类型（普通/加急）× 发送渠道（邮件/短信/微信）
 *   - 图形：形状（圆/方）× 颜色（红/蓝），各自独立扩展
 *
 * 【核心思想】
 *   "多维度变化时，用组合代替继承"。
 *   抽象类里持有实现接口的引用 —— 这条引用就是"桥"。
 *
 * 【记忆口诀】
 *   两个变化维度 → 各建一个继承体系 → 用组合连接 → 2 个 m + n 个类
 */
public class BridgePattern {

    // ==========================================================
    // 维度一：实现部分 —— 消息发送渠道（可以独立增加：钉钉、飞书...）
    // ==========================================================
    interface Channel {
        void send(String message);
    }

    static class EmailChannel implements Channel {
        @Override
        public void send(String message) {
            System.out.println("  [邮件渠道] 发送: " + message);
        }
    }

    static class SmsChannel implements Channel {
        @Override
        public void send(String message) {
            System.out.println("  [短信渠道] 发送: " + message);
        }
    }

    // ==========================================================
    // 维度二：抽象部分 —— 消息类型（可以独立增加：定时消息...）
    // 关键点：抽象类持有 Channel 的引用，这就是"桥"！
    // ==========================================================
    static abstract class Message {
        protected final Channel channel; // 桥：抽象引用实现

        public Message(Channel channel) {
            this.channel = channel;
        }

        abstract void send(String message);
    }

    // 普通消息：直接发
    static class NormalMessage extends Message {
        public NormalMessage(Channel channel) {
            super(channel);
        }

        @Override
        void send(String message) {
            channel.send(message); // 把发送动作"委托"给实现部分
        }
    }

    // 加急消息：先加标记再发（在自己维度上做了增强，不影响渠道维度）
    static class UrgentMessage extends Message {
        public UrgentMessage(Channel channel) {
            super(channel);
        }

        @Override
        void send(String message) {
            channel.send("【加急】" + message);
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 桥接模式演示 =====");

        // 两个维度自由组合，2 种消息 × 2 种渠道 = 4 种效果
        // 但只写了 4 个类（而不是继承方式的 4 + 4）
        Message msg1 = new NormalMessage(new EmailChannel());
        msg1.send("明天上午 10 点开会");

        Message msg2 = new NormalMessage(new SmsChannel());
        msg2.send("服务器 CPU 过高");

        Message msg3 = new UrgentMessage(new EmailChannel());
        msg3.send("线上订单服务宕机");

        Message msg4 = new UrgentMessage(new SmsChannel());
        msg4.send("数据库主从切换");
    }
}
