package com.example.demo.designMode.strategy;

/**
 * @author: YJY
 * @date: 2022/3/31 16:08
 * @description:
 */
public class FullReductionPrice implements PriceInterface {

  private Double moneyTotal;
  private Double moneyReturn;

  public FullReductionPrice(Double moneyTotal, Double moneyReturn) {
    this.moneyTotal = moneyTotal;
    this.moneyReturn = moneyReturn;
  }

  @Override
  public Double returnPrice(Double money) {
    if(money > moneyTotal){

      return money - Math.floor(money/moneyTotal) * moneyReturn;
    }
    return money;
  }
}
