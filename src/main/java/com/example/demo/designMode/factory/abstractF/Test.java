package com.example.demo.designMode.factory.abstractF;

import com.example.demo.designMode.factory.abstractF.factory.AbstractFactory;

/**
 * @author: YJY
 * @date: 2022/3/30 15:22
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    AbstractFactory abstractFactory = FactoryProducer.getFactory("正义的");

    Behavior behavior = abstractFactory.behavior();
    Color color = abstractFactory.color();
    System.out.println(behavior.behavior());
    System.out.println(color.draw());

    AbstractFactory abstractFactory2 = FactoryProducer.getFactory("邪恶的");

    Behavior behavior2 = abstractFactory2.behavior();
    Color color2 = abstractFactory2.color();
    System.out.println(behavior2.behavior());
    System.out.println(color2.draw());


  }
}
