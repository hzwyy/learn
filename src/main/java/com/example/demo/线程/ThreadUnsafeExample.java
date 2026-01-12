package com.example.demo.线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: YJY
 * @date: 2025/5/28 14:58
 * @description:
 */
public class ThreadUnsafeExample {
 // private static int count = 0; // 🚫 共享变量，未加锁
  private static AtomicInteger count = new AtomicInteger(0);
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 10; i++) {
      executor.execute(() -> {
        for (int j = 0; j < 1000; j++) {
         // count++;
          count.incrementAndGet(); // 原子操作
        }
      });
    }

    executor.shutdown();
    while (!executor.isTerminated()) {
      Thread.sleep(100); // 等线程跑完
    }

    System.out.println("最终计数值: " + count);
  }
}
