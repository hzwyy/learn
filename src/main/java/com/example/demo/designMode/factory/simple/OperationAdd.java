package com.example.demo.designMode.factory.simple;

/**
 * @author: YJY
 * @date: 2022/3/29 14:42
 * @description:
 */
public class OperationAdd extends OperationModel {

  public OperationAdd(Double numberA, Double numberB) {
    super(numberA, numberB);
  }

  @Override
  public Double getResult() {
    Double result = 0D;
    result = getNumberA() + getNumberB();
    return result;
  }
}
