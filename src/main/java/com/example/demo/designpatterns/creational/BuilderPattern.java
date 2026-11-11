package com.example.demo.designpatterns.creational;

/**
 * ============================================================
 * 创建型模式 04 —— 建造者模式（Builder）
 * ============================================================
 * 【一句话定义】
 *   把一个复杂对象的构建过程拆分出来，用链式调用一步步设置参数，
 *   最后 build() 出一个完整对象。
 *
 * 【经典场景】
 *   - new 一个有很多可选参数的对象（比"重叠构造器"优雅得多）
 *   - StringBuilder.append().append().toString() 就是这个思想
 *   - MyBatis 的 SqlSessionFactoryBuilder、Lombok 的 @Builder
 *   - OkHttp / Retrofit 的 Request.Builder()
 *
 * 【解决什么痛点？】
 *   一个类有 10 个参数，其中 6 个可选，传统写法：
 *     new User("张三", 18, null, null, "北京", null, ...)
 *   —— 参数一多就分不清谁是谁，还容易传错位置。
 *   建造者写法：
 *     new User.Builder().name("张三").age(18).city("北京").build()
 *   —— 想设哪个设哪个，可读性极强。
 *
 * 【四个角色】
 *   产品（Product）     —— 最终要构建的复杂对象
 *   抽象建造者（Builder）—— 规定构建步骤
 *   具体建造者          —— 实现每个步骤
 *   指挥者（Director）  —— 规定步骤的执行顺序（可选，简化版常省略）
 *
 * 【与工厂模式的区别】
 *   工厂模式关注"生产什么"，一步到位；
 *   建造者模式关注"怎么组装"，分步装配，适合参数多、步骤复杂的对象。
 */
public class BuilderPattern {

    // ==========================================================
    // 产品类：一台电脑（参数多，且多数可选）
    // ==========================================================
    static class Computer {
        // 必填参数
        private final String cpu;    // CPU 必须有
        private final String memory; // 内存必须有

        // 可选参数
        private final String disk;   // 硬盘
        private final String gpu;    // 显卡
        private final String screen; // 显示器

        // 构造器私有化：外界不能直接 new，必须通过 Builder
        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.memory = builder.memory;
            this.disk = builder.disk;
            this.gpu = builder.gpu;
            this.screen = builder.screen;
        }

        @Override
        public String toString() {
            return "Computer{cpu='" + cpu + "', memory='" + memory
                    + "', disk='" + disk + "', gpu='" + gpu
                    + "', screen='" + screen + "'}";
        }

        /**
         * 静态内部类 Builder：把"分步构建"封装在这里。
         * 每个 setter 方法返回 this（Builder 自己），就能链式调用。
         */
        static class Builder {
            // 必填参数在 Builder 构造器里就传进来，想赖都赖不掉
            private final String cpu;
            private final String memory;

            // 可选参数给默认值
            private String disk = "无";
            private String gpu = "集成显卡";
            private String screen = "无";

            public Builder(String cpu, String memory) {
                this.cpu = cpu;
                this.memory = memory;
            }

            // 关键技巧：每个方法 return this，实现链式调用
            public Builder disk(String disk) {
                this.disk = disk;
                return this; // 返回自身，支持 .disk().gpu() 连着写
            }

            public Builder gpu(String gpu) {
                this.gpu = gpu;
                return this;
            }

            public Builder screen(String screen) {
                this.screen = screen;
                return this;
            }

            // 最终装配：把 Builder 里攒的参数一次性交给产品
            public Computer build() {
                // 可以在这里做参数校验！比构造器里校验更清晰
                if (cpu == null || memory == null) {
                    throw new IllegalArgumentException("CPU 和内存是必填项");
                }
                return new Computer(this);
            }
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 建造者模式演示 =====");

        // 场景 1：只装必需品（办公电脑）
        // 链式调用像"填表格"一样直观，跳过不需要的参数
        Computer officePc = new Computer.Builder("i5-13400", "16G")
                .disk("512G SSD")
                .build();
        System.out.println("办公电脑: " + officePc);

        // 场景 2：全都要（游戏电脑）
        Computer gamePc = new Computer.Builder("i9-14900K", "64G")
                .disk("2T NVMe")
                .gpu("RTX 4090")
                .screen("4K 240Hz")
                .build();
        System.out.println("游戏电脑: " + gamePc);

        // 场景 3：参数校验生效
        try {
            new Computer.Builder(null, "16G").build();
        } catch (IllegalArgumentException e) {
            System.out.println("参数校验生效: " + e.getMessage());
        }
    }
}
