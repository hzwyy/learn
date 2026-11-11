package com.example.demo.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 行为型模式 08 —— 中介者模式（Mediator）
 * ============================================================
 * 【一句话定义】
 *   用一个中介对象封装一组对象的交互，
 *   让对象之间不再直接引用，改网状依赖为星型依赖。
 *
 * 【经典场景】
 *   - 聊天室：所有人只连服务器，消息由服务器转发，
 *     用户之间互相不认识（不是网状加好友）
 *   - 机场塔台：所有飞机只和塔台通信，不互相协调
 *   - MVC：Controller 就是 View 和 Model 的中介者
 *   - 房产中介：买家卖家不直接谈判
 *
 * 【解决什么痛点？网状 → 星型】
 *   n 个对象互相通信 = n×(n-1)/2 条连线（6 人 = 15 条！）。
 *   加一个对象要改 n 个类。
 *   中介者模式：所有对象只认识中介者，连线降到 n 条。
 *   加对象只改中介者（或注册即可）。
 *
 * 【与外观模式的区别】
 *   外观：单向简化，子系统不知道门面存在，子系统之间不交互
 *   中介者：双向协调，同事对象认识中介者，彼此之间原本要交互
 *
 * 【注意】
 *   中介者可能变成"上帝类"，啥逻辑都堆它身上 —— 职责要控制。
 */
public class MediatorPattern {

    // ==========================================================
    // 中介者：聊天室服务器
    // 核心职责：注册用户 + 转发消息
    // ==========================================================
    static class ChatRoom {
        private final List<User> users = new ArrayList<>();

        // 用户加入聊天室（向中介者注册）
        public void register(User user) {
            users.add(user);
            user.setChatRoom(this); // 告诉用户：你的中介是我
            System.out.println("  [聊天室] " + user.getName() + " 加入了聊天室");
        }

        /**
         * 核心：消息转发逻辑全在这里。
         * 发送者不需要认识其他任何人，只管把消息丢给中介者。
         */
        public void relay(User sender, String message) {
            System.out.println("  [聊天室] 收到 " + sender.getName() + " 的消息，开始转发...");
            for (User u : users) {
                // 不转发给发送者自己
                if (u != sender) {
                    u.receive(sender.getName(), message);
                }
            }
        }
    }

    // ==========================================================
    // 同事类：用户
    // 关键特征：只持有中介者的引用，不持有其他 User 的引用！
    // ==========================================================
    static class User {
        private final String name;
        private ChatRoom chatRoom; // 只认识中介者

        public User(String name) {
            this.name = name;
        }

        void setChatRoom(ChatRoom chatRoom) {
            this.chatRoom = chatRoom;
        }

        public String getName() {
            return name;
        }

        // 发消息：发给中介者，而不是直接发给某个人
        public void send(String message) {
            System.out.println("  [" + name + "] 我要发言: " + message);
            chatRoom.relay(this, message);
        }

        // 收消息：由中介者调用
        public void receive(String from, String message) {
            System.out.println("  [" + name + "] 收到来自 " + from + " 的消息: " + message);
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 中介者模式演示 =====");

        ChatRoom room = new ChatRoom();
        User a = new User("小明");
        User b = new User("小红");
        User c = new User("小刚");

        room.register(a);
        room.register(b);
        room.register(c);

        System.out.println();
        // 小明只调 send，不知道有谁在群里 —— 全靠中介者转发
        a.send("大家晚上一起打球吗？");
    }
}
