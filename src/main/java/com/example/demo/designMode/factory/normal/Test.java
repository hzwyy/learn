package com.example.demo.designMode.factory.normal;

import com.example.demo.designMode.factory.Operator;

/**
 * @author: YJY
 * @date: 2022/3/30 10:46
 * @description:通工厂模式 将工厂抽象 消费者知道它所要创建对象的类(产品类)，但不关心如何创建的时候。 而且要修改的话 可以动态创建 而不需要修改原有的类 实现新的类方法
 * 实现新的工厂即可
 */
public class Test {


  public static void main(String[] args) {

    OperationFactoryInterface operationFactoryOne = new OperationFactoryOne();
    OperationModel operationModel = operationFactoryOne.createOperation(Operator.ADD.getOperate());
    operationModel.setNumberA(100D);
    operationModel.setNumberB(200D);
    System.out.println(operationModel.getResult());

    OperationFactoryInterface operationFactoryTwo = new OperationFactoryTwo();
    OperationModel operationModelTwo = operationFactoryTwo
        .createOperation(Operator.DIVIDE.getOperate());
    operationModelTwo.setNumberA(100D);
    operationModelTwo.setNumberB(200D);
    System.out.println(operationModelTwo.getResult());
  }
}
