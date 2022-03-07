package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/3 15:04
 * @description: 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 38 输出: 2 解释: 各位相加的过程为： 38 --> 3 + 8 --> 11 11 --> 1 + 1 --> 2 由于 2 是一位数，所以返回 2。 示例 1:
 * <p>
 * 输入: num = 0 输出: 0
 */
public class AddDigits {

  /**
   * @return int
   * @Author YJY
   * @Description 循环
   * @Date 2022/3/3
   * @Param [num]
   **/
  public static int addDigits(int num) {

    while (num >= 10) {
      int sum = 0;
      while (num > 0) {
        //获取数字的个位数
        sum += num % 10;
        //抹去数字的个位数
        num = num / 10;
      }
      num = sum;

    }

    return num;
  }


  /**
   * @Author YJY
   * @Description 数学推导
   * @Date  2022/3/3
   * @Param [num]
   * @return int
   **/
  public int addDigits1(int num) {
    return (num - 1) % 9 + 1;
  }

  public static void main(String[] args) {

    System.out.println(addDigits(999999534));
  }

}
