package com.example.demo.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/2/15 11:25
 * @description: 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * <p>
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * <p>
 * 在同一行的所有元素中最小 在同一列的所有元素中最大  
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]] 输出：[15] 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。 示例 2：
 * <p>
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]] 输出：[12] 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 * <p>
 * 输入：matrix = [[7,8],[1,2]] 输出：[7]
 */
public class LuckyNumbers {

  public static List<Integer> luckyNumbers (int[][] matrix) {

    int m = matrix.length, n = matrix[0].length;
    int[] minRow = new int[m];
    Arrays.fill(minRow, Integer.MAX_VALUE);
    int[] maxCol = new int[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        minRow[i] = Math.min(minRow[i], matrix[i][j]);
        maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
      }
    }
    List<Integer> ret = new ArrayList<Integer>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
          ret.add(matrix[i][j]);
        }
      }
    }
    return ret;

  }

  public static void main(String[] args) {

    int[][] nums = {{1,10,4,2},{9,3,8,7},{15,16,17,12}};
    System.out.println(luckyNumbers(nums));

  }
}
