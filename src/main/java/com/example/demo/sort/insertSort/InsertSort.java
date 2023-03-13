package com.example.demo.sort.insertSort;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2022/7/26 15:57
 * @description: 插入排序
 */
public class InsertSort {


  public static void insertSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        } else {
          break;
        }
      }
    }
    System.out.println(Arrays.toString(arr));
  }


  public static void main(String[] args) {

    int[] arr = {2,0,2,1,1,0};
    insertSort(arr);
  }
}
