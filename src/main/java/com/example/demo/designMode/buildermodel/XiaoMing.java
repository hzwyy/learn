package com.example.demo.designMode.buildermodel;

/**
 * @author: YJY
 * @date: 2024/7/25 11:24
 * @description:
 */
public class XiaoMing extends Person{

  @Override
  public void builderHand() {
    System.out.println("小手");
  }

  @Override
  public void builderFoot() {
    System.out.println("小脚");
  }

  @Override
  public void builderHead() {
    System.out.println("大头");
  }

  @Override
  public void builderBody() {
    System.out.println("瘦子");
  }
}
