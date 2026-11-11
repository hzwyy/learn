package com.example.demo.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 行为型模式 02 —— 观察者模式（Observer / 发布-订阅）
 * ============================================================
 * 【一句话定义】
 *   对象间一对多依赖：被观察者状态变化时，自动通知所有观察者。
 *
 * 【经典场景】
 *   - 微信公众号：你关注了号主（观察者），号主发文（主题变化），你收到推送
 *   - GUI 事件监听：按钮点击 → 通知所有监听器
 *   - Spring 的 ApplicationEvent / 消息队列 MQ（思想的工程化放大版）
 *
 * 【解决什么痛点？】
 *   耦合问题。没有观察者模式时，订单服务要"主动调"短信服务、
 *   积分服务、库存服务 —— 订单服务把所有下游都认识了一遍。
 *   有了观察者：订单只管"发事件"，谁关心谁来订阅，完全解耦。
 *
 * 【两个角色】
 *   主题/被观察者（Subject）—— 维护观察者列表，状态变了就广播
 *   观察者（Observer）—— 实现统一的通知接口，等消息
 *
 * 【推模型 vs 拉模型】
 *   推：主题把数据直接塞给观察者（本案例）
 *   拉：主题只通知"我有变化了"，观察者需要时自己来取
 */
public class ObserverPattern {

    // ==========================================================
    // 观察者接口：所有关注者统一的"收通知"姿势
    // ==========================================================
    interface Observer {
        void update(String articleTitle);
    }

    // ==========================================================
    // 主题/被观察者：公众号
    // ==========================================================
    static class OfficialAccount {
        private final String name;
        // 观察者列表：谁关注了我
        private final List<Observer> subscribers = new ArrayList<>();

        public OfficialAccount(String name) {
            this.name = name;
        }

        // 关注（注册观察者）
        public void subscribe(Observer observer) {
            subscribers.add(observer);
        }

        // 取关
        public void unsubscribe(Observer observer) {
            subscribers.remove(observer);
        }

        // 发布文章 —— 状态变化，触发广播
        public void publish(String title) {
            System.out.println("\n[公众号: " + name + "] 发布新文章《" + title + "》");
            notifyAllSubscribers(title);
        }

        // 遍历所有观察者，逐一通知 —— 这就是"广播"
        private void notifyAllSubscribers(String title) {
            for (Observer o : subscribers) {
                o.update(title);
            }
        }
    }

    // ==========================================================
    // 具体观察者 1：读者小明
    // ==========================================================
    static class ReaderXiaoMing implements Observer {
        @Override
        public void update(String articleTitle) {
            System.out.println("  [小明] 收到推送，马上点开《" + articleTitle + "》阅读！");
        }
    }

    // ==========================================================
    // 具体观察者 2：读者小红（同一个事件，不同人不同反应）
    // ==========================================================
    static class ReaderXiaoHong implements Observer {
        @Override
        public void update(String articleTitle) {
            System.out.println("  [小红] 收到推送，先收藏《" + articleTitle + "》，回头再看");
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 观察者模式演示 =====");

        OfficialAccount account = new OfficialAccount("Java 进阶之路");

        Observer xiaoMing = new ReaderXiaoMing();
        Observer xiaoHong = new ReaderXiaoHong();

        // 两人都关注了
        account.subscribe(xiaoMing);
        account.subscribe(xiaoHong);

        account.publish("设计模式 23 种速记口诀");

        // 小红取关了，之后只有小明收到通知 —— 观察者可以动态增减！
        account.unsubscribe(xiaoHong);
        account.publish("Spring AOP 原理剖析");
    }
}
