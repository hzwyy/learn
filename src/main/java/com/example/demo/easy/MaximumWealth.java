package com.example.demo.easy;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2022/4/14 11:00
 * @description:
 */
public class MaximumWealth {

  public int maximumWealth(int[][] accounts) {
    int maxValue = Integer.MIN_VALUE;
    for (int[] nums : accounts) {

      maxValue = Math.max(maxValue, Arrays.stream(nums).sum());

    }
    return maxValue;
  }
}
