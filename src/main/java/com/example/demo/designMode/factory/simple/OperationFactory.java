package com.example.demo.designMode.factory.simple;

import static com.example.demo.designMode.factory.simple.Operator.getByOperate;

/**
 * @author: YJY
 * @date: 2022/3/29 14:05
 * @description:
 */
public class OperationFactory {


  public static OperationModel getResult(Double numberA,Double numberB,String operate) {
    OperationModel operationModel = null;
    switch (getByOperate(operate)) {
      case ADD:
        return new OperationAdd(numberA,numberB);
      case SUBTRACT:
        return new OperationSubtract(numberA,numberB);
      default:
        return new OperationAdd(numberA,numberB);
    }
  }
}
