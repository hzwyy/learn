package com.example.demo.designMode.state;

/**
 * @author: YJY
 * @date: 2023/9/8 16:29
 * @description:
 */
public class Main {

  public static void main(String[] args) {

    Context c = new Context(new StateA());
    c.Request();
    c.Request();
    c.Request();
    c.Request();
    c.Request();
    c.Request();

  }
}
