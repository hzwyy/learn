package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/2/9 11:15
 * @description: 小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 x 和 y），请小扣说出计算指令：
 * <p>
 * "A" 运算：使 x = 2 * x + y； "B" 运算：使 y = 2 * y + x。 在本次游戏中，店家说出的数字为 x = 1 和 y = 0，小扣说出的计算指令记作仅由大写字母
 * A、B 组成的字符串 s， 字符串中字符的顺序表示计算顺序，请返回最终 x 与 y 的和为多少。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "AB"
 * <p>
 * 输出：4
 * <p>
 * 解释： 经过一次 A 运算后，x = 2, y = 0。 再经过一次 B 运算，x = 2, y = 2。 最终 x 与 y 之和为 4。
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 10 s 由 'A' 和 'B' 组成
 */
public class Calculate {


  /**
   * @return int
   * @Author YJY
   * @Description 第一反应直接循环就可以了 但是仔细看一下公式
   * <p>
   * 如果输入 A   x = 2 * x + y 结果就是  X+Y 也就是  2 * x + y + y 继续计算得出 结果 = 2x + 2y
   * 如果输入 B   y = 2 * y + x 结果同理 X+Y 也就是  2 * y + x + x  继续计算 结果 = 2y + 2x
   * 所以不管输入几 结果都可以理解为 2(x + y)*用户输入的字符长度   也就是输入了几个 AB
   * 由于知道 x = 1 y = 0 所以最终的结果就是 2*用户输入的字符长度
   * @Date 2022/2/9
   * @Param [s]
   **/
  public static int calculate(String s) {
    if(null == s || "".equals(s)){
      return 1;
    }
    return  (int) Math.pow(2,s.length());


  }

  public static void main(String[] args) {

    System.out.println(calculate(""));
  }
}
