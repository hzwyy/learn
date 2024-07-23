package com.example.demo.designMode.garnisher;

/**
 * @author: YJY
 * @date: 2023/6/9 10:02
 * @description:
 */
public class ConcreteDecoratorB  extends Decorator{


  @Override
  public void operation() {
    add();
    super.operation();

  }

  public void add(){
    System.out.println("戴帽子");
  }
}
