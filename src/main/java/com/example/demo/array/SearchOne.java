package com.example.demo.array;

/**
 * @author: YJY
 * @date: 2020/9/3 17:35
 * @description: 只出现一次的数字 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明： 你的算法应该具有线性时间复杂度(所以不能用暴力办法 时间复杂度不符合)。 你可以不使用额外空间来实现吗？(所以不可以用HashMap等其他额外空间)
 * <p>
 * 解析: 根据异或(^)的特点，我们可以将数组中的全部元素异或，相同的元素全部变成了0，一个数与0异或又是他本身，那么最终剩下来的那个数就是结果啦 异或是满足交换律和结合的， 也就是说 a^
 * b^c = a^ c ^b， 这样当我们遍历数组，顺次进行异或运算，那么最终的结果就是唯一的不重复数字。 如[4,1,2,1,2]， 4 ^ 1^ 2^ 1^ 2 = 1^ 1^ 2^ 2^ 4
 * =0^ 0^4=4
 */
public class SearchOne {


  public static void searchOne(int[] nums) {

    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      res = res ^ nums[i];
    }

    System.out.println(res);
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 1, 2, 3, 4};
    searchOne(nums);
  }
}
