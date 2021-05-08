package com.example.demo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: YJY
 * @date: 2021/5/8 15:35
 * @description:
 *
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 解析:
 * 字母异位词就是两个字符串中的字母都是一样的，只不过顺序被打乱了，这里要把他们找出来，然后放到一起。
 * 既然字母异位词的字母都是一样的，可以对字符串中的字符进行排序，生成一个新的字符串，
 * 如果生成新的字符串相同，那么他们就是字母异位词。代码比较简单
 *
 */
public class GroupAnagrams {


  public static void groupAnagrams(String []chars){
    //结果集
    HashMap<String, List<String>> result = new HashMap<>();

    for (int i = 0; i<chars.length; i++){
      //对值进行排序 异位词就变为一致了
      String key =  sort(chars[i]);
       //如果还未存储创建新的集合
       if(!result.containsKey(key)) {
         result.put(key, new ArrayList<>());
       }
        //存储
       result.get(key).add(chars[i]);
    }
    System.out.println(result.values());
  }


  private static String sort(String values){
    char[] v = values.toCharArray();
    Arrays.sort(v);
    return String.valueOf(v);
  }


  public static void main(String[] args) {
    String[] keys = {"abc","acb","bca","ad","da","haha"};
    groupAnagrams(keys);
  }
}
