package com.example.demo.designMode.factory.normal;

import static com.example.demo.designMode.factory.Operator.getByOperate;


/**
 * @author: YJY
 * @date: 2022/3/30 10:42
 * @description:
 */
public class OperationFactory implements OperationFactoryInterface{

  @Override
  public OperationModel createOperation(String operate) {
     OperationModel operationModel = null;
    switch (getByOperate(operate)) {
      case ADD:
        return new OperationAdd();
      case SUBTRACT:
        return new OperationSubtract();
      default:
        return new OperationAdd();
    }
  }
}
