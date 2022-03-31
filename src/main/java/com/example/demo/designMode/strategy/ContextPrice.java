package com.example.demo.designMode.strategy;

/**
 * @author: YJY
 * @date: 2022/3/31 16:16
 * @description: 策略与工厂的结合
 */
public class ContextPrice {

  PriceInterface priceInterface = null;

  public ContextPrice(String type){

    switch (type){
      case "正常":
        priceInterface = new NormalPrice();
        break;
      case "折扣":
        priceInterface = new DiscountPrice(0.8);
        break;
      case "满减":
        priceInterface = new FullReductionPrice(300D,100D);
        break;
      default:
        priceInterface = new NormalPrice();
        break;
    }
  }

  public Double getPriceResult(Double price){

    return priceInterface.returnPrice(price);
  }
}
