package com.example.demo.designMode.decoration;

/**
 * @author: YJY
 * @date: 2022/4/1 16:54
 * @description: 装饰者抽象类
 */
public abstract class Decorator implements Person {

  protected Person person;

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public void eat() {
    person.eat();
  }
}
