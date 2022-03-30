package com.example.demo.designMode.factory.simple;

import static com.example.demo.designMode.factory.Operator.getByOperate;

/**
 * @author: YJY
 * @date: 2022/3/29 14:05
 * @description:  简单工厂
 *  特点:
 *    他是一个具体的类 非接口抽象类 有一个重要的方法 通过不同的参数返回不同的实现
 *    这个方法一般是静态的 也可以称之为静态工厂
 *  缺点:
 *   扩展性差 扩展一个类的话 除了新增一个实现类还需要改工厂
 *   实现的方法参数必须统一 无法灵活扩展
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
