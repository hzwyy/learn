package com.example.demo.array;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/12/9 11:25
 * @description: 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1： 输入：[3,2,3] 输出：3
 * <p>
 * 示例 2： 输入：[2,2,1,1,1,2,2] 输出：2
 */
public class MajorityElement {

  /**
   * @return int
   * @Author YJY
   * @Description 摩尔投票法： 核心就是对拼消耗。 玩一个诸侯争霸的游戏，假设你方人口超过总人口一半以上，并且能保证每个人口出去干仗都能一对一同归于尽。最后还有人活下来的国家就是胜利。
   * 那就大混战呗，最差所有人都联合起来对付你（对应你每次选择作为计数器的数都是众数）， 或者其他国家也会相互攻击（会选择其他数作为计数器的数），但是只要你们不要内斗，最后肯定你赢。
   * 最后能剩下的必定是自己人。
   * @Date 2021/12/9
   * @Param [nums]
   **/
  public static int majorityElement(int[] nums) {

    int number = 0;
    int count = 0;
    for (int i = 0; i < nums.length; i++) {

      if (count == 0) {
        number = nums[i];
        count++;
        continue;
      }
      if (number == nums[i]) {
        count++;
      } else {
        count--;
      }
    }

    return number;
  }

  /**
   * @return int
   * @Author YJY
   * @Description 如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为 2/n的元素（下标从 0 开始）一定是众数。 因为这个数字占多半数 怎么排序
   * 下标2/n的数值 都肯定是他
   * @Date 2021/12/9
   * @Param [nums]
   **/
  public static int sortElement(int[] nums) {

    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  public static void main(String[] args) {

    int[] a = {1, 2, 3, 1, 1, 1};
    System.out.println(majorityElement(a));
    System.out.println(sortElement(a));
  }

}
