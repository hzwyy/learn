package com.example.demo.designMode.garnisher;

/**
 * @author: YJY
 * @date: 2023/6/9 10:00
 * @description:
 */
public class ConcreteDecoratorA extends Decorator {


  @Override
  public void operation() {
    add();
    super.operation();


  }

  public void add() {

    System.out.println("穿鞋");
  }
}
