package com.example.demo.array;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2021/7/20 14:54
 * @description: 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1： 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3 输出：[1,2,2,3,5,6]
 * <p>
 * 示例 2： 输入：nums1 = [1], m = 1, nums2 = [], n = 0 输出：[1]
 */
public class Merge {

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int ml = m - 1;
    int nl = n - 1;
    int all = m + n - 1;
    while (nl >= 0) {
      nums1[all--] = (ml >= 0 && nums1[ml] > nums2[nl]) ? nums1[ml--] : nums2[nl--];
    }

    System.out.println(Arrays.toString(nums1));

  }


  public static void main(String[] args) {

    int[] nums1 = {1, 2, 8, 0, 0, 0};
    int m = 3;
    int[] nums2 = {6, 7, 8};
    int n = 3;

    merge(nums1, m, nums2, n);
    //  mergeFinal(nums1, m, nums2, n);

  }

}

