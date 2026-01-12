package com.example.demo.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author: YJY
 * @date: 2022/4/25 17:42
 * @description: RandomIndex的另一个解法 水塘抽样解法
 */
public class RandomIndexSampling {

  int[] nums;
  Random random;

  /**
   * @return
   * @Author YJY
   * @Description 余额
   * @Date 2022/4/25
   * @Param [nums]
   **/
  public RandomIndexSampling(int[] nums) {
    this.nums = nums;
    random = new Random();
  }

  public int pickByHash(int target) {
    int pond = 0;
    int i = 0;
    for (int n : nums) {
      if (n == target) {
        ++pond;
        if (random.nextInt(pond) == 0) {
          return i;
        }

      }
      i++;
    }
    return i;
  }
}
