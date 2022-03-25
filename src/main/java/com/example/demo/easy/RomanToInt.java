package com.example.demo.easy;

import java.util.HashMap;

/**
 * @author: YJY
 * @date: 2022/3/15 14:07
 * @description: 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。 字符   数值 I1  V 5   X 10   L 50    C 100   D 500
 * M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4
 * 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。  C 可以放在 D (500)
 * 和 M (1000) 的左边，来表示 400 和 900。 给定一个罗马数字，将其转换成整数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "III" 输出: 3 示例 2:
 * <p>
 * 输入: s = "IV" 输出: 4 示例 3:
 * <p>
 * 输入: s = "IX" 输出: 9 示例 4:
 * <p>
 * 输入: s = "LVIII" 输出: 58 解释: L = 50, V= 5, III = 3. 示例 5:
 * <p>
 * 输入: s = "MCMXCIV" 输出: 1994 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class RomanToInt {

  /**
   * @return int
   * @Author YJY
   * @Description
   * @Date 2022/3/15
   * @Param [s]
   **/
  public static int romanToInt(String s) {

    int result = 0;
    HashMap<Character, Integer> roman = new HashMap() {{
      put('I', 1);
      put('V', 5);
      put('X', 10);
      put('L', 50);
      put('C', 100);
      put('D', 500);
      put('M', 1000);
    }};
    int n = s.length();
    for (int i = 0; i < n; i++) {

      if (i < n - 1 && roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
        result += -roman.get(s.charAt(i));
      } else {
        result += roman.get(s.charAt(i));
      }
    }

    return result;
  }

  public static void main(String[] args) {

    System.out.println(romanToInt("LVIII"));
  }
}
