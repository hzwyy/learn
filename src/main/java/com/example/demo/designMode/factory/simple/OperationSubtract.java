package com.example.demo.designMode.factory.simple;

/**
 * @author: YJY
 * @date: 2022/3/30 10:12
 * @description:
 */
public class OperationSubtract extends OperationModel {

  public OperationSubtract(Double numberA, Double numberB) {
    super(numberA, numberB);
  }

  @Override
  public Double getResult() {
    Double result = 0D;
    result = getNumberA() - getNumberB();
    return result;
  }
}
