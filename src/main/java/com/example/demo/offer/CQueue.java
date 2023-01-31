package com.example.demo.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author: YJY
 * @date: 2023/1/30 11:30
 * @description: 剑指 Offer 09. 用两个栈实现队列
 */
public class CQueue {


  Deque<Integer> stack1;
  Deque<Integer> stack2;

  /**
   * Your CQueue object will be instantiated and called as such: CQueue obj = new CQueue();
   * obj.appendTail(value); int param_2 = obj.deleteHead();
   */
  public CQueue() {
    stack1 = new ArrayDeque<>();
    stack2 = new ArrayDeque<>();
  }

  public void appendTail(int value) {
    stack1.push(value);
  }

  public int deleteHead() {
    if (stack2.isEmpty()) {
      if(stack1.isEmpty()){
        return -1;
      }
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }
    return stack2.pop();
  }
}
