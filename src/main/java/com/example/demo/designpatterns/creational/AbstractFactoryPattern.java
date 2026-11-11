package com.example.demo.designpatterns.creational;

/**
 * ============================================================
 * 创建型模式 03 —— 抽象工厂模式（Abstract Factory）
 * ============================================================
 * 【一句话定义】
 *   提供一个接口，用于创建"一组相关或相互依赖的对象"，
 *   而不需要指定它们的具体类。
 *
 * 【和工厂方法的区别（面试必问）】
 *   - 工厂方法：一个工厂只生产【一种】产品（一个产品等级结构）
 *   - 抽象工厂：一个工厂生产【一族】产品（多个产品等级结构）
 *
 * 【经典场景】
 *   - UI 框架换主题：Windows 风格工厂生产 Win 按钮 + Win 文本框，
 *     Mac 风格工厂生产 Mac 按钮 + Mac 文本框。一次换，整族换。
 *   - 换数据库：MySQL 工厂生产 MySQL 连接 + MySQL 命令；
 *     Oracle 工厂生产 Oracle 连接 + Oracle 命令。
 *
 * 【通俗理解】
 *   工厂方法 = 专门卖一种鞋的鞋店
 *   抽象工厂 = 宜家，一次买齐成套的"同风格"家具（桌椅柜都是一套的）
 *
 * 【优缺点】
 *   + 保证同一族的产品一起使用，不会出现"Win 按钮配 Mac 文本框"的混乱
 *   + 换一整族产品只需换一个工厂，非常方便
 *   - 加【新产品种类】很痛苦：要在抽象工厂和所有具体工厂里都加方法，
 *     违反开闭原则（加产品"族"容易，加产品"种类"难）
 */
public class AbstractFactoryPattern {

    // ==========================================================
    // 产品族 A：按钮（同一个等级结构）
    // ==========================================================
    interface Button {
        void render();
    }

    static class WinButton implements Button {
        @Override
        public void render() {
            System.out.println("  [Win按钮] 方方正正的灰色按钮");
        }
    }

    static class MacButton implements Button {
        @Override
        public void render() {
            System.out.println("  [Mac按钮] 圆润的半透明按钮");
        }
    }

    // ==========================================================
    // 产品族 B：文本框（另一个等级结构）
    // ==========================================================
    interface TextBox {
        void render();
    }

    static class WinTextBox implements TextBox {
        @Override
        public void render() {
            System.out.println("  [Win文本框] 白底黑边文本框");
        }
    }

    static class MacTextBox implements TextBox {
        @Override
        public void render() {
            System.out.println("  [Mac文本框] 圆角浅灰文本框");
        }
    }

    // ==========================================================
    // 抽象工厂：定义"一套 UI 组件"该怎么生产
    // 注意：一个工厂要生产【两种】产品，这就是和工厂方法的本质区别
    // ==========================================================
    interface UIFactory {
        Button createButton();   // 生产按钮
        TextBox createTextBox(); // 生产文本框
    }

    // Win 风格工厂：只生产 Win 全家桶
    static class WinFactory implements UIFactory {
        @Override
        public Button createButton() {
            return new WinButton();
        }

        @Override
        public TextBox createTextBox() {
            return new WinTextBox();
        }
    }

    // Mac 风格工厂：只生产 Mac 全家桶
    static class MacFactory implements UIFactory {
        @Override
        public Button createButton() {
            return new MacButton();
        }

        @Override
        public TextBox createTextBox() {
            return new MacTextBox();
        }
    }

    /**
     * 客户端代码：只依赖抽象工厂和抽象产品。
     * 想把整个界面从 Win 风格换成 Mac 风格？
     * 只需要把 new WinFactory() 换成 new MacFactory()，其他一行不用动。
     */
    static class Application {
        private final Button button;
        private final TextBox textBox;

        // 构造时注入哪个工厂，界面就是哪种风格
        public Application(UIFactory factory) {
            this.button = factory.createButton();
            this.textBox = factory.createTextBox();
        }

        public void renderUI() {
            button.render();
            textBox.render();
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 抽象工厂模式演示 =====");

        System.out.println(">>> 使用 Win 风格工厂：");
        new Application(new WinFactory()).renderUI();

        System.out.println(">>> 切换为 Mac 风格工厂（客户端代码结构完全没变）：");
        new Application(new MacFactory()).renderUI();
    }
}
