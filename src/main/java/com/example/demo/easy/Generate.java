package com.example.demo.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/4/13 15:37
 * @description: 杨辉三角
 */
public class Generate {

  public static List<List<Integer>> generate(int numRows) {

    List<List<Integer>> result = new ArrayList<>();

    if (numRows >= 1) {
      List<Integer> data = new ArrayList<>();
      data.add(1);
      result.add(data);

    }
    if (numRows >= 2) {
      List<Integer> data2 = new ArrayList<>();
      data2.add(1);
      data2.add(1);
      result.add(data2);

    }
    for (int i = 3; i <= numRows; i++) {
      List<Integer> data = new ArrayList<>();

      for (int j = 0; j < i; j++) {

        if (j == 0 || j == i - 1) {
          data.add(1);
        } else {
          data.add(result.get(i - 2).get(j - 1) + result.get(i - 2).get(j));
        }

      }

      result.add(data);
    }

    return result;
  }

  public static void main(String[] args) {

    System.out.println(generate(6));

  }
}
