package com.example.demo;

import static com.example.demo.bubbleSort.BubbleSort.bubbleSort;
import static com.example.demo.quickSort.QuickSort.quickSort;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2020/8/11 11:24
 * @description: 测试类
 */
public class Test {



  public static void main(String[] args) {

    int arr[] = {5, 1, 3, 2, 10, 4, 7, 6, 9, 8};
    /**
    * @Description 快排
    **/
    int rightIndex = arr.length - 1;
    int leftIndex = 0;
    quickSort(leftIndex, rightIndex, arr);
    System.out.println(Arrays.toString(arr));
    /**
     * @Description 冒泡
     **/
    bubbleSort(arr);
    System.out.println(Arrays.toString(arr));

  }
}
