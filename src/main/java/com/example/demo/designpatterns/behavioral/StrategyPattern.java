package com.example.demo.designpatterns.behavioral;

/**
 * ============================================================
 * 行为型模式 01 —— 策略模式（Strategy）
 * ============================================================
 * 【一句话定义】
 *   定义一族可互换的算法，把它们各自封装成独立的类，
 *   客户端可以动态切换使用哪种算法。
 *
 * 【经典场景】
 *   - 电商促销：原价 / 打折 / 满减，大促时随意切换
 *   - 支付方式：微信 / 支付宝 / 银联
 *   - JDK：ThreadPoolExecutor 的拒绝策略、Comparator 排序规则
 *
 * 【解决什么痛点？消灭 if-else！】
 *   坏味道代码：
 *     if(type == 1) 原价计算;
 *     else if(type == 2) 打折计算;
 *     else if(type == 3) 满减计算;
 *   每加一种活动就要改这个方法，越来越长，谁都不敢动。
 *   策略模式：每种算法一个类，加新算法 = 加一个类，老代码不动。
 *
 * 【三个角色】
 *   策略接口（Strategy）—— 统一的算法签名
 *   具体策略（ConcreteStrategy）—— 每种算法
 *   上下文（Context）—— 持有策略，替客户端调用
 *
 * 【实战升级】
 *   策略 + 工厂/Map 注册，可以彻底干掉 if-else（见 main 里的演示）
 */
public class StrategyPattern {

    // ==========================================================
    // 策略接口：统一"算价格"这个行为
    // ==========================================================
    interface Promotion {
        double calculate(double originalPrice);
    }

    // ==========================================================
    // 具体策略 1：原价
    // ==========================================================
    static class NormalPromotion implements Promotion {
        @Override
        public double calculate(double originalPrice) {
            System.out.println("  [策略:原价] 不做任何优惠");
            return originalPrice;
        }
    }

    // ==========================================================
    // 具体策略 2：打 8 折
    // ==========================================================
    static class DiscountPromotion implements Promotion {
        @Override
        public double calculate(double originalPrice) {
            System.out.println("  [策略:折扣] 全场 8 折");
            return originalPrice * 0.8;
        }
    }

    // ==========================================================
    // 具体策略 3：满 300 减 100
    // ==========================================================
    static class FullReductionPromotion implements Promotion {
        @Override
        public double calculate(double originalPrice) {
            System.out.println("  [策略:满减] 满 300 减 100");
            // 可叠加：每满 300 减 100
            return originalPrice - (int) (originalPrice / 300) * 100;
        }
    }

    // ==========================================================
    // 上下文：把策略包装起来，客户端通过它来使用策略
    // ==========================================================
    static class Order {
        private Promotion promotion; // 当前使用的策略

        public void setPromotion(Promotion promotion) {
            this.promotion = promotion;
        }

        public double checkout(double price) {
            // 委托给策略对象计算 —— 上下文自己不关心算法细节
            return promotion.calculate(price);
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 策略模式演示 =====");

        Order order = new Order();
        double original = 450.0;

        // 同一笔订单，动态切换不同策略 —— 运行时换算法
        order.setPromotion(new NormalPromotion());
        System.out.println("应付: " + order.checkout(original) + " 元\n");

        order.setPromotion(new DiscountPromotion());
        System.out.println("应付: " + order.checkout(original) + " 元\n");

        order.setPromotion(new FullReductionPromotion());
        System.out.println("应付: " + order.checkout(original) + " 元\n");

        // 实战技巧：用 Map 把策略名和策略对象绑定，彻底消灭 if-else
        // （真实项目中常配合 Spring 把所有策略 Bean 注入到一个 Map 里）
        java.util.Map<String, Promotion> strategyMap = new java.util.HashMap<>();
        strategyMap.put("normal", new NormalPromotion());
        strategyMap.put("discount", new DiscountPromotion());
        strategyMap.put("fullReduction", new FullReductionPromotion());

        String userChoice = "fullReduction"; // 这个值可以来自前端参数
        double pay = strategyMap.get(userChoice).calculate(original);
        System.out.println("用户选择 " + userChoice + "，应付: " + pay + " 元");
    }
}
