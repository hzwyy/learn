package com.example.demo.designMode.factory.simple;

/**
 * @author: YJY
 * @date: 2022/3/29 14:22
 * @description:
 */
public class Test {


  public static void main(String[] args) {

    OperationModel operationModel = OperationFactory.getResult(100D,200D,Operator.SUBTRACT.getOperate());
    System.out.println(operationModel.getResult());


  }
}
