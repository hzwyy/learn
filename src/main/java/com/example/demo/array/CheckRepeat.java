package com.example.demo.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import org.springframework.util.ObjectUtils;

/**
 * @author: YJY 判断数组是否存在重复元素
 * @date: 2020/9/3 16:50
 * @description: 给定一个整数数组，判断是否存在重复元素。 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false
 */
public class CheckRepeat {


  /**
   * @return void
   * @Author YJY
   * @Description 利用hashMap 特性去做 方便快捷 利用数组的值做 key. 如果key之前不存在 hashMap 会直接放入value 并没有返回值. 第一次 a =
   * hashMap.put(arr[i],"1");  a这个时候等于 null 如果此key 已经存放过值 会保存第二次存放的值并返回第一次存的值. 第二次    a =
   * hashMap.put(arr[i],"1");  a这个时候等于 1 这样就可以判断在hashmap中这个key是第一次存入。即可判断数组中是否有重复的值。
   * @Date 2020/9/3
   * @Param [arr]
   **/
  public static void checkRepeatByHashMap(int[] arr) {

    HashMap hashMap = new HashMap(arr.length);
    Object a = 0;
    for (int i = 0; i < arr.length; i++) {

      a = hashMap.put(arr[i], "1");

      if (!ObjectUtils.isEmpty(a) && a.equals("1")) {

        System.out.println(true);
        return;
      }

    }
    System.out.println(false);

  }

  /**
   * @return void
   * @Author YJY
   * @Description 利用hashMap特性  自带的包含方法
   * @Date 2021/8/23
   * @Param []
   **/
  public static void checkRepeatByHashMapKey(int[] arr) {

    HashMap hashMap = new HashMap();

    for (int i = 0; i < arr.length; i++) {

      if (hashMap.containsKey(arr[i])) {
        System.out.println(true);
        return;
      }
      hashMap.put(arr[i], "1");
    }
    System.out.println(false);
  }

  /**
   * @return void
   * @Author YJY
   * @Description 利用Set特性去做 Set 自动去重 将数组的值放入set 如果都不重复 set.size == arr.length 有重复的话
   * set的大小肯定小于原数组的大小
   * @Date 2021/8/23
   * @Param [arr]
   **/
  public static void checkRepeatBySet(int[] arr) {

    Set<Integer> res = new HashSet<>();
    if (arr.length <= 1) {
      System.out.println(false);
    }
    for (int i : arr) {
      res.add(i);
    }
    System.out.println(res.size() == arr.length ? false : true);
  }

  /**
   * @return void
   * @Author YJY
   * @Description 排序 将数组排序 判断相邻的数组大小是否一致就可以知道是不是有重复的
   * @Date 2021/8/23
   * @Param [arr]
   **/
  public static void checkRepeatBySort(int[] arr) {

    Arrays.sort(arr);
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] == arr[i + 1]) {
        System.out.println(true);
        return;
      }

    }
    System.out.println(false);
  }

  /**
   * @return void
   * @Author YJY
   * @Description 利用stream流 一行代码
   * @Date 2021/8/23
   * @Param [arr]
   **/
  public static void checkRepeatByStream(int[] arr) {

    System.out.println(IntStream.of(arr).distinct().count() == arr.length ? false : true);
  }

  public static void main(String[] args) {

//    int[] arr = {1, 2, 3, 4, 5, 6};
//
//    checkRepeatByHashMap(arr);
//    checkRepeatBySet(arr);
//    checkRepeatBySort(arr);
//    checkRepeatByStream(arr);
//    checkRepeatByHashMapKey(arr);

    Set<Integer> res = new HashSet<>();

    System.out.println(res.add(1));
    System.out.println(res.add(1));
  }
}
