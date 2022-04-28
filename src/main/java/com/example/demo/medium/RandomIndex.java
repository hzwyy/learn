package com.example.demo.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author: YJY
 * @date: 2022/4/25 16:24
 * @description: 给你一个可能含有 重复元素 的整数数组 nums ，请你随机输出给定的目标数字 target 的索引。你可以假设给定的数字一定存在于数组中。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution(int[] nums) 用数组 nums 初始化对象。 int pick(int target) 从 nums 中选出一个满足 nums[i] == target 的随机索引
 * i 。如果存在多个有效的索引，则每个索引的返回概率应当相等。  
 * <p>
 * 示例：
 * <p>
 * 输入 ["Solution", "pick", "pick", "pick"] [[[1, 2, 3, 3, 3]], [3], [1], [3]] 输出 [null, 4, 0, 2]
 * <p>
 * 解释 Solution solution = new Solution([1, 2, 3, 3, 3]); solution.pick(3); // 随机返回索引 2, 3 或者 4
 * 之一。每个索引的返回概率应该相等。 solution.pick(1); // 返回 0 。因为只有 nums[0] 等于 1 。 solution.pick(3); // 随机返回索引 2, 3
 * 或者 4 之一。每个索引的返回概率应该相等。  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104 -231 <= nums[i] <= 231 - 1 target 是 nums 中的一个整数 最多调用 pick 函数 104 次  
 */
public class RandomIndex {

  Random random = new Random();
  HashMap<Integer, List<Integer>> hashMap = new HashMap<>();

  /**
   * @return
   * @Author YJY
   * @Description 余额
   * @Date 2022/4/25
   * @Param [nums]
   **/
  public  RandomIndex(int[] nums) {
    int index = 0;
    for (int n : nums) {
      hashMap.putIfAbsent(n, new ArrayList<>());
      hashMap.get(n).add(index);
      index++;
    }
  }

  public  int pickByHash(int target) {
   return  hashMap.get(target).get(random.nextInt(hashMap.get(target).size()));
  }


}
