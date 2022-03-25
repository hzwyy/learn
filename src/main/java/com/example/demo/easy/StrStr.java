package com.example.demo.easy;


import java.util.LinkedList;

/**
 * @author: YJY
 * @date: 2022/1/18 10:09
 * @description: 实现 strStr() 函数。
 * <p>
 * 给你两个字符串 haystack 和 needle ， 请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。 如果不存在，则返回  -1 。
 * <p>
 * 说明： 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 对于本题而言，当 needle 是空字符串时我们应当返回 0 。 这与 C
 * 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 *   示例 1： 输入：haystack = "hello", needle = "ll" 输出：2
 * <p>
 * 示例 2： 输入：haystack = "aaaaa", needle = "bba" 输出：-1
 * <p>
 * 示例 3： 输入：haystack = "", needle = "" 输出：0
 */
public class StrStr {

  /**
   * @return int
   * @Author YJY
   * @Description 一般解法
   * @Date 2022/1/18
   * @Param [haystack, needle]
   **/
  public static int strStr(String haystack, String needle) {

    if (null == needle || "".equals(needle)) {
      return 0;
    }
    if (null == haystack || "".equals(haystack) || needle.length() > haystack.length()) {
      return -1;
    }
    char[] h = haystack.toCharArray();
    char[] n = needle.toCharArray();
    int hl = haystack.length();
    int nl = needle.length();

    //hl - nl 只在这个长度里有答案
    for (int i = 0; i <= hl - nl; i++) {

      int hs = i;
      int ns = 0;
      while (ns < nl && h[hs] == n[ns]) {
        hs++;
        ns++;
      }

      if (ns == nl) {
        return i;
      }
    }
    return -1;
  }


  public static void main(String[] args) {

    String haystack = "a";
    String needle = "a";

    System.out.println(strStr(haystack, needle));
  }


}
