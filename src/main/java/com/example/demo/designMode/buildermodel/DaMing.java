package com.example.demo.designMode.buildermodel;

/**
 * @author: YJY
 * @date: 2024/7/25 11:24
 * @description:
 */
public class DaMing extends Person{

  @Override
  public void builderHand() {
    System.out.println("大手");
  }

  @Override
  public void builderFoot() {
    System.out.println("大脚");
  }

  @Override
  public void builderHead() {
    System.out.println("小头");
  }

  @Override
  public void builderBody() {
    System.out.println("胖子");
  }
}
