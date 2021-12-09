package com.example.demo.string;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/9/28 14:38
 * @description: 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "ab" s2 = "eidbaooo" 输出：true 解释：s2 包含 s1 的排列之一 ("ba"). 示例 2：
 * <p>
 * 输入：s1= "ab" s2 = "eidboaoo" 输出：false
 */
public class CheckInclusion {

  public static boolean checkInclusion(String s1, String s2) {
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    int len1 = s1.length();
    int len2 = s2.length();
    // 【总欠账表】：s1的词频表
    int[] charCount = new int[26];
    // 统计s1的词频
    for (char c : str1) {
      charCount[c - 'a']++;
    }
    // 滑动窗口左右边界
    int l = 0, r = 0;
    // 依次尝试固定以s2中的每一个位置l作为左端点开始的len1长度的子串s2[l ... l+len1)是否是s1的排列
    // 固定左端点只需要尝试到len2-len1即可
    while (l <= len2 - len1) {
      // 右边界s2[r]字符进入窗口【还账】
      while (r < l + len1 && charCount[str2[r] - 'a'] >= 1) {
        // 【"还账"】
        charCount[str2[r] - 'a']--;
        r++;
      }
      if (r == l + len1) {
        return true;
      }
      // 左边界s2[l]字符出窗口【赊账】，l++，开始尝试固定下一个位置做左端点
      // 重新【"赊账"】
      charCount[str2[l] - 'a']++;
      l++;
    }
    // 所有的左端点均尝试还账失败，不可能再有答案了
    return false;
  }


  /**
  * @Description 滑动窗口
  **/
  public static boolean checkInclusion2(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    if (n > m) {
      return false;
    }
    int[] cnt1 = new int[26];
    int[] cnt2 = new int[26];
    for (int i = 0; i < n; ++i) {
      ++cnt1[s1.charAt(i) - 'a'];
      ++cnt2[s2.charAt(i) - 'a'];
    }
    if (Arrays.equals(cnt1, cnt2)) {
      return true;
    }
    for (int i = n; i < m; ++i) {
      ++cnt2[s2.charAt(i) - 'a'];
      --cnt2[s2.charAt(i - n) - 'a'];
      if (Arrays.equals(cnt1, cnt2)) {
        return true;
      }
    }
    return false;
  }


  public static void main(String[] args) {

    System.out.println(checkInclusion2("ab","eidboaoo"));
    String a = "eee";
    System.out.println(a.charAt(1) - 'a');
  }

}
