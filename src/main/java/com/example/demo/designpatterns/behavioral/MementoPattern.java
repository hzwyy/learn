package com.example.demo.designpatterns.behavioral;

/**
 * ============================================================
 * 行为型模式 09 —— 备忘录模式（Memento）
 * ============================================================
 * 【一句话定义】
 *   在不破坏封装的前提下，捕获对象的内部状态并保存起来，
 *   以便以后恢复到这个状态（快照 / 撤销）。
 *
 * 【经典场景】
 *   - 编辑器 Ctrl+Z / 游戏存档
 *   - 数据库事务的回滚点（savepoint）
 *   - 画图软件的历史记录
 *
 * 【三个角色】
 *   发起人（Originator）—— 拥有状态，能创建快照、从快照恢复
 *   备忘录（Memento）—— 状态的"封装体"，最好对外不可读
 *   管理者（Caretaker）—— 只负责存取备忘录，绝不偷看内容！
 *
 * 【封装的意义】
 *   管理者拿到的是"黑盒"，看不到也改不了里面的状态。
 *   状态只有发起人自己能写入读出 —— 不破坏封装性。
 *
 * 【本案例的封装技巧】
 *   Memento 的字段用包级私有，构造器和 getter 对外不开放；
 *   Originator 通过"构造器传入快照 + 字段直接读取"完成存取，
 *   管理者只是个"保管柜"。
 */
public class MementoPattern {

    // ==========================================================
    // 备忘录：文章快照（黑盒！管理者不能读不能改）
    // ==========================================================
    static class ArticleMemento {
        // 包级私有字段：只有同包（即发起人）能碰
        final String title;
        final String content;

        // 构造器私有同包可访问，外界无法伪造快照
        ArticleMemento(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    // ==========================================================
    // 发起人：正在编辑的文章
    // ==========================================================
    static class ArticleEditor {
        private String title;
        private String content;

        public void write(String title, String content) {
            this.title = title;
            this.content = content;
        }

        // 创建快照：把当前状态打包成备忘录交出去
        public ArticleMemento save() {
            System.out.println("  [编辑器] 保存快照 ← 《" + title + "》");
            return new ArticleMemento(title, content);
        }

        // 从快照恢复：状态回到过去的某一刻
        public void restore(ArticleMemento memento) {
            this.title = memento.title;
            this.content = memento.content;
            System.out.println("  [编辑器] 已恢复到快照: 《" + title + "》");
        }

        @Override
        public String toString() {
            return "《" + title + "》: " + content;
        }
    }

    // ==========================================================
    // 管理者：历史记录保管柜
    // 只负责存取备忘录，完全不知道备忘录里装了什么 —— 保持封装！
    // ==========================================================
    static class History {
        private final java.util.Deque<ArticleMemento> stack = new java.util.ArrayDeque<>();

        public void push(ArticleMemento memento) {
            stack.push(memento);
        }

        public ArticleMemento pop() {
            return stack.pop(); // 弹出最近一次快照（后进先出 = 先撤最近的）
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 备忘录模式演示 =====");

        ArticleEditor editor = new ArticleEditor();
        History history = new History(); // 保管柜

        // 第一版
        editor.write("设计模式笔记", "第一版：只写了单例模式");
        history.push(editor.save()); // 打快照

        // 第二版
        editor.write("设计模式笔记", "第二版：加上了工厂模式和建造者模式");
        history.push(editor.save());

        // 第三版：手滑写坏了（模拟误操作）
        editor.write("设计模式笔记", "第三版：sd#$% 内容全删光了！");
        System.out.println("当前内容: " + editor);

        // 后悔了，撤销 —— 恢复到最近的快照
        System.out.println("\n--- 执行 Ctrl+Z 撤销 ---");
        editor.restore(history.pop());
        System.out.println("撤销后: " + editor);

        // 再撤销一次
        System.out.println("\n--- 再撤销一次 ---");
        editor.restore(history.pop());
        System.out.println("最终: " + editor);
    }
}
