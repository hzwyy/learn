package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/7 11:39
 * @description:
 */
public class ConvertToBase7 {


  public static String convertToBase7(int num) {

    StringBuilder s = new StringBuilder();
    if (num == 0) {
      return "0";
    }
    boolean m = num < 0;
    num = Math.abs(num);

    while (num > 0) {

      s.append(num % 7);
      num = num / 7;
    }

    if (m) {
      s.append("-");
    }
    return s.reverse().toString();
  }

  public static void main(String[] args) {

    System.out.println(convertToBase7(7));
  }
}
