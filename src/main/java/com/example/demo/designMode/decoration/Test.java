package com.example.demo.designMode.decoration;

/**
 * @author: YJY
 * @date: 2022/4/1 16:45
 * @description:
 */
public class Test {

  public static void main(String[] args) {
    Man man = new Man();
    ManDecoratorA md1 = new ManDecoratorA();
    ManDecoratorB md2 = new ManDecoratorB();

    md1.setPerson(man);
    md2.setPerson(md1);
    md2.eat();

  }
}
