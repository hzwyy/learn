package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/2/9 15:28
 * @description: 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：ransomNote = "a", magazine = "b" 输出：false 示例 2：
 * <p>
 * 输入：ransomNote = "aa", magazine = "ab" 输出：false 示例 3：
 * <p>
 * 输入：ransomNote = "aa", magazine = "aab" 输出：true  
 * <p>
 * 提示：
 * <p>
 * 1 <= ransomNote.length, magazine.length <= 105 ransomNote 和 magazine 由小写英文字母组成
 */
public class CanConstruct {

  /**
   * @return boolean
   * @Author YJY
   * @Description 直接使用计数器 计算即可
   * @Date 2022/2/9
   * @Param [ransomNote, magazine]
   **/
  public static boolean canConstruct(String ransomNote, String magazine) {

    int[] c = new int[26];
    if (ransomNote.length() > magazine.length()) {
      return false;
    }
    for (char a : magazine.toCharArray()) {
      //在改下标加一 记录这个字母出现的次数
      c[a - 'a']++;
    }

    for (char a : ransomNote.toCharArray()) {
      //在该下标减一 上面已经记录了次数 如果减一后小于 0 则表示 不能满足题目要求
      c[a - 'a']--;

      if (c[a - 'a'] < 0) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {

    System.out.println(canConstruct("a", "b"));
  }
}
