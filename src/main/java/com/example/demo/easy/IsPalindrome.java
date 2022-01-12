package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/1/7 10:30
 * @description: 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 */
public class IsPalindrome {

  /**
   * @return boolean
   * @Author YJY
   * @Description 解题思路 运用 除法(/)和取商(%)来计算 除法可以获取指定int前面的位数 取商可以从尾部获取指定位数的数字 比如12321 我想获取前两位
   * 12321/100即可获取12 而我想获取后两位 12321%100 即可获取21 然后 21 获取的时候可以依次获取(两次取余 %10) 然后 组装即可得到 12 前后相等即可或得题解
   * @Date 2022/1/7
   * @Param [x]
   **/
  public static boolean isPalindrome(int x) {

    // 特殊情况：
    // 如上所述，当 x < 0 时，x 不是回文数。
    // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
    // 则其第一位数字也应该是 0
    // 只有 0 满足这一属性
    if (x <= 0 || x % 10 == 0) {
      return false;
    }

    int reverseNumber = 0;
    while (x > reverseNumber) {

      reverseNumber = reverseNumber * 10;
      reverseNumber += x % 10;
      x = x / 10;
    }

    // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
    // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
    // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
    return x == reverseNumber || x == reverseNumber / 10;
  }

  public static void main(String[] args) {

    System.out.println(isPalindrome(121));
  }
}
