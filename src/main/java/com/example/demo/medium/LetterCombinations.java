package com.example.demo.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YJY
 * @date: 2022/4/24 15:55
 * @description: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23" 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"] 示例 2：
 * <p>
 * 输入：digits = "" 输出：[] 示例 3：
 * <p>
 * 输入：digits = "2" 输出：["a","b","c"]
 */
public class LetterCombinations {

  //数据
  public static  Map<Character, String> phoneMap = new HashMap<Character, String>() {{
    put('2', "abc");
    put('3', "def");
    put('4', "ghi");
    put('5', "jkl");
    put('6', "mno");
    put('7', "pqrs");
    put('8', "tuv");
    put('9', "wxyz");
  }};

  public static List<String> letterCombinations(String digits) {
    //结果集
    List<String> combinations = new ArrayList<String>();
    if (digits.length() == 0) {
      return combinations;
    }

    //回溯函数
    myTest(combinations,  digits, 0, new StringBuffer());
    return combinations;
  }

  public static void backtrack(List<String> combinations,String digits, int index, StringBuffer combination) {
    if (index == digits.length()) {
      combinations.add(combination.toString());
    } else {
      char digit = digits.charAt(index);
      String letters = phoneMap.get(digit);
      int lettersCount = letters.length();
      for (int i = 0; i < lettersCount; i++) {
        combination.append(letters.charAt(i));
        backtrack(combinations, digits, index + 1, combination);
        //向下到了终点 开始撤销上一步动作 回溯
        combination.deleteCharAt(index);
      }
    }
  }

  /**
   * @Author YJY
   * @Description 自己测试模拟回溯
   * @Date  2022/4/27
   * @Param [results 封装各个结果集, target 目标值, index 进入深度, one 一次回溯的结果]
   * @return void
   **/
  public static void myTest(List<String> results,String target,int index,StringBuffer one){
    //第一定义一次深入的回溯条件
    //也就是达成某一个条件 这条路径即可返回
    //这里target.length() == index 的时候 代表 已经达到的最深处
    if(target.length() == index){
      //达到深处后 放入结果 此时程序开始回溯此次深入
      results.add(one.toString());
    }else{

      //找到此位置的字符对应的'字符串'
      String v =  phoneMap.get(target.charAt(index));
      for (int i=0; i < v.length(); i ++){
        //放入第i个位置的结果
        one.append(v.charAt(i));
        //当上面放入第i个位置的结果后 就该再深入下一层 获取下一层的字符 进行拼凑
        myTest(results,target,index +1,one);
        //这里的删除是 当回溯到最深层时 我们已经将结果放入了 results里面
        //然后每回溯一层 将之前的放入操作都删除 相当于回到了树的第一层 此时里面只有入口值 然后下次从入口换路径进入
        one.deleteCharAt(index);
      }
    }
  }

  public static void main(String[] args) {

    List<String> list = letterCombinations("23");
    System.out.println(list.toString());
  }
}
