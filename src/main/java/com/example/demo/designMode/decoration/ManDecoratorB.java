package com.example.demo.designMode.decoration;

/**
 * @author: YJY
 * @date: 2022/4/1 16:56
 * @description: 具体装饰者
 */
public class ManDecoratorB extends Decorator {

  @Override
  public void eat() {
    super.eat();
    reEat();

  }

  public void reEat() {
    System.out.println("并且再吃两顿饭");
  }
}
