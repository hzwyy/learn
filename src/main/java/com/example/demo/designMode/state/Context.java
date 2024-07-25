package com.example.demo.designMode.state;

/**
 * @author: YJY
 * @date: 2023/9/8 16:25
 * @description:
 */
public class Context {

  private State state;

  public Context(State state) {
    this.state = state;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void Request(){
    state.handle(this);
  }
}
