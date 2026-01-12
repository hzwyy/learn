package com.example.demo.线程;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: YJY
 * @date: 2025/5/28 11:09
 * @description: 线程池 线程池执行流程顺序如下：
 * <p>
 * 当前线程数 < 核心线程数 → 直接创建线程处理任务
 * <p>
 * 当前线程数 >= 核心线程数 → 任务放入队列等待执行
 * <p>
 * 队列满了 且 当前线程数 < 最大线程数 → 创建非核心线程处理
 * <p>
 * 队列满了 且 当前线程数 >= 最大线程数 → 触发拒绝策略
 */
public class ThreadMain {

  //核心线程数 业务高并发建议设为CPU核心数
  private final static int corePoolSize = 2;
  //最大线程数 通常为core * 2或core + N
  private final static int maximumPoolSize = 2;
  //空闲线程存活时间 控制非核心线程多久回收
  private final static int keepAliveTime = 10;
  //任务队列
  //LinkedBlockingQueue(默认) 无界队列，任务永远放得下，不会创建超过 core 的线程(适用于任务处理速度快于提交速度)
  //ArrayBlockingQueue 有界队列，常用，配合 maxPoolSize 控制线程增长 控制最大任务量，避免OOM
  //SynchronousQueue 不存储任务，来一个交一个，直接让线程处理 高频交易系统、并发高、拒绝排队
  //PriorityBlockingQueue  带优先级的任务队列 执行有优先级的任务
  private final static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(10);
  // 创建线程的工厂
  //threadFactory
  // 拒绝策略
  //handler
  //AbortPolicy(默认) 直接抛异常
  //CallerRunsPolicy 调用线程自己执行任务（同步执行）
  //DiscardPolicy 直接丢弃任务
  //DiscardOldestPolicy  丢掉队列头的任务,放入当前任务
  static ThreadPoolExecutor executor = new ThreadPoolExecutor(
      corePoolSize,
      maximumPoolSize,
      keepAliveTime,
      TimeUnit.SECONDS,
      workQueue,
      Executors.defaultThreadFactory(),
      new ThreadPoolExecutor.DiscardPolicy()
  );


  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //不带返回值
    ThreadMain.executor.execute(() -> {

      System.out.println("Runnable 任务执行完成，线程: " + Thread.currentThread().getName());
    });

    // 提交 Callable 任务（有返回值）
    Future<String> future = executor.submit(() -> {
      Thread.sleep(1000); // 模拟耗时操作
      return "Callable 任务结果，线程: " + Thread.currentThread().getName();
    });

    // 获取返回值（会阻塞直到任务完成）
    String result = future.get();
    System.out.println(result);
   //关闭线程池
    executor.shutdown();
  }
}
