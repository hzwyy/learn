package com.example.demo.designMode.proxy;

/**
 * @author: YJY
 * @date: 2022/4/2 10:44
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    Girl girl = new Girl("小花");
    //通过小慧隐藏了李明的存在 小慧代理了小明送礼物
    XiaoHui xiaoHui = new XiaoHui(girl);
    xiaoHui.giveChocolate();
    xiaoHui.giveDolls();
    xiaoHui.giveFlowers();
  }
}
