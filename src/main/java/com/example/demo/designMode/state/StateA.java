package com.example.demo.designMode.state;

/**
 * @author: YJY
 * @date: 2023/9/8 16:24
 * @description:
 */
public class StateA  extends State{

  @Override
  public void handle(Context context) {
    System.out.println("进入了状态A");
    context.setState(new StateB());
  }
}
