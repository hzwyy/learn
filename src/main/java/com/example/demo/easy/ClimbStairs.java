package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/1/24 15:09
 * @description: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2 输出：2 解释：有两种方法可以爬到楼顶。 1. 1 阶 + 1 阶 2. 2 阶 示例 2：
 * <p>
 * 输入：n = 3 输出：3 解释：有三种方法可以爬到楼顶。 1. 1 阶 + 1 阶 + 1 阶 2. 1 阶 + 2 阶 3. 2 阶 + 1 阶  
 * <p>
 * 提示： 1 <= n <= 45
 */
public class ClimbStairs {

  public static int climbStairs(int n) {

    int n2 = 0;
    int n1 = 0;
    int r = 1;

    for (int i = 1; i <= n; i++) {

      n2 = n1;
      n1 = r;
      r = n1 + n2;
    }

    return r;
  }

  public static void main(String[] args) {

    System.out.println(climbStairs(4));

  }
}
