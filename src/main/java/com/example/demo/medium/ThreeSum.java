package com.example.demo.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: YJY
 * @date: 2021/12/14 11:11
 * @description: 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ， 使得 a + b + c = 0 ？请你找出所有和为 0
 * 且不重复的三元组。 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1： 输入：nums = [-1,0,1,2,-1,-4] 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2： 输入：nums = [] 输出：[]
 * <p>
 * 示例 3： 输入：nums = [0] 输出：[]
 */
public class ThreeSum {

  /**
   * @Description 排序 + 双指针 官方代码
   **/
  public static List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    // 枚举 a
    for (int first = 0; first < n; ++first) {
      // 需要和上一次枚举的数不相同
      if (first > 0 && nums[first] == nums[first - 1]) {
        continue;
      }
      // c 对应的指针初始指向数组的最右端
      int third = n - 1;
      int target = -nums[first];
      // 枚举 b
      for (int second = first + 1; second < n; ++second) {
        // 需要和上一次枚举的数不相同
        if (second > first + 1 && nums[second] == nums[second - 1]) {
          continue;
        }
        // 需要保证 b 的指针在 c 的指针的左侧
        while (second < third && nums[second] + nums[third] > target) {
          --third;
        }
        // 如果指针重合，随着 b 后续的增加
        // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
        if (second == third) {
          break;
        }
        if (nums[second] + nums[third] == target) {
          List<Integer> list = new ArrayList<Integer>();
          list.add(nums[first]);
          list.add(nums[second]);
          list.add(nums[third]);
          ans.add(list);
        }
      }
    }
    return ans;

  }


  /**
   * @return java.util.List<java.util.List < java.lang.Integer>>
   * @Author YJY
   * @Description 排序 + 双指针
   * @Date 2021/12/14
   * @Param [nums]
   **/
  public static List<List<Integer>> myThreeSum(int[] nums) {

    List<List<Integer>> res = new ArrayList<>();
    int n = nums.length;
    Arrays.sort(nums);
    //三数之和 拆分为 一层循环+一层双指针
    for (int i = 0; i < nums.length; i++) {

      int target = -nums[i];
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      //最后的数组节点
      int three = n - 1;
      for (int second = i + 1; second < n - 1; ++second) {
        // 需要和上一次枚举的数不相同
        if (second > i + 1 && nums[second] == nums[second - 1]) {
          continue;
        }
        while (second < three && nums[second] + nums[three] > target) {
          //大于目标值 这里不用写 <target逻辑 应为 second ++ 本来就在外层for循环
          three--;
        }
        //执行过程中如果指针重合了 直接中断
        if (second == three) {
          break;
        }
        if (nums[second] + nums[three] == target) {
          List<Integer> list = new ArrayList<>();
          list.add(nums[i]);
          list.add(nums[second]);
          list.add(nums[three]);
          res.add(list);
        }

      }

    }
    return res;
  }


  public static void main(String[] args) {

    int[] nums = {-1, 0, 1, 2, -1, -4};

    System.out.println(myThreeSum(nums));

  }
}
