package com.example.demo.designMode.factory.simple;

import com.example.demo.designMode.factory.Operator;

/**
 * @author: YJY
 * @date: 2022/3/29 14:22
 * @description:
 *  简单工厂
 *    特点:
 *       他是一个具体的类 非接口抽象类 有一个重要的方法 通过不同的参数返回不同的实现
 *       这个方法一般是静态的 也可以称之为静态工厂
 *     缺点:
 *       扩展性差 扩展一个类的话 除了新增一个实现类还需要改工厂
 *       实现的方法参数必须统一 无法灵活扩展
 */
public class Test {


  public static void main(String[] args) {

    OperationModel operationModel = OperationFactory.getResult(100D,200D, Operator.SUBTRACT.getOperate());
    System.out.println(operationModel.getResult());


  }
}
