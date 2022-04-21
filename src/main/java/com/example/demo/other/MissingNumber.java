package com.example.demo.other;

/**
 * @author: YJY
 * @date: 2021/7/20 16:33
 * @description: 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 * 进阶： 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?   示例 1： 输入：nums = [3,0,1] 输出：2 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围
 * [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 示例 2： 输入：nums = [0,1] 输出：2 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 示例 3： 输入：nums = [9,6,4,2,3,5,7,0,1] 输出：8 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在
 * nums 中。
 * <p>
 * 示例 4： 输入：nums = [0] 输出：1 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。  
 * <p>
 * 提示： n == nums.length 1 <= n <= 104 0 <= nums[i] <= n nums 中的所有数字都 独一无二
 */
public class MissingNumber {


  /**
   * @Description 根据异或原理操作
   **/
  public static void missing(int[] arr) {

    int result = 0;
    //异或操作
    for (int i = 0; i < arr.length; i++) {

      result ^= (i + 1) ^ arr[i];

    }

    System.out.println(result);
  }


  /**
   * @Description 求和 如果不缺那个数字的话，这个数组的所有数字可以组成一个等差数列， 我们只需要根据公式求和，然后再减去数组中所有的数字即可，代码如下
   **/
  public static int missingNumber(int[] nums) {
    int length = nums.length;
    int sum = (0 + length) * (length + 1) / 2;
    for (int i = 0; i < length; i++) {
      sum -= nums[i];
    }
    return sum;
  }

  public static void main(String[] args) {

    int[] arr = {0, 1, 2, 3};
    missing(arr);
    System.out.println(missingNumber(arr));
  }
}
