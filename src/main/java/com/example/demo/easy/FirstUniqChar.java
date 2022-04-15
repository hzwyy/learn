package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/4/14 16:43
 * @description: 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode" 输出: 0 示例 2:
 * <p>
 * 输入: s = "loveleetcode" 输出: 2 示例 3:
 * <p>
 * 输入: s = "aabb" 输出: -1
 */
public class FirstUniqChar {

  /**
   * @return int
   * @Author YJY
   * @Description
   * @Date 2022/4/14
   * @Param [s]
   **/
  public int firstUniqChar(String s) {

    int[] arr = new int[26];
    int n = s.length();
    for (int i = 0; i < n; i++) {

      arr[s.charAt(i) - 'a']++;
    }
    for (int nums = 0; nums < n; nums++) {

      if (arr[s.charAt(nums) - 'a'] == 1) {
        return nums;
      }
    }

    return -1;
  }

}
