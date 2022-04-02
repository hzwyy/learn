package com.example.demo.designMode.proxy;

/**
 * @author: YJY
 * @date: 2022/4/2 10:35
 * @description:
 */
public class LiMing implements GiveGift {


  Girl girl;

  public LiMing(Girl girl) {
    this.girl = girl;
  }

  @Override
  public void giveDolls() {
    System.out.println("给"+ girl.getName()+"送娃娃");
  }

  @Override
  public void giveFlowers() {
    System.out.println("给"+ girl.getName()+"送鲜花");
  }

  @Override
  public void giveChocolate() {
    System.out.println("给"+ girl.getName()+"送巧克力");
  }
}
