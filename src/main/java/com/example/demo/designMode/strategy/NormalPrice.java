package com.example.demo.designMode.strategy;

/**
 * @author: YJY
 * @date: 2022/3/31 16:03
 * @description:
 */
public class NormalPrice implements PriceInterface {

  @Override
  public Double returnPrice(Double money) {
    return money;
  }
}
