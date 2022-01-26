package com.example.demo.easy;

import java.util.HashSet;

/**
 * @author: YJY
 * @date: 2022/1/25 15:26
 * @description: 编写一个算法来判断一个数 n 是不是快乐数。 「快乐数」定义为： 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。 然后重复这个过程直到这个数变为
 * 1，也可能是 无限循环 但始终变不到 1。 如果 可以变为  1，那么这个数就是快乐数。 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * <p>
 * 示例 1： 输入：n = 19 输出：true
 * <p>
 * 解释： 1^2 + 9^2 = 82 8^2 + 2^2 = 68 6^2 + 8^2 = 100 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 示例 2： 输入：n = 2 输出：false  
 * <p>
 * 提示： 1 <= n <= 231 - 1
 */
public class IsHappy {


  public static boolean isHappy(int n) {

    HashSet set = new HashSet();
    while (1 != n && !set.contains(n)) {
      set.add(n);
      n = getNext(n);

    }

    return n == 1;
  }

  public static int getNext(int n) {
    int total = 0;
    while (n > 0) {
      int d = n % 10;
      total += d * d;
      n = n / 10;
    }
    return total;
  }

  public static void main(String[] args) {

    System.out.println(isHappy(19));
  }
}
