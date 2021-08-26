package com.example.demo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: YJY
 * @date: 2021/8/26 9:48
 * @description: 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2] 输出：[2,2] 输入 nums1 =[1,2],  nums2 = [1,1] 输出 [1]
 */
public class Intersect {


  public static int[] intersect(int[] nums1, int[] nums2) {

    if (nums2.length > nums1.length) {
      return intersect(nums2, nums1);

    }

    HashMap<Integer, Integer> hashMap = new HashMap();
    int[] intersection = new int[nums1.length];
    for (int v : nums1) {
      //需要计算数字出现的次数 和数组2相同一个 就要去掉一个
      int count = hashMap.getOrDefault(v, 0) + 1;
      hashMap.put(v, count);
    }
    int index = 0;
    for (int t : nums2) {
      //判断nums1是否存在此数值
      int count = hashMap.getOrDefault(t, 0);

      if (count > 0) {
        intersection[index++] = t;
        count--;
      }
      //比较一次相同了 则减一次 如果自减后数值还大于0则更新count 如果等于0 则表示这个数值没有比较次数了 remove
      if (count > 0) {
        hashMap.put(t, count);
      } else {
        hashMap.remove(t);
      }

    }

    return Arrays.copyOfRange(intersection, 0, index);
  }

  public static void main(String[] args) {
    int nums2[] = {1, 2};
    int nums1[] = {1,1};
    System.out.println(Arrays.toString(intersect(nums1, nums2)));

  }
}
