package com.example.demo.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author: YJY
 * @date: 2022/3/17 10:32
 * @description: 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
 * 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7 输出：[[2,2,3],[7]] 解释： 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2
 * 可以使用多次。 7 也是一个候选， 7 = 7 。 仅有这两种组合。 示例 2：
 * <p>
 * 输入: candidates = [2,3,5], target = 8 输出: [[2,2,2,2],[2,3,3],[3,5]] 示例 3：
 * <p>
 * 输入: candidates = [2], target = 1 输出: []  
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30 1 <= candidates[i] <= 200 candidate 中的每个元素都 互不相同 1 <= target <= 500
 */
public class CombinationSum {


  /**
   * @Author YJY
   * @Description 回溯算法
   * @Date  2022/3/17
   * @Param [candidates, target]
   * @return java.util.List<java.util.List<java.lang.Integer>>
   **/
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {


    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> combine = new ArrayList<>();
    dfs(candidates,target,ans,combine,0);
    return ans;
  }


  public static void dfs(int[]candidates,int target,List<List<Integer>> ans,List<Integer> combine,int idx){
    //如果深度达到了目标的最深处则已探索完成
    if(idx == candidates.length){
      return;
    }
    //当下列所有探索完成以后 target 被完全计算完成即为答案
    if(target == 0){
      ans.add(new ArrayList<Integer>(combine));
      return;
    }
    //铺开
     dfs(candidates,target,ans,combine,idx+1);
    //进行计算
    if(target - candidates[idx] >= 0){
      combine.add(candidates[idx]);
      dfs(candidates,target-candidates[idx],ans,combine,idx);
      combine.remove(combine.size()-1);
    }


  }




  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    int len = candidates.length;
    List<List<Integer>> res = new ArrayList<>();
    if (len == 0) {
      return res;
    }

    Deque<Integer> path = new ArrayDeque<>();
    dfs2(candidates, 0, len, target, path, res);
    return res;
  }

  /**
   * @param candidates 候选数组
   * @param begin      搜索起点
   * @param len        冗余变量，是 candidates 里的属性，可以不传
   * @param target     每减去一个元素，目标值变小
   * @param path       从根结点到叶子结点的路径，是一个栈
   * @param res        结果集列表
   */
  private static void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
    // target 为负数和 0 的时候不再产生新的孩子结点
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(path));
      return;
    }

    // 重点理解这里从 begin 开始搜索的语意
    for (int i = begin; i < len; i++) {
      path.addLast(candidates[i]);

      // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
      dfs2(candidates, i, len, target - candidates[i], path, res);

      // 状态重置
      path.removeLast();
    }
  }



  public static void main(String[] args) {

    int[] candidates = {2,3,5};
    int target = 8;

    System.out.println(combinationSum(candidates,target).toString());
  }

}


