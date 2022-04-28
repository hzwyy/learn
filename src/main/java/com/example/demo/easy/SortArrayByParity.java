package com.example.demo.easy;

import com.example.demo.util.Swap;
import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2022/4/28 10:00
 * @description:
 */
public class SortArrayByParity {

  /**
   * @Author YJY
   * @Description 原地交换 空间少 时间慢
   * @Date  2022/4/28
   * @Param [nums]
   * @return int[]
   **/
  public static int[] sortArrayByParity(int[] nums) {

    int j = -1;
    for (int i = 0; i < nums.length; i++) {

      //奇数
      if (nums[i] % 2 != 0) {
        if (j == -1) {
          j = i;
        }
      }
      //找到偶数了
      if (nums[i] % 2 == 0) {
        if (j != -1) {
          Swap.swap(i, j, nums);
          i = j;
          j = -1;

        }
      }
    }

    return nums;
  }
  /**
   * @Author YJY
   * @Description 双指针 时间快 需要空间
   * @Date  2022/4/28
   * @Param [nums]
   * @return int[]
   **/
  public int[] sortArrayByParity2(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    int left = 0, right = n - 1;
    for (int num : nums) {
      if (num % 2 == 0) {
        res[left++] = num;
      } else {
        res[right--] = num;
      }
    }
    return res;
  }



  public static void main(String[] args) {
    int[] nums = {1,2,3,4,5,6,7,8,9};
    System.out.println(Arrays.toString(sortArrayByParity(nums)));
  }
}
