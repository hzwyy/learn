package com.example.demo.designMode.factory.abstractF.factory;

import com.example.demo.designMode.factory.abstractF.Behavior;
import com.example.demo.designMode.factory.abstractF.ChinaBehavior;
import com.example.demo.designMode.factory.abstractF.ChinaColor;
import com.example.demo.designMode.factory.abstractF.Color;
import com.example.demo.designMode.factory.abstractF.factory.AbstractFactory;

/**
 * @author: YJY
 * @date: 2022/3/30 15:14
 * @description: 可以在这里面任意组装需要的行为 类似于组装衣服搭配
 */
public class JusticeFactory extends AbstractFactory {

  @Override
  public Behavior behavior() {
    return new ChinaBehavior();
  }

  @Override
  public Color color() {
    return new ChinaColor();
  }
}
