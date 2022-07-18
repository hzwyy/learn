package com.example.demo.sort.bubbleSort;

import static com.example.demo.util.Swap.swap;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2020/8/11 11:13
 * @description: 冒泡
 */
public class BubbleSort {


  public static void bubbleSort(int[] arr) {

    /**
     * @Description 外层循环确定排序位置
     **/
    for (int i = 0; i < arr.length; i++) {
      /**
       * @Description 外层循环每循环完一次调准基准值
       **/
      int max = arr[i];
      int indexSwap = i;

      /**
       * @Description 将i后面的数组值依次进行判断 将最大提位到最前面
       **/
      for (int j = i; j < arr.length; j++) {

        if (arr[j] > max) {
          max = arr[j];
          indexSwap = j;
        }
      }
      if (indexSwap != i) {
        swap(i, indexSwap, arr);
      }
    }
  }


  public static void main(String[] args) {

    int[] arr = {2, 4, 5, 1, 9, 7, 8, 6};

    bubbleSort(arr);
    System.out.println(Arrays.toString(arr));
  }

}
