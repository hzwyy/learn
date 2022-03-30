package com.example.demo.designMode.factory.normal;

import com.example.demo.designMode.factory.Operator;

/**
 * @author: YJY
 * @date: 2022/3/30 10:46
 * @description:
 */
public class Test {


  public static void main(String[] args) {

    OperationFactory operationFactory = new OperationFactory();
    OperationModel operationModel = operationFactory.createOperation(Operator.ADD.getOperate());
    operationModel.setNumberA(100D);
    operationModel.setNumberB(200D);
    System.out.println(operationModel.getResult());
  }
}
