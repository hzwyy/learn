package com.example.demo.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/3/18 11:28
 * @description: 给定一个  无重复元素 的 有序 整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b "a" ，如果 a == b  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,4,5,7] 输出：["0->2","4->5","7"] 解释：区间范围是： [0,2] --> "0->2" [4,5] --> "4->5" [7,7]
 * --> "7" 示例 2：
 * <p>
 * 输入：nums = [0,2,3,4,6,8,9] 输出：["0","2->4","6","8->9"] 解释：区间范围是： [0,0] --> "0" [2,4] --> "2->4"
 * [6,6] --> "6" [8,9] --> "8->9"
 */
public class SummaryRanges {

  public static List<String> summaryRanges(int[] nums) {
    List<String> list = new ArrayList<>();
    for (int i = 0, j = 0; j < nums.length; j++) {
      if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) {
        continue;
      }
      if (i == j) {
        list.add(nums[i] + "");
      } else {
        list.add(nums[i] + "->" + nums[j]);
      }
      i = j + 1;
    }
    return list;
  }

  public static void main(String[] args) {

   int[]  nums = {0,2,3,4,6,8,9};

    System.out.println(summaryRanges(nums).toString());
  }
}
