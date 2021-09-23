package com.example.demo.array;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/9/23 14:12
 * @description: 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100] 解释：平方后，数组变为 [16,1,0,9,100] 排序后，数组变为 [0,1,9,16,100] 示例
 * 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
 */
public class SortedSquares {


  public static int[] sortedSquares(int[] nums) {

    int[] ans = new int[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      ans[i] = nums[i] * nums[i];
    }
    Arrays.sort(ans);
    return ans;
  }

  /**
   * @return int[]
   * @Author YJY
   * @Description 双指针
   * @Date 2021/9/23
   * @Param [nums]
   **/
  public static int[] sortedSquaresTwo(int[] nums) {

    int l = nums.length;
    int[] post = new int[l];
    int p = nums.length-1;
    for (int i = 0, j = l - 1; i <= j;) {

      if (nums[i]*nums[i] < nums[j]* nums[j]) {
        post[p] = nums[j]* nums[j];
        j--;
      } else {
        post[p] = nums[i]* nums[i];
        i++;
      }
      p--;
    }

    return post;
  }

  public static void main(String[] args) {

    int[] nums = {-4, -1, 0, 3, 10};
    System.out.println(Arrays.toString(sortedSquaresTwo(nums)));
  }

}
