package com.example.demo.designMode.factory.abstractF.factory;

import com.example.demo.designMode.factory.abstractF.Behavior;
import com.example.demo.designMode.factory.abstractF.Color;

/**
 * @author: YJY
 * @date: 2022/3/30 15:04
 * @description: 可以在这里面任意组装需要的行为 类似于组装衣服搭配 将抽象工厂进一步抽象
 *  可以抽象多次
 */
public abstract class AbstractFactory {

  public abstract Behavior behavior();

  public abstract Color color();

}
