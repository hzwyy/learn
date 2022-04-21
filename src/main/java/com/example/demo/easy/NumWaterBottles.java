package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/24 17:42
 * @description: 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * <p>
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * <p>
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：numBottles = 9, numExchange = 3 输出：13 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：numBottles = 15, numExchange = 4 输出：19 解释：你可以用 4 个空酒瓶兑换 1 瓶酒。 所以最多能喝到 15 + 3 + 1 = 19 瓶酒。 示例
 * 3：
 * <p>
 * 输入：numBottles = 5, numExchange = 5 输出：6 示例 4：
 * <p>
 * 输入：numBottles = 2, numExchange = 3 输出：2
 */
public class NumWaterBottles {


  public static int numWaterBottles(int numBottles, int numExchange) {

    int bottle = numBottles, ans = numBottles;
    while (bottle >= numExchange) {
      bottle -= numExchange;
      ++ans;
      ++bottle;
    }
    return ans;

  }

  public static void main(String[] args) {

    System.out.println(numWaterBottles(5, 5));

  }
}
