package com.example.demo.array;

import com.example.demo.util.Swap;
import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2020/8/31 15:33
 * @description: 旋转数组 未完成 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 说明: 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class Rotate {


  public static void rotate(int[] arr, int move) {

    int length = arr.length;
    int indexValue = 0;
    int index = 0;
    for (int i = 0; i < length; i++) {
      //计算后的位置
      index = (i + move) % 7;

      System.out.println("计算出来的位置为" + index);
      Swap.swap(i, index, arr);
    }

    System.out.println(Arrays.toString(arr));

  }

  public static void main(String[] args) {

    int[] arr = {1, 2, 3, 4, 5, 6, 7};

    rotate(arr, 1);


  }
}
