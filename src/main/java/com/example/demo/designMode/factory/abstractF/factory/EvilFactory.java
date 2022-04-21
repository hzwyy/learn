package com.example.demo.designMode.factory.abstractF.factory;

import com.example.demo.designMode.factory.abstractF.Behavior;
import com.example.demo.designMode.factory.abstractF.Color;
import com.example.demo.designMode.factory.abstractF.USBehavior;
import com.example.demo.designMode.factory.abstractF.USColor;

/**
 * @author: YJY
 * @date: 2022/3/30 15:16
 * @description:
 */
public class EvilFactory extends AbstractFactory {

  @Override
  public Behavior behavior() {
    return new USBehavior();
  }

  @Override
  public Color color() {
    return new USColor();
  }
}
