package com.example.demo.array;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/9/24 16:47
 * @description: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 示例: 输入: [0,1,0,3,12] 输出:
 * [1,3,12,0,0]
 * <p>
 * 说明: 必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
 */
public class MoveZeroes {


  public static void moveZeroes(int[] nums) {

    int index = 0;
    for (int num : nums) {

      if (num != 0) {
        nums[index] = num;
        index++;
      }
    }

    for (int i = index; i < nums.length; i++) {

      nums[i] = 0;
    }

  }

  public static void main(String[] args) {

    int[] nums = {0, 1, 0, 3, 12};
    moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }

}
