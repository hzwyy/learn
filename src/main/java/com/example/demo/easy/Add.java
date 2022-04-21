package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/25 10:23
 * @description: 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 * <p>
 * 示例:
 * <p>
 * 输入: a = 1, b = 1 输出: 2 提示：
 * <p>
 * a, b 均可能是负数或 0 结果不会溢出 32 位整数
 */
public class Add {


  public static int add(int a, int b) {
    //不进位加法
    int m = a ^ b;
    //进位
    int n = (a & b) << 1;
    while (n != 0) {
      int temp = n ^ m;

      m = temp;
    }
    return m;
  }

  public static void main(String[] args) {

    System.out.println(add(4, 5));
  }
}
