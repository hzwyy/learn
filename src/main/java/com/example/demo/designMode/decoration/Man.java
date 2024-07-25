package com.example.demo.designMode.decoration;

/**
 * @author: YJY
 * @date: 2022/4/1 16:53
 * @description: 具体被装饰的类
 */
public class Man implements Person {

  @Override
  public void eat() {
    System.out.println("男生吃肉");
  }
}
