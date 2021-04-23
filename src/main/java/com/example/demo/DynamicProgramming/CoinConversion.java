package com.example.demo.DynamicProgramming;

/** 动态规划
 * @author: YJY
 * @date: 2021/4/21 10:36
 * @description:
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
 * (结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 *
 * 示例2:
 *
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 *
 */
public class CoinConversion {

  public static int waysToChange(int n) {
    int[] coins = new int[]{1, 5, 10, 25};
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int coin : coins) {
      for (int i = 1; i <= n; i++) {
        if (i - coin >= 0) {
          dp[i] = (dp[i] + dp[i - coin]);
        }
      }
    }
    return dp[n];
  }


  public static void main(String[] args) {

    System.out.println(waysToChange(10));
  }


}
