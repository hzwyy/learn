package com.example.demo.string;

import com.example.demo.util.Swap;
import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/9/26 11:29
 * @description: 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * 示例 1：
 * <p>
 * 输入：["h","e","l","l","o"] 输出：["o","l","l","e","h"] 示例 2：
 * <p>
 * 输入：["H","a","n","n","a","h"] 输出：["h","a","n","n","a","H"]
 */
public class ReverseString {


  /**
   * @Description 双指针简单快捷
   **/
  public static void reverseString(String[] s) {

    int i = 0;
    int j = s.length - 1;

    for (; i < j; ) {

      String temp = s[i];
      s[i] = s[j];
      s[j] = temp;
      i++;
      j--;
    }

    System.out.println(Arrays.toString(s));

  }

  /**
   * @Description 异或运算 速度更快 空间更省 String 无法异或
   **/
  public static void reverseString(char[] s) {
    int n = s.length;
    for (int i = 0; i < n / 2; ++i) {
      int j = n - 1 - i;

      s[i] = (char) (s[i] ^ s[j]);
      s[j] = (char) (s[j] ^ s[i]);
      s[i] = (char) (s[i] ^ s[j]);

    }
    System.out.println(Arrays.toString(s));
  }

  public static void main(String[] args) {

//    char[] c = {'H', 'a', 'n', 'n', 'a', 'h'};
//    String[] s = {"H", "a", "n", "n", "a", "h"};
//    reverseString(s);
//    reverseString(c);

    String word = "Let's take LeetCode contest";
    char[] c = word.toCharArray();
    System.out.println(c);
  }
}
