package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/1/24 16:25
 * @description:
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 *
 * 输入：s = "", t = "y"
 * 输出："y"
 *
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 *  
 * 提示：
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 *
 */
public class FindTheDifference {


  /**
   * @Author YJY
   * @Description
   * 方法一：计数
   * 首先遍历字符串 s，对其中的每个字符都将计数值加 1；
   * 然后遍历字符串 t，对其中的每个字符都将计数值减 1。
   * 当发现某个字符计数值为负数时，说明该字符在字符串 t 中出现的次数大于在字符串 s 中出现的次数，
   * 因此该字符为被添加的字符

   * @Date  2022/1/24
   * @Param [s, t]
   * @return char
   **/
  public char findTheDifference1(String s, String t) {
    int[] cnt = new int[26];
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      cnt[ch - 'a']++;
    }
    for (int i = 0; i < t.length(); ++i) {
      char ch = t.charAt(i);
      cnt[ch - 'a']--;
      if (cnt[ch - 'a'] < 0) {
        return ch;
      }
    }
    return ' ';
  }

  /**
   * @Author YJY
   * @Description
   * 将字符串 ss 中每个字符的 ASCII 码的值求和，得到 A_s
   * 对字符串 tt 同样的方法得到 A_t两者的差值 A_t-A_s
   *   即代表了被添加的字符。
   * @Date  2022/1/24
   * @Param [s, t]
   * @return char
   **/
  public char findTheDifference2(String s, String t) {
    int as = 0, at = 0;
    for (int i = 0; i < s.length(); ++i) {
      as += s.charAt(i);
    }
    for (int i = 0; i < t.length(); ++i) {
      at += t.charAt(i);
    }
    return (char) (at - as);
  }

  /**
   * @Author YJY
   * @Description
   * 位运算
   * @Date  2022/1/24
   * @Param [s, t]
   * @return char
   **/
  public char findTheDifference3(String s, String t) {
    int ret = 0;
    for (int i = 0; i < s.length(); ++i) {
      ret ^= s.charAt(i);
    }
    for (int i = 0; i < t.length(); ++i) {
      ret ^= t.charAt(i);
    }
    return (char) ret;
  }


}
