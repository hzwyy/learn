package com.example.demo.designpatterns.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ============================================================
 * 结构型模式 03 —— 代理模式（Proxy）
 * ============================================================
 * 【一句话定义】
 *   为目标对象提供一个"替身"，通过替身来控制对目标对象的访问。
 *   客户端调用替身，替身在调用目标的前后可以"做点手脚"。
 *
 * 【经典场景】
 *   - Spring AOP：事务、日志、权限校验（动态代理的天下）
 *   - MyBatis 的 Mapper 接口：没有实现类，全靠动态代理
 *   - RPC 框架：远程调用的本地"存根"
 *   - 现实比喻：明星的经纪人（采访先过经纪人这一关）
 *
 * 【三种实现】
 *   1. 静态代理：手写代理类。缺点：每个目标都要写一个代理，类爆炸。
 *   2. JDK 动态代理：基于接口，运行时生成代理类（本案例重点）。
 *   3. CGLIB 动态代理：基于继承（子类），不要求有接口。Spring 默认两者结合。
 *
 * 【与装饰器的区别（易混淆）】
 *   装饰器：客户端自己一层层包，目的是增强功能
 *   代理：客户端拿到的就是代理，目的是控制访问，对客户端透明
 */
public class ProxyPattern {

    // ==========================================================
    // 公共接口：明星和经纪人共同的行为
    // ==========================================================
    interface Star {
        void sing(String song);
    }

    // ==========================================================
    // 真实对象：明星本人，只专心唱歌
    // ==========================================================
    static class RealStar implements Star {
        @Override
        public void sing(String song) {
            System.out.println("  [明星本人] 演唱《" + song + "》");
        }
    }

    // ==========================================================
    // 写法一：静态代理 —— 经纪人手写
    // 能看到：唱歌前后的杂事（谈价、收钱）都加在代理里
    // ==========================================================
    static class StaticAgent implements Star {
        private final RealStar realStar; // 持有真实对象

        public StaticAgent(RealStar realStar) {
            this.realStar = realStar;
        }

        @Override
        public void sing(String song) {
            System.out.println("  [静态代理] 前置工作：谈出场费、签合同");
            realStar.sing(song); // 真正干活的是明星本人
            System.out.println("  [静态代理] 后置工作：收尾款");
        }
    }

    // ==========================================================
    // 写法二：JDK 动态代理 —— 重点掌握！Spring AOP 的基础
    //
    // 原理：运行时由 JVM 自动生成一个代理类（$Proxy0），
    //       它实现了 Star 接口，所有方法调用都会转发给
    //       InvocationHandler.invoke()，我们在 invoke 里做增强。
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 代理模式演示 =====");

        // 1. 静态代理
        Star star1 = new StaticAgent(new RealStar());
        star1.sing("孤勇者");

        // 2. JDK 动态代理
        //    三个参数：类加载器、接口列表、调用处理器
        Star star2 = (Star) Proxy.newProxyInstance(
                Star.class.getClassLoader(),
                new Class[]{Star.class},
                new InvocationHandler() {
                    // proxy: 生成的代理对象
                    // method: 客户端调用的方法（反射 Method 对象）
                    // args:   方法参数
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // ---- 前置增强 ----
                        System.out.println("  [动态代理] 前置工作：谈出场费、签合同");
                        // 转发给真实对象执行，注意要传"真实对象"而不是 proxy，
                        // 如果传 proxy 会无限递归调自己！
                        Object result = method.invoke(new RealStar(), args);
                        // ---- 后置增强 ----
                        System.out.println("  [动态代理] 后置工作：收尾款");
                        return result;
                    }
                });

        // 客户端用起来和真实对象毫无区别，但每次调用都被"拦截"了
        star2.sing("夜曲");

        // 面试要点：动态代理的好处 —— 一个 InvocationHandler 可以代理
        // 任意接口的任意方法，不用为每个类手写代理，这就是 AOP 的原理。
    }
}
