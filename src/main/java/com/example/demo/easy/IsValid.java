package com.example.demo.easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author: YJY
 * @date: 2022/4/15 10:35
 * @description: 有效的括号 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()" 输出：true 示例 2：
 * <p>
 * 输入：s = "()[]{}" 输出：true 示例 3：
 * <p>
 * 输入：s = "(]" 输出：false 示例 4：
 * <p>
 * 输入：s = "([)]" 输出：false 示例 5：
 * <p>
 * 输入：s = "{[]}" 输出：true
 */
public class IsValid {

  /**
   * @return boolean
   * @Author YJY
   * @Description 利用栈先入后出的原理 根据题意即可得出 如果遇到了右括号 那么它的左边必定是符合他的右括号 要不然就不符合题意
   * @Date 2022/4/15
   * @Param [s]
   **/
  public static boolean isValid(String s) {

    //如果不为偶数 必定不符合题意
    if (s.length() % 2 != 0) {
      return false;
    }
    //先将括号 存入hashMap ()[]{} 方便下面判断
    HashMap<Character, Character> hashMap = new HashMap<Character, Character>() {{
      put('(', ')');
      put('{', '}');
      put('[', ']');

    }};
    //先入后出 所以如果遇到了右括号 要去栈中找第一个左括号是否匹配 不匹配就不符合题意
    Stack<Character> stack = new Stack();
    for (int i = 0; i < s.length(); i++) {

      if (hashMap.containsValue(s.charAt(i))) {
         //stack.peek() 返回栈顶元素 但是不删除
        if (stack.isEmpty() || hashMap.get(stack.peek()) != s.charAt(i)) {
          return false;
        } else {
          //返回栈顶元素 并删除
          stack.pop();
        }
      }else{
        stack.push(s.charAt(i));
      }
    }
    //如果遇到(( 这种的只存入没有比较了 所以还要判断栈中元素是否都弹出
    //如果还有元素代表不符合题意
    return stack.isEmpty();
  }

  public static void main(String[] args) {

    String s = "((";
    System.out.println(isValid(s));
  }
}
