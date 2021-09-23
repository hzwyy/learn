package com.example.demo.binarySearch;

/**
 * @author: YJY
 * @date: 2021/9/23 9:41
 * @description: 你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API
 * 的次数。
 */
public class FirstBadVersion {

  public static int firstBadVersion(int n) {

    int min = 0;
    int max = n;

    while (min < max) {

      int mid = min + (max - min) / 2;

      if (isBadVersion(mid)) {

        max = mid;
      } else {

        min = mid + 1;
      }

    }
    return min;
  }


  public static boolean isBadVersion(int version) {
    if (version >= 4) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {

    System.out.println(firstBadVersion(5));

  }

}


