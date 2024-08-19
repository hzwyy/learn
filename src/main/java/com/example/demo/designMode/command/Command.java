package com.example.demo.designMode.command;

/**
 * @author: YJY
 * @date: 2024/8/19 15:29
 * @description: 抽象命令接口
 */
public interface Command {

  //只需要定义一个统一的执行方法
  void execute();
}
