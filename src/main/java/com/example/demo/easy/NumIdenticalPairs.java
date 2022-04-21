package com.example.demo.easy;

import java.util.HashMap;

/**
 * @author: YJY
 * @date: 2022/3/23 15:38
 * @description: 给你一个整数数组 nums 。
 * <p>
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 * <p>
 * 返回好数对的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1,1,3] 输出：4 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始 示例 2：
 * <p>
 * 输入：nums = [1,1,1,1] 输出：6 解释：数组中的每组数字都是好数对 示例 3：
 * <p>
 * 输入：nums = [1,2,3] 输出：0
 */
public class NumIdenticalPairs {

  /**
   * @return int
   * @Author YJY
   * @Description 仔细看对应结果 出现的次数和结果为 出现 1次 0 出现 2次 1 +1 出现三次 1 + 2 出现四次 1 + 2 + 3
   * @Date 2022/3/23
   * @Param [nums]
   **/
  public static int numIdenticalPairs(int[] nums) {

    HashMap<Integer, Integer> hashMap = new HashMap<>();
    int result = 0;
    for (int n : nums) {

      int a = hashMap.getOrDefault(n, 0);
      result += a;
      hashMap.put(n, ++a);

    }

    return result;
  }

  public static void main(String[] args) {

    int[] nums = {1, 1, 1, 1, 1};
    System.out.println(numIdenticalPairs(nums));
  }
}
