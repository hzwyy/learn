package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/25 11:36
 * @description: 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * <p>
 * 返回的长度需要从小到大排列。
 * <p>
 * 示例 1
 * <p>
 * 输入： shorter = 1 longer = 2 k = 3 输出： [3,4,5,6] 解释： 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次
 * longer，得到结果 4 。以此类推，得到最终结果。
 */
public class DivingBoard {

  /**
   * @return int[]
   * @Author YJY
   * @Description
   * @Date 2022/3/25
   * @Param [shorter, longer, k]
   **/
  public static int[] divingBoard(int shorter, int longer, int k) {

    if (k == 0) {
      return new int[0];
    }
    if (shorter == longer) {
      return new int[]{shorter * k};
    }
    int[] lengths = new int[k + 1];
    for (int i = 0; i <= k; i++) {
      lengths[i] = shorter * (k - i) + longer * i;
    }
    return lengths;
  }





  public static void main(String[] args) {


  }
}
