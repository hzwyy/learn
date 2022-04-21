package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/2/23 9:57
 * @description: 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * <p>
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？ 示例 1：
 * <p>
 * 输入：s = "abc", t = "ahbgdc" 输出：true 示例 2：
 * <p>
 * 输入：s = "axc", t = "ahbgdc" 输出：false
 */
public class IsSubsequence {

  /**
   * @return boolean
   * @Author YJY
   * @Description 方法一：双指针 但是不能解决大数据量问题
   * <p>
   * 本题询问的是，s 是否是 t 的子序列，因此只要能找到任意一种 s 在 t 中出现的方式，即可认为 s 是 t 的子序列。
   * <p>
   * 而当我们从前往后匹配，可以发现每次贪心地匹配靠前的字符是最优决策。
   * <p>
   * 假定当前需要匹配字符 c，而字符 c 在 t 中的位置 x1 ​ 和 x2 ​ 出现（x1< x2 ​），那么贪心取 x1 ​ 是最优解，因为 x2 ​ 后面能取到的字符，x1
   * ​也都能取到，并且通过 x1 ​与 x2 ​之间的可选字符，更有希望能匹配成功。
   * <p>
   * 这样，我们初始化两个指针 i 和 j，分别指向 s 和 t 的初始位置。每次贪心地匹配，匹配成功则 i 和 j 同时右移，匹配 s 的下一个位置，匹配失败则 j 右移，i 不变，尝试用 t
   * 的下一个字符匹配 s。
   * <p>
   * 最终如果 i 移动到 s 的末尾，就说明 s 是 t 的子序列。
   * @Date 2022/2/23
   * @Param [s, t]
   **/
  public static boolean isSubsequence(String s, String t) {

    int sL = s.length();
    int tL = t.length();
    int si = 0;
    int ti = 0;
    while (si < sL && ti < tL) {

      if (s.charAt(si) == t.charAt(ti)) {
        si++;
        ti++;
      } else {
        ti++;
      }
    }

    return si == sL;
  }

  /**
   * @return boolean
   * @Author YJY
   * @Description KMP算法 还不太懂
   * @Date 2022/2/23
   * @Param [s, t]
   **/
  public static boolean isSubsequence2(String s, String t) {

    return false;
  }


  public static void main(String[] args) {

    System.out.println(isSubsequence("asd", "aiewqewsninind"));

  }
}
