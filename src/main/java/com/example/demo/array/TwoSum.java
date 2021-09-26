package com.example.demo.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: YJY
 * @date: 2021/8/24 10:00
 * @description: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */
public class TwoSum {


  /**
   * @return int[]
   * @Author YJY
   * @Description 简单版 利用hashMap 双重循环
   * @Date 2021/9/26
   * @Param [numbers, target]
   **/
  public static int[] twoSumEasy(int[] numbers, int target) {

    int[] two = new int[2];
    HashMap<Integer, Integer> hashMap = new HashMap();

    for (int i = 0; i < numbers.length; i++) {

      for (int v : hashMap.keySet()) {
        if (target == numbers[i] + v) {
          two[0] = hashMap.get(v);
          two[1] = i;
          return two;
        }
      }
      hashMap.put(numbers[i], i);
    }

    return two;
  }

  /**
   * @return int[]
   * @Author YJY
   * @Description 进阶版 利用hashMap 一次循环
   * @Date 2021/9/26
   * @Param [nums, target]
   **/
  public static int[] twoSum(int[] nums, int target) {

    int result[] = new int[2];

    HashMap<Integer, Integer> hashMap = new HashMap();
    if (null == nums || nums.length < 2) {
      return nums;
    }

    for (int i = 0; i < nums.length; i++) {

      //先判断再放值 否则 [3,3]这种情况会出错
      if (hashMap.containsKey(target - nums[i])) {
        result[0] = i;
        result[1] = hashMap.get(target - nums[i]);
      }
      hashMap.put(nums[i], i);
    }
    return result;
  }

  /**
   * @return int[]
   * @Author YJY
   * @Description 不利用其他空间--双指针
   * @Date 2021/9/26
   * @Param [nums, target]
   **/
  public static int[] twoSumPointer(int[] nums, int target) {

    int result[] = new int[2];

    int i = 0;
    int j = nums.length - 1;

    for (; i < j; ) {

      if(nums[i]+nums[j] == target){

        result[0] = i;
        result[1] = j;
        return result;
      }

      if(nums[i]+nums[j] > target){
        j --;
      }
      if(nums[i]+nums[j] < target){
        i ++;
      }
    }
    return result;
    }

    public static void main (String[]args){

      int nums[] = {2,7,11,15};

      System.out.println(Arrays.toString(twoSumPointer(nums, 9)));
    }
  }
