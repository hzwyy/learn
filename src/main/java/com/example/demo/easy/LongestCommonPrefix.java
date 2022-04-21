package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/2/7 9:55
 * @description: 编写一个函数来查找字符串数组中的最长公共前缀。 如果不存在公共前缀，返回空字符串 ""。  
 * <p>
 * 示例 1： 输入：strs = ["flower","flow","flight"] 输出："fl"
 * <p>
 * 示例 2： 输入：strs = ["dog","racecar","car"] 输出："" 解释：输入不存在公共前缀。
 * <p>
 * 提示： 1 <= strs.length <= 200 0 <= strs[i].length <= 200 strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix {


  /**
   * @return java.lang.String
   * @Author YJY
   * @Description 横向扫描 依次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，更新最长公共前缀， 当遍历完所有的字符串以后，即可得到字符串数组中的最长公共前缀。
   * @Date 2022/2/7
   * @Param [strs]
   **/
  public static String longestCommonPrefix(String[] strs) {

    if (null == strs || strs.length == 0 || strs.length == 1) {
      return "";
    }
    String flag = strs[0];
    for (int i = 1; i < strs.length; i++) {
      flag = compare(flag, strs[i]);
    }

    return flag;
  }

  public static String compare(String flag, String str) {

    int length = Math.min(flag.length(), str.length());
    int i = 0;
    while (length > i && flag.charAt(i) == str.charAt(i)) {

      i++;
    }
    return flag.substring(0, i);
  }


}
