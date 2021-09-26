package com.example.demo.string;

/**
 * @author: YJY
 * @date: 2021/9/26 15:52
 * @description:
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 */
public class ReverseWords {


  public static String reverseWords(String s) {
    StringBuffer ret = new StringBuffer();
    int length = s.length();
    int i = 0;
    while (i < length) {
      int start = i;
      while (i < length && s.charAt(i) != ' ') {
        i++;
      }
      for (int p = start; p < i; p++) {
        ret.append(s.charAt(start + i - 1 - p));
      }
      while (i < length && s.charAt(i) == ' ') {
        i++;
        ret.append(' ');
      }
    }
    return ret.toString();
  }


  public static void main(String[] args) {

    String word = "Let's take LeetCode contest";

    System.out.println(reverseWords(word));

  }

}
