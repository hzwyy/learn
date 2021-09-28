package com.example.demo.string;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: YJY   最长不含重复字符的子字符串
 * @date: 2021/4/6 11:35
 * @description: 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。 输入: s = "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是
 * "abc"，所以其长度为 3。
 * <p>
 * 示例 2: 输入: s = "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class LengthOfLongestSubstring {

  public static int lengthOfLongestSubstring(String s) {

    int l = 0;
    int maxL = 0;
    HashMap hashMap = new HashMap();

    if (StringUtils.isEmpty(s)) {
      return l;
    }
    char[] chars = s.toCharArray();
    int i = 0;
    int j = 0;
    while (i < chars.length && j < chars.length) {

      if (hashMap.containsValue(chars[j])) {
        hashMap.remove(i);
        i++;
      } else {
        hashMap.put(j, chars[j]);
        if (maxL < j - i) {
          maxL = j - i;
        }
        j++;


      }
    }
    return maxL + 1;
  }

  /**
   * @Description 优化后的
   **/
  public int lengthOfLongestSubstringTwo(String s) {
    // 记录字符上一次出现的位置
    int[] last = new int[128];
    for (int i = 0; i < 128; i++) {
      last[i] = -1;
    }
    int n = s.length();

    int res = 0;
    // 窗口开始位置
    int start = 0;
    for (int i = 0; i < n; i++) {
      int index = s.charAt(i);
      start = Math.max(start, last[index] + 1);
      res = Math.max(res, i - start + 1);
      last[index] = i;
    }

    return res;
  }


  public static void main(String[] args) {

    String s = null;
    System.out.println(s.length());

    System.out.println(lengthOfLongestSubstring(s));
  }
}

