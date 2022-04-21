package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/7 14:57
 * @description: 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * <p>
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * <p>
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * <p>
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicates {

  /**
   * @return int
   * @Author YJY
   * @Description 双指针
   * @Date 2022/3/7
   * @Param [nums]
   **/
  public static int removeDuplicates(int[] nums) {

    int j = 1;
    int i = 0;
    int n = 1;
    while (j < nums.length) {

      if (nums[i] == nums[j]) {
        j++;
      } else {
        i++;
        n++;
        nums[i] = nums[j];
        j++;
      }

    }

    return n;
  }

  /**
   * @return int
   * @Author YJY
   * @Description 双指针优化
   * @Date 2022/3/7
   * @Param [nums]
   **/
  public static int removeDuplicates1(int[] nums) {

    int j = 1;
    int i = 0;
    while (j < nums.length) {

      if (nums[i] != nums[j]) {
        nums[++i] = nums[j];
      }
      j++;
    }

    return i + 1;
  }


  public static void main(String[] args) {

    int[] num = {0, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6};
    System.out.println(removeDuplicates(num));
  }
}
