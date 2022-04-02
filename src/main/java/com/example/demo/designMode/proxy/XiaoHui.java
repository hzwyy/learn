package com.example.demo.designMode.proxy;

/**
 * @author: YJY
 * @date: 2022/4/2 10:37
 * @description: 小慧代替别人送礼 代理
 */
public class XiaoHui implements GiveGift{

  LiMing liMing;

  public XiaoHui(Girl girl) {
    liMing = new LiMing(girl);
  }

  @Override
  public void giveDolls() {
    liMing.giveDolls();
  }

  @Override
  public void giveFlowers() {
    liMing.giveFlowers();
  }

  @Override
  public void giveChocolate() {
    liMing.giveChocolate();
  }
}
