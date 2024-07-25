package com.example.demo.designMode.state;

/**
 * @author: YJY
 * @date: 2023/9/8 16:25
 * @description:
 */
public class StateB extends State {

  @Override
  public void handle(Context context) {
    System.out.println("进入了状态B");
    context.setState(new StateA());
  }
}
