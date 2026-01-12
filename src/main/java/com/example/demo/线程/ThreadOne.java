package com.example.demo.线程;

import lombok.SneakyThrows;

/**
 * @author: YJY
 * @date: 2025/5/28 10:39
 * @description:  继承 Thread 类
 *       调用run()方法 是顺序执行的 只是单纯的调用了方法
 *       调用start() 才是异步执行
 */
public class ThreadOne extends Thread {

  public ThreadOne(String name){
    super(name);
  }

  @SneakyThrows
  public void run() {
    Thread.sleep(1000);
    System.out.println(
        "线程:" + Thread.currentThread().getName() + "运行时间" + System.currentTimeMillis());
  }


  public static void main(String[] args) {
    ThreadOne t1 = new ThreadOne("线程1");
    ThreadOne t2 = new ThreadOne("线程2");
    ThreadOne t3 = new ThreadOne("线程3");
    t1.run();
    t2.run();
    t3.run();

    t1.start();
    t2.start();
    t3.start();
  }
}
