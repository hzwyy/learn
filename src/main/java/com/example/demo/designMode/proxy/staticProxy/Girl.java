package com.example.demo.designMode.proxy.staticProxy;

/**
 * @author: YJY
 * @date: 2022/4/2 10:38
 * @description:
 */
public class Girl {

  String name;

  public Girl(String name) {
    this.name = name;
  }

  public Girl() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
