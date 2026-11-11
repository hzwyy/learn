package com.example.demo.designpatterns.behavioral;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * 行为型模式 10 —— 解释器模式（Interpreter）
 * ============================================================
 * 【一句话定义】
 *   为某种"语言"定义文法，并设计一个解释器来解释该语言中的句子。
 *
 * 【经典场景】
 *   - 正则表达式：java.util.regex.Pattern 内部就是解释器
 *   - SQL 解析器、SpEL 表达式、规则引擎
 *   - 计算器：解析 "1 + 2 * 3" 并算出结果
 *
 * 【核心思想】
 *   每种语法规则 = 一个类；表达式 = 这些类组成的"抽象语法树"（AST）。
 *   求值 = 从树根开始递归调用每个节点的 interpret()。
 *
 * 【本案例实现一个四则运算解释器】
 *   文法规则（简化版）：
 *     表达式 ::= 数字 | (表达式 运算符 表达式)
 *     运算符 ::= + | -
 *
 *   "7 + 3 - 2" 会被构建成这样一棵树：
 *            Minus
 *           /    \
 *         Plus    2
 *        /   \
 *       7     3
 *   解释时：Minus.interpret() = 左子树值 - 右子树值，递归下去。
 *
 * 【优缺点】
 *   + 语法规则一目了然，容易扩展新运算符（加个类就行）
 *   + 与享元/组合模式配合好
 *   - 每条规则一个类，语法复杂时类爆炸；一般直接用现成的库
 */
public class InterpreterPattern {

    // ==========================================================
    // 抽象表达式：所有语法节点的公共接口
    // interpret 的意思是"在这套变量环境下，我求出来的值是多少"
    // ==========================================================
    interface Expression {
        int interpret();
    }

    // ==========================================================
    // 终结符表达式：数字（不能再往下拆分了，所以叫"终结符"）
    // ==========================================================
    static class NumberExpression implements Expression {
        private final int value;

        public NumberExpression(int value) {
            this.value = value;
        }

        @Override
        public int interpret() {
            return value; // 数字自己就是值，直接返回
        }
    }

    // ==========================================================
    // 非终结符表达式：加法（持有两个子表达式 = 树的左右分支）
    // ==========================================================
    static class AddExpression implements Expression {
        private final Expression left;
        private final Expression right;

        public AddExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret() {
            // 递归：我的值 = 左边算出来 + 右边算出来
            return left.interpret() + right.interpret();
        }
    }

    // ==========================================================
    // 非终结符表达式：减法
    // ==========================================================
    static class SubExpression implements Expression {
        private final Expression left;
        private final Expression right;

        public SubExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpret() {
            return left.interpret() - right.interpret();
        }
    }

    // ==========================================================
    // 简易解析器：把字符串"7+3-2"解析成抽象语法树
    // （真正的编译器这一步复杂得多，这里只演示思想）
    // ==========================================================
    static class Parser {
        public static Expression parse(String text) {
            String[] tokens = text.split(" "); // 按空格切分
            // 第一个数字作为树的根
            Expression result = new NumberExpression(Integer.parseInt(tokens[0]));

            // 循环成对读取：运算符 + 数字，不断把新节点挂到树上
            for (int i = 1; i < tokens.length; i += 2) {
                String op = tokens[i];
                int num = Integer.parseInt(tokens[i + 1]);
                switch (op) {
                    case "+":
                        result = new AddExpression(result, new NumberExpression(num));
                        break;
                    case "-":
                        result = new SubExpression(result, new NumberExpression(num));
                        break;
                    default:
                        throw new IllegalArgumentException("不支持的运算符: " + op);
                }
            }
            return result; // 返回树的根节点
        }
    }

    // ==========================================================
    // 演示入口
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("===== 解释器模式演示 =====");

        // 把 "7 + 3 - 2" 解析成语法树，然后解释执行
        Expression expression = Parser.parse("7 + 3 - 2");
        System.out.println("表达式: 7 + 3 - 2");
        System.out.println("解释结果: " + expression.interpret()); // 8

        Expression expression2 = Parser.parse("10 - 2 + 1");
        System.out.println("表达式: 10 - 2 + 1");
        System.out.println("解释结果: " + expression2.interpret()); // 9

        System.out.println("\n>>> 每个语法规则就是一个类，求值就是递归遍历语法树。");
        System.out.println(">>> 正则 Pattern、SpEL、规则引擎的底层都是这个思路的工业级版本。");
    }
}
