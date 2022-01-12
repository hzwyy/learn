package com.example.demo.easy;

import java.util.Arrays;

/**
 * @author: YJY
 * @date: 2022/1/7 11:14
 * @description: 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class PlusOne {

  /**
   * @return int[]
   * @Author YJY
   * @Description 当我们对数组  加一时，我们只需要关注 的末尾出现了多少个 9 即可。我们可以考虑如下的三种情况：
   * <p>
   * 如果数组的末尾没有 9，例如[1,2,3]，那么我们直接将末尾的数加一，得到[1,2,4] 并返回；
   * <p>
   * 如果数组的末尾有若干个9，例如 [1,2,3,9,9]，那么我们只需要找出从末尾开始的第一个不为 9 的元素，即 3，将该元素加一，得到 [1,2,4,9,9]。随后将末尾的 99
   * 全部置零，得到 [1,2,4,0,0] 并返回。
   * <p>
   * 如果数组的所有元素都是 9，例如 [9,9,9,9,9]，那么答案为[1,0,0,0,0,0]。我们只需要构造一个长度比原数组多1的新数组，将首元素置为 1，其余元素置为 00 即可。
   * @Date 2022/1/7
   * @Param [digits]
   **/
  public static int[] plusOne(int[] digits) {

    for (int i = digits.length - 1; i >= 0; --i) {
      //倒着找 找到了不为9的数字 加一
      if (digits[i] != 9) {

        ++digits[i];

        //将不为9的数字加一后 后面的要统一改为0
        for (int j = i + 1; j < digits.length; j++) {
          digits[j] = 0;
        }
        return digits;
      }

    }
    //如果所有数字都为9 则走下面这个方法
    int[] res = new int[digits.length + 1];
    //第一位放入1即可
    res[0] = 1;
    return res;
  }

  /**
   * @return int[]
   * @Author YJY
   * @Description 第二种方法 直接相加 然后判断结果取余是否等于0 如果等于代表 继续循环前位数加一 不好理解
   * @Date 2022/1/7
   * @Param [digits]
   **/
  public static int[] plusOne2(int[] digits) {

    for (int i = digits.length - 1; i >= 0; --i) {
      digits[i] = (digits[i] + 1) % 10;
      if (digits[i] != 0) {
        return digits;
      }
    }

    //如果所有数字都为9 则走下面这个方法
    int[] res = new int[digits.length + 1];
    //第一位放入1即可
    res[0] = 1;
    return res;
  }


  public static void main(String[] args) {
    int[] a = {9, 9, 9, 9};
    System.out.println(Arrays.toString(plusOne2(a)));
  }
}
