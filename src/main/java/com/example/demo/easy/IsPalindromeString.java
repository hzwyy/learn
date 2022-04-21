package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/18 10:57
 * @description: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 说明：本题中，我们将空字符串定义为有效的回文串
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama" 输出: true 解释："amanaplanacanalpanama" 是回文串 示例 2:
 * <p>
 * 输入: "race a car" 输出: false 解释："raceacar" 不是回文串
 */
public class IsPalindromeString {


  public static boolean isPalindrome(String s) {

    int begin = 0;
    int end = s.length() - 1;

    while (begin <= end) {

      if (!Character.isLetterOrDigit(s.charAt(begin))) {
        begin++;
        continue;
      }
      if (!Character.isLetterOrDigit(s.charAt(end))) {
        end--;
        continue;
      }

      if (Character.toLowerCase(s.charAt(begin)) != Character.toLowerCase(s.charAt(end))) {
        return false;
      }
      begin++;
      end--;

    }
    return true;
  }


  public static void main(String[] args) {
    String a = "race a ecar";
    System.out.println(isPalindrome(a));
  }


}
