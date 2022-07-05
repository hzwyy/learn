package com.example.demo.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/7/4 16:58
 * @description: 给你个整数数组 arr，其中每个元素都 不相同。
 * <p>
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [4,2,1,3] 输出：[[1,2],[2,3],[3,4]] 示例 2：
 * <p>
 * 输入：arr = [1,3,6,10,15] 输出：[[1,3]] 示例 3：
 * <p>
 * 输入：arr = [3,8,-10,23,19,-4,-14,27] 输出：[[-14,-10],[19,23],[23,27]]
 */
public class MinimumAbsDifference {

  public static List<List<Integer>> minimumAbsDifference(int[] arr) {

    int minValue = Integer.MAX_VALUE;
    List<List<Integer>> lists = new ArrayList<>();
    int n = arr.length;
    Arrays.sort(arr);
    for (int i = 0; i + 1 < n; i++) {

      if (arr[i + 1] - arr[i] < minValue) {
        lists.clear();
        minValue = arr[i + 1] - arr[i];
        List<Integer> list = new ArrayList<>();
        list.add(arr[i]);
        list.add(arr[i + 1]);
        lists.add(list);
      } else if (arr[i + 1] - arr[i] == minValue) {
        List<Integer> list = new ArrayList<>();
        list.add(arr[i]);
        list.add(arr[i + 1]);
        lists.add(list);
      }
    }
    return lists;
  }

  public static void main(String[] args) {

    int[] arr = {3,8,-10,23,19,-4,-14,27};
    System.out.println(minimumAbsDifference(arr).toString());
  }
}
