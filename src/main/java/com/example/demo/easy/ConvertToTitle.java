package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/1/12 11:30
 * @description:
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。

 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 *  
 *
 * 示例 1:
 *
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: columnTitle = "ZY"
 * 输出: 701
 */
public class ConvertToTitle {





  /**
   * @Author YJY
   * @Description 这是一道从 1 开始的的 26 进制转换题。想快速理解先看本题的反转题目(titleToNumber)下面那个方法
   *
   * 对于一般性的进制转换题目，只需要不断地对 columnNumber 进行 % 运算取得最后一位，
   * 然后对 columnNumber 进行 / 运算，将已经取得的位数去掉，直到 columnNumber 为 0 即可。
   *
   * 一般性的进制转换题目无须进行额外操作，是因为我们是在「每一位数值范围在 [0,x)」的前提下进行「逢 x 进一」。
   *
   * 但本题需要我们将从 1 开始，因此在执行「进制转换」操作前，我们需要先对 columnNumber 执行减一操作，从而实现整体偏移。
   *
   * @Date  2022/1/12
   * @Param []
   * @return int
   **/
  public static String convertToTitle(int columnNumber){

    StringBuffer sb = new StringBuffer();
    while (columnNumber > 0) {
      int a0 = (columnNumber - 1) % 26 + 1;
      sb.append((char)(a0 - 1 + 'A'));
      columnNumber = (columnNumber - a0) / 26;
    }
    return sb.reverse().toString();
  }




  /**
   * @Author YJY
   * @Description
   * 进制转换  和原题是反转题目
   * 也许你没专门做过「进制转换」类的题目，但相信你确实在 1010 进制中使用过如下的「进制转换」手段。
   *
   * 如果题目是 10 进制转换，那么你会很容易想到如下转换过程：
   * 从高位向低位处理，起始让 ans为 0，每次使用当前位数值更新 ans，更新规则为 ans = ans * 10 + val_i
   *
   * 举个🌰，假设存在某个十进制数字，编码为 ABCD（字母与数字的映射关系与本题相同），转换过程如下：
   *
   * ans = 0
   * ans = ans * 10 + 1 => A
   * ans = ans * 10 + 2 => B
   * ans = ans * 10 + 3 => C
   * ans = ans * 10 + 4=> D
   *
   * 同理，本题只是将 10 进制换成 26 进制。
   * @Date  2022/1/12
   * @Param [columnTitle]
   * @return int
   **/
  public static int titleToNumber(String columnTitle) {
    char[] cs = columnTitle.toCharArray();
    int n = cs.length;
    int ans = 0;
    for (int i = 0; i < n; i++) {
      ans = ans * 26 + (cs[i] - 'A' + 1);
    }
    return ans;

  }





  public static void main(String[] args) {

    System.out.println(titleToNumber("AD"));
  }
}
