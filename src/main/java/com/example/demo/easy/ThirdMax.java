package com.example.demo.easy;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2022/1/17 9:57
 * @description:
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 *
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 *
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 */
public class ThirdMax {

  public static int thirdMax(int[] nums) {
    Arrays.sort(nums);
    reverse(nums);
    for (int i = 1, diff = 1; i < nums.length; ++i) {
      // 此时 nums[i] 就是第三大的数
      if (nums[i] != nums[i - 1] && ++diff == 3) {
        return nums[i];
      }
    }
    return nums[0];
  }

  public static void reverse(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;
      left++;
      right--;
    }
  }


  public static void main(String[] args) {

    int[] num = {2, 2, 3, 1};

    System.out.println(thirdMax(num));

  }

}
