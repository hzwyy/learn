package com.example.demo.designpatterns.creational;

/**
 * ============================================================
 * 创建型模式 02 —— 工厂方法模式（Factory Method）
 * ============================================================
 * 【一句话定义】
 *   定义一个创建对象的接口，让子类决定实例化哪个具体类。
 *   把"new 对象"这件事延迟到子类中去做。
 *
 * 【经典场景】
 *   - 日志框架：不同日志工厂生产 FileLogger / DatabaseLogger
 *   - JDBC：不同数据库驱动工厂返回不同 Connection
 *   - Spring 的 BeanFactory：根据配置决定生产哪种 Bean
 *
 * 【和简单工厂的区别（面试常问）】
 *   - 简单工厂：一个工厂类 + if/switch 判断，加新产品要改老代码，
 *     违反"开闭原则"（对扩展开放、对修改关闭）。
 *   - 工厂方法：每个产品配一个工厂，加新产品 = 新增两个类，
 *     完全不用改老代码，符合开闭原则。
 *
 * 【四个角色】
 *   抽象产品（Product）        —— 规定产品能干什么
 *   具体产品（ConcreteProduct）—— 真正干活的
 *   抽象工厂（Factory）        —— 规定工厂能生产什么
 *   具体工厂（ConcreteFactory）—— 真正生产的
 *
 * 【优缺点】
 *   + 符合开闭原则，扩展方便
 *   + 客户端只依赖抽象，不依赖具体类（解耦）
 *   - 类的数量翻倍，每加一个产品要加两个类
 */
public class FactoryMethodPattern {

    // ==========================================================
    // 角色 1：抽象产品 —— 所有动物都会叫
    // ==========================================================
    interface Animal {
        void speak();
    }

    // ==========================================================
    // 角色 2：具体产品 —— 狗和猫
    // ==========================================================
    static class Dog implements Animal {
        @Override
        public void speak() {
            System.out.println("  [狗] 汪汪汪！");
        }
    }

    static class Cat implements Animal {
        @Override
        public void speak() {
            System.out.println("  [猫] 喵喵喵～");
        }
    }

    // ==========================================================
    // 角色 3：抽象工厂 —— 规定"工厂的任务是生产一只动物"
    // ==========================================================
    interface AnimalFactory {
        // 工厂方法：让子类决定到底生产什么
        Animal createAnimal();
    }

    // ==========================================================
    // 角色 4：具体工厂 —— 狗工厂只生产狗，猫工厂只生产猫
    // ==========================================================
    static class DogFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Dog();
        }
    }

    static class CatFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Cat();
        }
    }

    // ==========================================================
    // 扩展演示：想加"鸟"，只需新增两个类，不动任何老代码
    // 这就是开闭原则的体现！
    // ==========================================================
    static class Bird implements Animal {
        @Override
        public void speak() {
            System.out.println("  [鸟] 叽叽喳喳！");
        }
    }

    static class BirdFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Bird();
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 工厂方法模式演示 =====");

        // 客户端只认"抽象工厂 + 抽象产品"，完全不关心具体类名
        // 想换产品？换一个工厂就行，业务代码一行不用改
        AnimalFactory factory = new DogFactory();
        Animal animal = factory.createAnimal();
        animal.speak();

        factory = new CatFactory();
        factory.createAnimal().speak();

        // 新增产品时，客户端这边只是换了个工厂，无侵入
        factory = new BirdFactory();
        factory.createAnimal().speak();
    }
}
