package com.example.demo.array;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2020/8/26 15:50
 * @description: 删除排序数组中的重复项
 * <p>
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次， 返回移除后数组的新长度 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1)
 * 额外空间的条件下完成
 * <p>
 * 示例 1: 给定数组 nums = [1,1,2] 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2
 * <p>
 * 示例 2: 给定 nums = [0,0,1,1,1,2,2,3,3,4], 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class DeleteRepeat {

  /**
   * @return void
   * @Author YJY
   * @Description 重点  有序的  不需要考虑数组中超出新长度那部分的数据是什么东西
   * @Date 2020/8/27
   * @Param [arr]
   **/
  public static void deleteRepeat(int[] arr) {

    int i = 0;
    for (int k = 1; k < arr.length; k++) {
      //因为是有序的 如果不等于就将 该元素放到比较元素的后面
      if (arr[i] != arr[k]) {

        i++;
        arr[i] = arr[k];
      }
    }

    System.out.println(Arrays.toString(arr));

  }

  public static void main(String[] args) {

    int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

    deleteRepeat(arr);
  }
}
