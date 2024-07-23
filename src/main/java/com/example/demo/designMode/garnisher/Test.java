package com.example.demo.designMode.garnisher;

/**
 * @author: YJY
 * @date: 2023/6/9 10:03
 * @description:
 */
public class Test {


  public static void main(String[] args) {

    ConcreteDecoratorA a = new ConcreteDecoratorA();

    ConcreteDecoratorB b = new ConcreteDecoratorB();

    b.SetComponent(a);

    b.operation();
  }
}
