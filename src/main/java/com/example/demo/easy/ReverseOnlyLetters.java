package com.example.demo.easy;

import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * @author: YJY
 * @date: 2022/2/23 11:23
 * @description: 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。 所有英文字母（小写或大写）位置反转。 返回反转后的 s 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab-cd" 输出："dc-ba" 示例 2：
 * <p>
 * 输入：s = "a-bC-dEf-ghIj" 输出："j-Ih-gfE-dCba" 示例 3：
 * <p>
 * 输入：s = "Test1ng-Leet=code-Q!" 输出："Qedo1ct-eeLg=ntse-T!"
 */
public class ReverseOnlyLetters {

  /**
   * @return java.lang.String
   * @Author YJY
   * @Description 双指针 思路与算法
   * <p>
   * 我们使用 left 指针从左边开始扫描字符串 s，right 指针从右边开始扫描字符串 s。如果两个指针都扫描到字母，且 left<right，那么交换 s[left] 和
   * s[right]，然后继续进行扫描；否则表明反转过程结束，返回处理后的字符串。
   * @Date 2022/2/23
   * @Param [s]
   **/
  public String reverseOnlyLetters(String s) {
    int n = s.length();
    char[] arr = s.toCharArray();
    int left = 0, right = n - 1;
    while (true) {
      // 判断左边是否扫描到字母
      while (left < right && !Character.isLetter(s.charAt(left))) {
        left++;
      }
      // 判断右边是否扫描到字母
      while (right > left && !Character.isLetter(s.charAt(right))) {
        right--;
      }
      if (left >= right) {
        break;
      }
      swap(arr, left, right);
      left++;
      right--;
    }
    return new String(arr);
  }

}
