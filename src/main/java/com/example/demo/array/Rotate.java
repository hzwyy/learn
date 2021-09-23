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


  /**
  * @Description 需要使用额外的数组
  **/
  public static void rotate(int[] arr, int move) {

    int length = arr.length;
    int index = 0;
    int[] nArr = new int[length];

    for (int i = 0; i < length; i++) {
      //计算后的位置
      index = (i + move) % length;
      System.out.println(index);
      nArr[index] = arr[i];
    }

    System.out.println(Arrays.toString(nArr));

  }

  /**
   * @Description 不需要使用额外的数组
   **/
  public static void rotate2(int[] nums, int k) {

    int len = nums.length,n = len;
    int i = 0,pos = 0, pre = nums[pos],temp = nums[pos];

    if(k%n == 0) return;

    while (n-- != 0) {
      pos =  (pos + k) % len;
      temp = nums[pos];
      nums[pos] = pre;
      pre = temp;
      if (pos == i) {
        pos = ++i;
        pre = nums[pos];
        temp = nums[pos];
      }
    }

    System.out.println(Arrays.toString(nums));

    }



  public static void main(String[] args) {

    int[] arr = {-1,-100,3,99};

    rotate2(arr, 2);


  }
}
