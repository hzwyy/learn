package com.example.demo.util;

/**
 * @author: YJY
 * @date: 2020/8/11 11:15
 * @description:
 */
public class Swap {

  /**
   * 交换
   *
   * @param swapA
   * @param swapB
   * @param arr
   */
  public static void swap(int swapA, int swapB, int[] arr) {

    int temp = arr[swapA];
    arr[swapA] = arr[swapB];
    arr[swapB] = temp;
  }

  /**
   * 交换
   *
   * @param swapA
   * @param swapB
   * @param arr
   */
  public static void swapString(int swapA, int swapB, String[] arr) {

    String temp = arr[swapA];
    arr[swapA] = arr[swapB];
    arr[swapB] = temp;
  }

}
