package com.example.demo.线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: YJY
 * @date: 2025/5/28 14:33
 * @description:
 */
public class MyCallable implements Callable<Integer> {

  int a;
  int b;

  public MyCallable(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public Integer call() throws Exception {
    System.out.println(Thread.currentThread().getName());
    return a + b;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 创建MyCallable对象
    MyCallable mc = new MyCallable(1,2);
    // 创建FutureTask
    FutureTask<Integer> ft = new FutureTask<Integer>(mc);
    // 创建Thread对象
    Thread t1 = new Thread(ft);
    Thread t2 = new Thread(ft);
    // 调用start方法启动线程
    t1.start();
    // 调用ft的get方法获取执行结果
    // 输出
    System.out.println(ft.get());


  }
}
