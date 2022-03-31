package com.example.demo.designMode.strategy;

/**
 * @author: YJY
 * @date: 2022/3/31 16:06
 * @description:
 */
public class DiscountPrice implements PriceInterface {

  private Double discount = 1D;

  public DiscountPrice(Double discount) {
    this.discount = discount;
  }

  @Override
  public Double returnPrice(Double money) {
    return discount * money;
  }
}
