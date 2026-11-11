package com.example.demo.designpatterns.structural;

/**
 * ============================================================
 * 结构型模式 01 —— 适配器模式（Adapter）
 * ============================================================
 * 【一句话定义】
 *   把一个类的接口转换成客户端期望的另一种接口，
 *   让原本"接口不兼容"的类可以一起工作。
 *
 * 【经典场景】
 *   - 老系统对接新系统：老接口不能改，写个适配器包一层
 *   - Spring MVC 的 HandlerAdapter：统一适配各种 Controller
 *   - JDK 的 Arrays.asList()：把数组适配成 List
 *   - 现实比喻：港版插头 → 转换插头 → 国标插座
 *
 * 【两种实现方式】
 *   1. 类适配器：继承被适配类（Java 单继承，有局限，不推荐）
 *   2. 对象适配器：组合（持有被适配类的实例）—— 推荐！
 *
 * 【什么时候用】
 *   代码已经写好了，接口改不动（可能是三方库或老系统），
 *   只能加一层"转换器"来迁就它。
 *
 * 【与装饰器的区别】
 *   适配器：为了"兼容"，改变接口；事后补救
 *   装饰器：为了"增强"，接口不变；主动设计
 */
public class AdapterPattern {

    // ==========================================================
    // 背景：客户端只认识这个"目标接口"—— 只接受 TypeC 充电
    // ==========================================================
    interface TypeCPhone {
        void chargeWithTypeC();
    }

    // ==========================================================
    // 被适配者：老式的圆孔充电器（接口不兼容，又不能改它）
    // ==========================================================
    static class OldCircleCharger {
        public void chargeWithCircle() {
            System.out.println("  [老充电器] 通过圆孔接口供电中...");
        }
    }

    // ==========================================================
    // 对象适配器（推荐写法）：持有被适配者的引用，实现目标接口
    // 作用就像一个"转换插头"
    // ==========================================================
    static class CircleToTypeCAdapter implements TypeCPhone {
        private final OldCircleCharger oldCharger; // 组合：持有被适配对象

        public CircleToTypeCAdapter(OldCircleCharger oldCharger) {
            this.oldCharger = oldCharger;
        }

        @Override
        public void chargeWithTypeC() {
            System.out.println("  [适配器] 把 TypeC 接口转换成圆孔接口...");
            // 内部偷偷调用老充电器的方法，客户端完全无感知
            oldCharger.chargeWithCircle();
        }
    }

    // ==========================================================
    // 类适配器（了解即可）：通过继承同时拥有两边的特征
    // 缺点：占用了唯一的继承名额，且暴露了老类的 public 方法
    // ==========================================================
    static class ClassAdapter extends OldCircleCharger implements TypeCPhone {
        @Override
        public void chargeWithTypeC() {
            System.out.println("  [类适配器] 转换中...");
            // 因为继承了，可以直接调 super 的方法
            super.chargeWithCircle();
        }
    }

    // ==========================================================
    // 客户端
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 适配器模式演示 =====");

        // 客户端眼里只有 TypeCPhone 接口，完全不知道背后是个老充电器
        TypeCPhone phone = new CircleToTypeCAdapter(new OldCircleCharger());
        phone.chargeWithTypeC();

        System.out.println("--- 类适配器写法（继承方式）---");
        TypeCPhone phone2 = new ClassAdapter();
        phone2.chargeWithTypeC();
    }
}
