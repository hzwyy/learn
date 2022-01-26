package com.example.demo.easy;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2022/1/26 14:29
 * @description: 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1] 输出：2 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。 示例 2：
 * <p>
 * 输入：nums = [0,1] 输出：2 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。 示例 3：
 * <p>
 * 输入：nums = [9,6,4,2,3,5,7,0,1] 输出：8 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums
 * 中。 示例 4：
 * <p>
 * 输入：nums = [0] 输出：1 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 */
public class MissingNumber {

  /**
   * @Author YJY
   * @Description 排序  拍完顺序后 数组下标i 取出来的值对应的也应该是他排序后的位置
   * @Date  2022/1/26
   * @Param [nums]
   * @return int
   **/
  public int missingNumber1(int[] nums) {

    Arrays.sort(nums);
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (nums[i] != i) {
        return i;
      }
    }
    return n;
  }

  /**
   * @Author YJY
   * @Description 异或 找不同第一个想到的应该是这个方法
   * @Date  2022/1/26
   * @Param [nums]
   * @return int
   **/
  public int missingNumber2(int[] nums) {
    int xor = 0;
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      xor ^= nums[i];
    }
    for (int i = 0; i <= n; i++) {
      xor ^= i;
    }
    return xor;
  }


  /**
   * @Author YJY
   * @Description 相加求差 得出的结果即为少的数字
   * @Date  2022/1/26
   * @Param [nums]
   * @return int
   **/
  public int missingNumber(int[] nums) {
    int n = nums.length;
    int total = n * (n + 1) / 2;
    int arrSum = 0;
    for (int i = 0; i < n; i++) {
      arrSum += nums[i];
    }
    return total - arrSum;
  }

  public static void main(String[] args) {


    int [] a = {2,3,4,5,6};
    int total = a.length *(a.length + 1)/2;
    System.out.println(total);
  }

}