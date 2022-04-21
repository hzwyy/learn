package com.example.demo.binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: YJY
 * @date: 2021/9/22 16:49
 * @description: 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target， 如果目标值存在返回下标，否则返回
 * -1。
 */
public class BinarySearch {

  public static int search(int[] nums, int target) {
    int length = nums.length - 1;
    return searchOne(nums, 0, length, target);

  }

  public static int searchOne(int[] nums, int min, int max, int target) {

    int mid = min + (max - min) / 2;

    if (min > max) {
      return -1;
    }

    if (nums[mid] == target) {
      return mid;
    }
    if (nums[mid] > target) {
      return searchOne(nums, min, mid - 1, target);
    }

    return searchOne(nums, mid + 1, max, target);

  }

  public static void main(String[] args) {

    int[] nums = {-1, 0, 3, 5, 9, 12};
    int target = -2;
    System.out.println(search(nums, target));
  }
}
