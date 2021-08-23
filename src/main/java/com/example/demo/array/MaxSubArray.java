package com.example.demo.array;

/**
 * @author: YJY
 * @date: 2021/8/23 11:19
 * @description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例 1： 输入：nums = [-2,1,-3,4,-1,2,1,-5,4] 输出：6 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
 * <p>
 * 示例 2： 输入：nums = [1] 输出：1
 */
public class MaxSubArray {

  /**
   * @return int
   * @Author YJY
   * @Description 不太好理解
   * @Date 2021/8/23
   * @Param [nums]
   **/
  public static int maxSubArray(int[] nums) {
    int pre = 0, maxAns = nums[0];
    for (int x : nums) {
      pre = Math.max(pre + x, x);
      maxAns = Math.max(maxAns, pre);
    }
    return maxAns;
  }

  /**
   * @return int
   * @Author YJY
   * @Description 从第一个开始循环累加 如果相加结果为负数 直接清零 向后累加 如果累加结果为正数 将合和 maxSum做比较存入最大值。依次循环下去即可
   * @Date 2021/8/23
   * @Param [nums]
   **/
  public static int maxSubArray2(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    int maxSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];
      if (dp[i - 1] > 0) {
        dp[i] = dp[i - 1] + num;
      } else {
        dp[i] = num;
      }
      maxSum = Math.max(maxSum, dp[i]);
    }
    return maxSum;
  }

  public static void maxSubArrayEasy(int[] arr) {
    //定位第一个数字
    int par = arr[0];
    //先默认第一个最大
    int maxNum = arr[0];

    for (int i = 1; i < arr.length; i++) {

      //如果大于0 和后面的相加才会更大 所以小于0 直接赋值不累加
      if (par > 0) {
        par = arr[i] + par;
      } else {
        par = arr[i];
      }

      maxNum = Math.max(maxNum, par);
    }
    System.out.println(maxNum);
  }

  public static void main(String[] args) {

    int arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(maxSubArray2(arr));
    maxSubArrayEasy(arr);
  }
}
