package com.example.demo.designMode.factory.abstractF;

import com.example.demo.designMode.factory.abstractF.factory.AbstractFactory;
import com.example.demo.designMode.factory.abstractF.factory.EvilFactory;
import com.example.demo.designMode.factory.abstractF.factory.JusticeFactory;

/**
 * @author: YJY
 * @date: 2022/3/30 15:13
 * @description:  这里根据用户选择将组装好的行为返回给用户
 *
 *  类似于每一个小的实现工厂 将各个行为进行组装为一套衣服 然后这个工厂就是 这个衣服总类型的代表
 *  比如 时尚的  性感的  骨干的
 * 而这个工厂制造者 根据用户想要什么类型的返回 某一个实现好的工厂
 */
public class FactoryProducer {

  public static AbstractFactory getFactory(String choice) {

    switch (choice) {
      case "正义的":
        return new JusticeFactory();
      case "邪恶的":
        return new EvilFactory();
      default:
        return new JusticeFactory();
    }
  }
}
