package com.example.demo.myjob;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {


  /**
  * @Description 核心线程
  **/
  private final static int THREAD_POOL_CORE_SIZE = 4;
  /**
   * @Description 最大线程
   **/
  private final static int THREAD_POOL_MAX_SIZE = 5;
  /**
   * @Description 存活时间
   **/
  private final static int KEEP_ALIVE_TIME_SECOND = 3;
  /**
   * @Description 等待队列
   **/
  private final static int ARRAY_BLOCK_QUEUE = 1;
  /**
   * @Description 线程池
   **/
  private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
      THREAD_POOL_CORE_SIZE, THREAD_POOL_MAX_SIZE, KEEP_ALIVE_TIME_SECOND,
      TimeUnit.SECONDS, new ArrayBlockingQueue<>(ARRAY_BLOCK_QUEUE));

  public static ThreadPoolExecutor getThread() {

    return threadPoolExecutor;

  }

}
