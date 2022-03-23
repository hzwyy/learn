package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/23 10:55
 * @description: 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * <p>
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。 给你一个句子 s​​​​​​ 和一个整数 k​​​​​​ ，请你将 s​​
 * 截断 ​，​​​使截断后的句子仅含 前 k​​​​​​ 个单词。返回 截断 s​​​​​​ 后得到的句子。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "Hello how are you Contestant", k = 4 输出："Hello how are you" 解释： s 中的单词为 ["Hello", "how"
 * "are", "you", "Contestant"] 前 4 个单词为 ["Hello", "how", "are", "you"] 因此，应当返回 "Hello how are you"
 * 示例 2：
 * <p>
 * 输入：s = "What is the solution to this problem", k = 4 输出："What is the solution" 解释： s 中的单词为
 * ["What", "is" "the", "solution", "to", "this", "problem"] 前 4 个单词为 ["What", "is", "the",
 * "solution"] 因此，应当返回 "What is the solution" 示例 3：
 * <p>
 * 输入：s = "chopper is not a tanuki", k = 5 输出："chopper is not a tanuki"
 * <p>
 */
public class TruncateSentence {


  /**
   * @return java.lang.String
   * @Author YJY
   * @Description方法一：遍历 思路与算法
   * <p>
   * 由题意可知，除了最后一个单词，每个单词后面都跟随一个空格。因此我们可以通过统计空格与句子结尾的数目来统计单词数  count。当 count=k 时，将当前的下标记录到 end，返回句子 s
   * 在  end 处截断的句子。
   * @Date 2022/3/23
   * @Param [s, k]
   **/
  public String truncateSentence(String s, int k) {
    int n = s.length();
    int end = 0, count = 0;
    for (int i = 1; i <= n; i++) {
      if (i == n || s.charAt(i) == ' ') {
        count++;
        if (count == k) {
          end = i;
          break;
        }
      }
    }
    return s.substring(0, end);
  }

  /**
   * @return java.lang.String
   * @Author YJY
   * @Description 优化后的代码
   * @Date 2022/3/23
   * @Param [s, k]
   **/
  public String truncateSentence2(String s, int k) {
    StringBuffer sb = new StringBuffer();
    for (char a : s.toCharArray()) {

      // 遇到空格就减一下k，k减到0了就退出，后面没必要遍历了。
      if (a == ' ' && --k == 0) {
        break;
      }
      sb.append(a);
    }
    return sb.toString();
  }


}
