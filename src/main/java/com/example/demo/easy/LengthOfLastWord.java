package com.example.demo.easy;

import java.util.Stack;

/**
 * @author: YJY
 * @date: 2022/1/26 10:52
 * @description: 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "Hello World" 输出：5 示例 2：
 * <p>
 * 输入：s = "   fly me   to   the moon  " 输出：4 示例 3：
 * <p>
 * 输入：s = "luffy is still joyboy" 输出：6  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104 s 仅有英文字母和空格 ' ' 组成 s 中至少存在一个单词
 */
public class LengthOfLastWord {

  public static int lengthOfLastWord(String s) {

    int length = s.length()-1;
    int cL = 0;
    while (s.charAt(length) == ' ') {
      length--;
    }

    while (length >= 0 && s.charAt(length) != ' ') {

      cL++;
      length--;
    }

    return cL;
  }

  public static void main(String[] args) {

    System.out.println(lengthOfLastWord("luffy is still joyboy"));
  }
}
