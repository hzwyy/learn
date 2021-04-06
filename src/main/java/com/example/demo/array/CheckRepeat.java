package com.example.demo.array;

import java.util.HashMap;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author: YJY 判断数组是否存在重复元素
 * @date: 2020/9/3 16:50
 * @description:
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false
 */
public class CheckRepeat {


  /**
   * @Author YJY
   * @Description 利用hashMap 特性去做 方便快捷
   * 利用数组的值做 key.
   * 如果key之前不存在 hashMap 会直接放入value 并没有返回值. 第一次 a = hashMap.put(arr[i],"1");  a这个时候等于 null
   * 如果此key 已经存放过值 会保存第二次存放的值并返回第一次存的值. 第二次    a = hashMap.put(arr[i],"1");  a这个时候等于 1
   * 这样就可以判断在hashmap中这个key是第一次存入。即可判断数组中是否有重复的值。
   * @Date  2020/9/3
   * @Param [arr]
   * @return void
   **/
  public static void checkRepeat(int[] arr){

     HashMap hashMap = new HashMap(arr.length);
     Object a = 0;
    for (int i=0; i<arr.length; i++){

       a = hashMap.put(arr[i],"1");

       if(ObjectUtils.isNotEmpty(a) && a.equals("1")){

         System.out.println(true);
        return;
       }

    }
    System.out.println(false);

  }


  public static void main(String[] args) {

    int[] arr = {1,2,3,4,5,6,2};

    checkRepeat(arr);
  }
}
