package com.example.demo.designMode.factory.normal;

import static com.example.demo.designMode.factory.Operator.getByOperate;


/**
 * @author: YJY
 * @date: 2022/3/30 10:42
 * @description: 普通工厂模式   将工厂抽象 消费者知道它所要创建对象的类(产品类)，但不关心如何创建的时候。
 */
public class OperationFactoryOne implements OperationFactoryInterface {

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
