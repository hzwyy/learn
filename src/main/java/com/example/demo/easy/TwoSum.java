package com.example.demo.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: YJY
 * @date: 2022/4/8 16:29
 * @description: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6 输出：[1,2] 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6 输出：[0,1]
 */
public class TwoSum {

  public static int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];
    HashMap<Integer, Integer> map = new HashMap(nums.length);
    int i = 0;
    for (int n : nums) {

      if (map.containsKey(target - n)) {

        result[0] = i;
        result[1] = map.get(target - n);
        return result;
      }
      map.put(n, i);
      i++;
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {3,3};

    System.out.println(Arrays.toString(twoSum(nums,6)));

  }
}
