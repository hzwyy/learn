package com.example.demo.array;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/8/23 14:34
 * @description: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 如果数组中不存在目标值target，返回 [-1,
 * -1]。 进阶： 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？   示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8 输出：[3,4]
 */
public class SearchRange {

  public static int[] searchRange(int[] nums, int target) {
    int[] res = new int[] {-1, -1};
    res[0] = binarySearch(nums, target, true);
    res[1] = binarySearch(nums, target, false);
    return res;
  }
  //leftOrRight为true找左边界 false找右边界
  public static int binarySearch(int[] nums, int target, boolean leftOrRight) {
    int res = -1;
    int left = 0, right = nums.length - 1, mid;
    while(left <= right) {
      mid = left + (right - left) / 2;
      if(target < nums[mid])
        right = mid - 1;
      else if(target > nums[mid])
        left = mid + 1;
      else {
        res = mid;
        //处理target == nums[mid]
        if(leftOrRight)
          right = mid - 1;
        else
          left = mid + 1;
      }
    }
    return res;
  }

  public static void main(String[] args) {

     int nums[] = {5, 7, 7, 8, 8, 10};

    System.out.println(Arrays.toString(searchRange(nums, 8)));
  }

}
