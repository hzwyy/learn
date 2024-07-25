package com.example.demo.designMode.buildermodel;

/**
 * @author: YJY
 * @date: 2024/7/25 11:27
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    Builder builder = new Builder(new XiaoMing());
    builder.CreatePerson();
    System.out.println("------------------------------------");
    Builder builder2 = new Builder(new DaMing());
    builder2.CreatePerson();
  }
}
