package com.example.demo.medium;

import com.example.demo.util.Swap;
import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/12/14 15:07
 * @description: 给定一个包含红色、白色和蓝色，一共 n 个元素的数组， 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 此题中，我们使用整数 0、 1
 * 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 示例 1： 输入：nums = [2,0,2,1,1,0] 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2： 输入：nums = [2,0,1] 输出：[0,1,2]
 * <p>
 * 示例 3： 输入：nums = [0] 输出：[0]
 * <p>
 * 示例 4： 输入：nums = [1] 输出：[1]
 */
public class SortColors {

  /**
   * @return void
   * @Author YJY
   * @Description 双重循环 还有一种简单的 直接循环一遍数组  统计出一共几个0 几个1 几个2  然后重组 但是不符合题目要求
   * @Date 2021/12/14
   * @Param [nums]
   **/
  public static void sortColors(int[] nums) {
    if (null == nums || nums.length <= 1) {
      return;
    }

    for (int i = 0; i < nums.length; i++) {

      int index = i;
      for (int j = i; j < nums.length; j++) {
        if (nums[index] > nums[j]) {
          index = j;
        }
      }
      if (i != index) {
        Swap.swap(i, index, nums);
      }
    }
  }

  /**
   * @return void
   * @Author YJY
   * @Description 指针
   * @Date 2021/12/14
   * @Param [nums]
   **/
  public static void sortColorsByPointer(int[] nums) {

  }

  public static void main(String[] args) {

    int[] nums = {2, 0, 2, 1, 1, 0};
    sortColors(nums);

    System.out.println(Arrays.toString(nums));
  }

}
