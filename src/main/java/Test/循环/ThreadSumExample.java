package Test.循环;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * @author: YJY
 * @date: 2025/3/28 17:34
 * @description:
 */
public class ThreadSumExample {

  // 共享变量
  private static int sum = 0;
  //static AtomicLong sum = new AtomicLong(0);
  // 线程数量
  private static final int THREAD_COUNT = 10;
  // 每个线程执行的累加次数
  private static final int LOOP_COUNT = 10000;

  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[THREAD_COUNT];

    for (int i = 0; i < THREAD_COUNT; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < LOOP_COUNT; j++) {
          sum++; // 非原子操作，可能导致计算错误

        }
      });
      threads[i].start();
    }

    // 等待所有线程执行完成
    for (Thread thread : threads) {
      thread.join();
    }

    // 预期值应该是 10 * 10000 = 100000
    System.out.println("最终计算结果: " + sum);
  }


}
