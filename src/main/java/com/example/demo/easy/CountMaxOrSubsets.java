package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/15 10:42
 * @description: 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * <p>
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * <p>
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1] 输出：2 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ： - [3] - [3,1] 示例 2：
 * <p>
 * 输入：nums = [2,2,2] 输出：7 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 23 - 1 = 7 个子集。 示例 3：
 * <p>
 * 输入：nums = [3,2,1,5] 输出：6 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ： - [3,5] - [3,1,5] - [3,2,5] -
 * [3,2,1,5] - [2,5] - [2,1,5]
 */
public class CountMaxOrSubsets {


  /**
   * @Author YJY
   * @Description 与运算:肯定参与运算的值越多数据越大
   * 所以简单理解就是 找子集与的结果和 数组整个集合与的结果 相等的集合
   * @Date  2022/3/15
   * @Param [nums]
   * @return int
   **/
  public static int countMaxOrSubsets(int[] nums) {

    //先得出最大值
    int maxNum = 0;
    for (int n : nums){

      maxNum = maxNum | n;
    }

    //找出子集里面和最大值相等的所有集合数量即为答案
    //1 << n 等于n*2  因为有 2n - 1中排列组合
    int ans = 0;
    int n = nums.length;
    for (int mask = 0; mask < (1 << n); mask++) {
      int or = 0;
      for (int i = 0; i < n; i++) {
        if ((mask >> i & 1) == 1) {
          or |= nums[i];
        }
      }
      if (or == maxNum) {
        ans++;
      }
    }

    return ans;
  }

  public static void main(String[] args) {

    int[] a = {1,2,3,4,5,6,78,1};
    System.out.println( countMaxOrSubsets(a));


  }

  }

