package com.example.demo.designpatterns.creational;

/**
 * ============================================================
 * 创建型模式 01 —— 单例模式（Singleton）
 * ============================================================
 * 【一句话定义】
 *   保证一个类在整个 JVM 中只有一个实例，并提供一个全局访问点。
 *
 * 【经典场景】
 *   - 数据库连接池、线程池
 *   - Spring 中的 Bean 默认就是单例（容器级别的）
 *    @Controller、@Service、@Repository、@Component 标注的 Bean，默认都是 Singleton 自己创建的则不是
 *   - 配置文件管理器、日志对象、Windows 的任务管理器
 *
 * 【为什么要用它】
 *   有些对象创建成本高（比如读配置、建连接），或者业务上就
 *   只能有一个（比如打印机队列）。如果到处 new，既浪费内存，
 *   又可能造成状态不一致。
 *
 * 【实现方式的演进（重点掌握前三种）】
 *   1. 饿汉式   —— 类加载时就创建。简单安全，但可能浪费资源。
 *   2. 懒汉式   —— 第一次用时才创建。需要加锁，否则线程不安全。
 *   3. 双重检查锁（DCL）—— 懒加载 + 高性能 + 线程安全，面试常考。
 *   4. 静态内部类 —— 利用类加载机制保证线程安全，推荐写法。
 *   5. 枚举     —— 《Effective Java》推荐，天然防反射、防反序列化破坏。
 * 几种单例模式对比
 *
 * 单例模式	    线程安全	    防反射	        防序列化
 *
 * 饿汉式	    是	        否，需处理       否，需处理
 * 懒汉式	视实现而定	    否，需处理	    否，需处理
 * 双重检查锁	是	        否，需处理	    否，需处理
 * 静态内部类	是	        否，需处理	    否，需处理
 * 枚举单例	    是	        防护	            防护
 *
 * 【JDK 中的实际应用】
 *   - Runtime.getRuntime()
 *   - Spring 的单例 Bean（容器级）
 */
public class SingletonPattern {

    // ==========================================================
    // 写法一：饿汉式（最简单，推荐在没有性能顾虑时使用）
    // ==========================================================
    static class HungrySingleton {
        // 类加载的时候就直接 new 好，天然线程安全（类加载过程由 JVM 保证）
        // 缺点：不管用不用，都会创建，可能浪费内存
        private static final HungrySingleton INSTANCE = new HungrySingleton();

        // 构造器私有化：外部无法 new，这是单例的第一道防线
        private HungrySingleton() {
            // 防御反射攻击：如果实例已存在，再通过反射调用构造器就直接报错
            if (INSTANCE != null) {
                throw new RuntimeException("禁止通过反射创建单例！");
            }
        }
        //如果单例类实现了 Serializable，防序列化攻击 第二道防线
        private Object readResolve() {
            return INSTANCE;
        }

        public static HungrySingleton getInstance() {
            return INSTANCE;
        }
    }

    // ==========================================================
    // 写法二：双重检查锁（DCL）—— 面试高频考点
    // ==========================================================
    static class DoubleCheckSingleton {
        // volatile 的作用（关键考点）：
        // new 一个对象不是原子操作，分三步：
        //   ① 分配内存  ② 初始化对象  ③ 把引用指向内存
        // 如果没有 volatile，JVM 可能指令重排成 ①③②，
        // 另一个线程拿到"还没初始化完"的对象，就会出错。
        private static volatile DoubleCheckSingleton instance;

        private DoubleCheckSingleton() {}

        public static DoubleCheckSingleton getInstance() {
            // 第一次检查：实例已存在就不用抢锁，提升性能
            if (instance == null) {
                // 只有第一次进来才会加锁，多个线程同时到这才排队
                synchronized (DoubleCheckSingleton.class) {
                    // 第二次检查：排队进来的线程发现别人已经创建过了，就不再创建
                    if (instance == null) {
                        instance = new DoubleCheckSingleton();
                    }
                }
            }
            return instance;
        }
    }

    // ==========================================================
    // 写法三：静态内部类（推荐！兼具懒加载和线程安全）
    // ==========================================================
    static class InnerClassSingleton {
        private InnerClassSingleton() {}

        // 原理：JVM 保证"类的静态内部类"只会在第一次被使用时才加载，
        // 且类加载过程是线程安全的。所以既懒加载，又不用自己加锁。
        private static class Holder {
            private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
        }

        public static InnerClassSingleton getInstance() {
            // 第一次调用 getInstance 时，才会触发 Holder 类的加载
            return Holder.INSTANCE;
        }
    }

    // ==========================================================
    // 写法四：枚举（最安全，Joshua Bloch 强烈推荐）
    // ==========================================================
    enum EnumSingleton {
        INSTANCE; // 枚举天生只有一个实例，JVM 层面保证

        public void doSomething() {
            System.out.println("  [枚举单例] 我是唯一实例：" + this);
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 单例模式演示 =====");

        // 验证 1：多次获取拿到的是同一个对象（内存地址相同）
        HungrySingleton s1 = HungrySingleton.getInstance();
        HungrySingleton s2 = HungrySingleton.getInstance();
        System.out.println("饿汉式两次获取是否同一个对象: " + (s1 == s2));

        DoubleCheckSingleton d1 = DoubleCheckSingleton.getInstance();
        DoubleCheckSingleton d2 = DoubleCheckSingleton.getInstance();
        System.out.println("DCL 两次获取是否同一个对象:   " + (d1 == d2));

        InnerClassSingleton i1 = InnerClassSingleton.getInstance();
        InnerClassSingleton i2 = InnerClassSingleton.getInstance();
        System.out.println("静态内部类两次获取是否同一对象: " + (i1 == i2));

        EnumSingleton.INSTANCE.doSomething();
    }
}
