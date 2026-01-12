package com.example.demo.线程;

/**
 * @author: YJY
 * @date: 2025/5/28 10:54
 * @description:
 * start()：启动线程（非run()）
 *
 * sleep(ms)：让当前线程睡眠一段时间
 *
 * join()：等待某个线程执行完再继续
 *
 * yield()：让出CPU执行权（不常用）
 *
 * interrupt()：中断线程
 */
public class ThreadTwo implements Runnable{

  @Override
  public void run() {
    System.out.println("线程:"+Thread.currentThread().getName()+"执行时间:"+System.currentTimeMillis());
  }


  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new ThreadTwo());
    Thread t2 = new Thread(new ThreadTwo());
    Thread t3 = new Thread(new ThreadTwo());

    t1.start();
    t2.start();
    t1.join(); // 等待t1完成
    t2.join(); // 等待t2完成
    System.out.println("t1 t2执行完毕");
    t3.start();
  }
}
