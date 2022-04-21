package com.example.demo.designMode.strategy;

/**
 * @author: YJY
 * @date: 2022/3/31 16:21
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    ContextPrice contextPrice = new ContextPrice("满减");
    System.out.println(contextPrice.getPriceResult(1000D));
  }
}
