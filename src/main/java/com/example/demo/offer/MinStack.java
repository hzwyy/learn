package com.example.demo.offer;

import java.util.Stack;

/**
 * @author: YJY
 * @date: 2023/1/30 14:30
 * @description: 剑指 Offer 30. 包含min函数的栈
 */
public class MinStack {

  Stack<Integer> stack;
  Stack<Integer> minStack;

  /**
   * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack();
   * obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4 = obj.min();
   */
  public MinStack() {
    stack = new Stack<>();
    minStack = new Stack<>();
    minStack.push(Integer.MAX_VALUE);
  }

  public void push(int x) {
    stack.push(x);
    // 这里的意思是 放入x 记录放入x以后的最小值
    // 这样minStack里面就存放了和stack每个值放入时 最小值都是什么
    minStack.push(Math.min(minStack.peek(), x));
  }

  public void pop() {
    stack.pop();
    minStack.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int min() {
    return minStack.peek();
  }
}
