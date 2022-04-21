package com.example.demo.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/2/24 10:04
 * @description: 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 */
public class IsPowerOfTwo {


  public static List<String> summaryRanges(int[] nums) {
    List<String> ret = new ArrayList<String>();
    int i = 0;
    int n = nums.length;
    while (i < n) {
      int low = i;
      i++;
      while (i < n && nums[i] == nums[i - 1] + 1) {
        i++;
      }
      int high = i - 1;
      StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
      if (low < high) {
        temp.append("->");
        temp.append(nums[high]);
      }
      ret.add(temp.toString());
    }
    return ret;

  }


  public static void main(String[] args) {
    int[] a = {0, 1, 2, 4, 5, 7};
    System.out.println((a));
  }
}
