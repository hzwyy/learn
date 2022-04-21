package com.example.demo.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/3/14 10:07
 * @description: 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * <p>
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]， list2 = ["Piatti", "The Grill at
 * Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"] 输出: ["Shogun"] 解释: 他们唯一共同喜爱的餐厅是“Shogun”。 示例
 * 2:
 * <p>
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger
 * King"] 输出: ["Shogun"] 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。  
 * <p>
 * 提示:
 * <p>
 * 1 <= list1.length, list2.length <= 1000 1 <= list1[i].length, list2[i].length <= 30  list1[i] 和
 * list2[i] 由空格 ' ' 和英文字母组成。 list1 的所有字符串都是 唯一 的。 list2 中的所有字符串都是 唯一 的。
 */
public class FindRestaurant {


  public static String[] findRestaurant(String[] list1, String[] list2) {

    List<String> answer = new ArrayList<>();
    HashMap<String, Integer> data = new HashMap();
    int i = 0;
    for (String a : list1) {
      data.put(a, i++);
    }
    int j = 0;
    int sum = Integer.MAX_VALUE;
    for (String b : list2) {
      if (data.containsKey(b)) {
        if (j + data.get(b) < sum) {
          sum = j + data.get(b);
          answer.clear();
          answer.add(b);
        } else if (j + data.get(b) == sum) {
          answer.add(b);
        }
      }
      ++j;
    }

    return answer.toArray(new String[answer.size()]);

  }

  public static void main(String[] args) {
    String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
    String[] list2 = {"KFC", "Shogun", "Burger King"};

    System.out.println(Arrays.toString(findRestaurant(list1, list2)));
  }

}
