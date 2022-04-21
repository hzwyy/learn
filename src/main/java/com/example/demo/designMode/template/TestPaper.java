package com.example.demo.designMode.template;

/**
 * @author: YJY
 * @date: 2022/4/12 14:47
 * @description: 此类为模板 其他类直接复用即可
 */
public class TestPaper {

  String userName;

  public TestPaper(String userName) {
    this.userName = userName;
  }

  public void questionOne() {
    System.out.println("问题1:海贼王中谁是要成为海贼王的男人? A:路飞 B:索隆 C:山治");
    System.out.println(userName + " 问题1的答案:" + answerOne());
  }

  public void questionTwo() {
    System.out.println("问题2:海贼王谁是人人果实中贫民状态? A:路飞 B:乔巴 C:战国");
    System.out.println(userName + " 问题2的答案:" + answerTwo());
  }


  public String answerOne() {
    return "";
  }

  public String answerTwo() {
    return "";
  }
}
