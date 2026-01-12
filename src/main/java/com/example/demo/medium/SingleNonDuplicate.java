package com.example.demo.medium;

/**
 * @author: YJY
 * @date: 2022/2/14 10:28
 * @description: 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8] 输出: 2 示例 2:
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11] 输出: 10
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 105 0 <= nums[i] <= 105
 */
public class SingleNonDuplicate {

  /**
   * @return int
   * @Author YJY
   * @Description 找单身 ^解决一切问题
   * @Date 2022/2/14
   * @Param [nums]
   **/
  public static int singleNonDuplicate(int[] nums) {
    int result = 0;
    for (int n : nums) {

      result = result ^ n;
    }
    return result;
  }

  /**
   * @return int
   * @Author YJY
   * @Description 官方解答 由于只出现一次的元素所在下标 x 的左边有偶数个元素，因此下标 x 一定是偶数，可以在偶数下标范围内二分查找。 二分查找的目标是找到满足  nums[x]
   * !=nums[x+1] 的最小的偶数下标 x，则下标 x处的元素是只出现一次的元素。 初始时，二分查找的左边界是 0，右边界是数组的最大偶数下标，由于数组的长度是奇数，因此数组的最大偶数下标等于数组的长度减
   * 1。 每次取左右边界的平均值 mid 作为待判断的下标，如果 mid 是奇数则将 mid 减 1，确保 mid 是偶数， 比较 nums[mid] 和 nums[mid+1]
   * 是否相等，如果相等则 mid<x，调整左边界，否则mid≥x， 调整右边界。调整边界之后继续二分查找，直到确定下标 x 的值。 得到下标 x 的值之后，nums[x]
   * 即为只出现一次的元素。
   * <p>
   * 细节
   * <p>
   * 考虑mid 和 1 按位与运算的结果，其中 & 是按位与运算符：
   * <p>
   * 当 mid 是偶数时，mid & 1=0；
   * <p>
   * 当 mid 是奇数时，mid & 1=1。
   * <p>
   * 因此在得到 mid 的值之后，将 mid 的值减去 mid & 1， 即可确保 mid 是偶数， 如果原来的 mid 是偶数则值不变，如果原来的 mid 是奇数则值减 1。
   * @Date 2022/2/14
   * @Param [nums]
   **/
  public int singleNonDuplicate1(int[] nums) {
    int low = 0, high = nums.length - 1;
    while (low < high) {
      int mid = (high - low) / 2 + low;
      mid -= mid & 1;
      if (nums[mid] == nums[mid + 1]) {
        low = mid + 2;
      } else {
        high = mid;
      }
    }
    return nums[low];

  }


  public static void main(String[] args) {

    int[] nums = {3, 3, 7, 7, 10, 11, 11};

    System.out.println(singleNonDuplicate(nums));
  }
}
