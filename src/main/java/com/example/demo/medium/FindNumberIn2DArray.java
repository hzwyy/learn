package com.example.demo.medium;

/**
 * @author: YJY
 * @date: 2022/2/16 10:01
 * @description: 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [ [1,   4,  7, 11, 15], [2,   5,  8, 12, 19], [3,   6,  9, 16, 22], [10, 13, 14, 17, 24], [18,
 * 21, 23, 26, 30] ] 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 */
public class FindNumberIn2DArray {

  /**
   * @return boolean
   * @Author YJY
   * @Description 最难的是 本题要从数组的右上角去思考题目 也就是不要从都是小的哪一方去看 由于给定的二维数组具备每行从左到右递增以及每列从上到下递增的特点，当访问到一个元素时，可以排除数组中的部分元素。
   * <p>
   * 从二维数组的右上角开始查找。如果当前元素等于目标值，则返回 true。如果当前元素大于目标值，则移到左边一列。如果当前元素小于目标值，则移到下边一行。
   * <p>
   * 可以证明这种方法不会错过目标值。如果当前元素大于目标值，说明当前元素的下边的所有元素都一定大于目标值，因此往下查找不可能找到目标值，往左查找可能找到目标值。如果当前元素小于目标值，说明当前元素的左边的所有元素都一定小于目标值，因此往左查找不可能找到目标值，往下查找可能找到目标值。
   * <p>
   * 若数组为空，返回 false 初始化行下标为 0，列下标为二维数组的列数减 1 重复下列步骤，直到行下标或列下标超出边界 获得当前下标位置的元素 num 如果 num 和 target
   * 相等，返回 true 如果 num 大于 target，列下标减 1 如果 num 小于 target，行下标加 1 循环体执行完毕仍未找到元素等于 target
   * ，说明不存在这样的元素，返回 false`
   * @Date 2022/2/16
   * @Param [matrix, target]
   **/
  public static boolean findNumberIn2DArray(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    int totalRow = matrix.length - 1;
    int totalColumn = matrix[0].length - 1;

    int row = 0;
    while (row <= totalRow && totalColumn >= 0) {

      if (matrix[row][totalColumn] == target) {
        return true;
      } else if (matrix[row][totalColumn] > target) {
        totalColumn--;
      } else if (matrix[row][totalColumn] < target) {
        row++;
      }
    }
    return false;
  }

  public static void main(String[] args) {

    int[][] nums = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
        {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

    System.out.println(findNumberIn2DArray(nums, 31));
  }
}
