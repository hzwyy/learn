package com.example.demo.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: YJY
 * @date: 2022/2/9 10:45
 * @description: 给定两个字符串 s 和 t ，判断它们是否是同构的。 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上， 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * 示例 1: 输入：s = "egg", t = "add" 输出：true
 * <p>
 * 示例 2： 输入：s = "foo", t = "bar" 输出：false
 * <p>
 * 示例 3： 输入：s = "paper", t = "title" 输出：true   提示： 1 <= s.length <= 5 * 104 t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 */
public class IsIsomorphic {

  /**
   * @return boolean
   * @Author YJY
   * @Description 直接判断位置 如果是同构的 他们每一个字符虽然不一样 但是对应的下标位置肯定是一样的
   * @Date 2022/2/9
   * @Param [s, t]
   **/
  public static boolean isIsomorphic(String s, String t) {
    for (int i = 0; i < s.length(); i++) {
      if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isIsomorphic2(String s, String t) {
    Map<Character, Character> s2t = new HashMap<Character, Character>();
    Map<Character, Character> t2s = new HashMap<Character, Character>();
    int len = s.length();
    for (int i = 0; i < len; ++i) {
      char x = s.charAt(i), y = t.charAt(i);
      if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
        return false;
      }
      s2t.put(x, y);
      t2s.put(y, x);
    }
    return true;
  }


  public static void main(String[] args) {

    String a = "sttr";
    String b = "assd";
    System.out.println(isIsomorphic2(a, b));

  }
}
