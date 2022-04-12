package com.example.demo.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/4/11 10:30
 * @description: 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。
 * 可以不考虑输出结果的顺序。
 * <p>
 * 示例 1： 输入：nums1 = [1,2,2,1], nums2 = [2,2] 输出：[2,2] 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出：[4,9]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000 0 <= nums1[i], nums2[i] <= 1000  
 * <p>
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？ 如果 nums1 的大小比 nums2 小，哪种方法更优？ 如果 nums2 的元素存储在磁盘上，内存是有限的，
 * 并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Intersect {

  public static int[] intersect(int[] nums1, int[] nums2) {
    //精髓 只需要先统计最少的那个数组即可
    if (nums1.length > nums2.length) {
      return intersect(nums2, nums1);
    }
    int[] intersection = new int[nums1.length];
    int index = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int n : nums1) {
      int count = map.getOrDefault(n, 0) + 1;
      map.put(n, count);
    }
    for (int n : nums2) {
      int count = map.getOrDefault(n, 0);
      if (count > 0) {
        count--;
        map.put(n, count);
        intersection[index++] = n;
      } else {
        map.remove(n);
      }
    }
    return Arrays.copyOfRange(intersection, 0, index);
  }

  public static void main(String[] args) {

    int[] nums1 = {1, 2, 2, 1};
    int[] nums2 = {2, 2};
    System.out.println(Arrays.toString(intersect(nums1, nums2)));

  }
}
