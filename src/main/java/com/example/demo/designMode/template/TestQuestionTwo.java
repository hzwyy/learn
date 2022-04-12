package com.example.demo.designMode.template;

/**
 * @author: YJY
 * @date: 2022/4/12 14:54
 * @description:
 */
public class TestQuestionTwo extends TestPaper {

  public TestQuestionTwo(String userName) {
    super(userName);
  }
  @Override
  public String answerOne(){
    return "B";
  }
  @Override
  public String answerTwo(){
    return "C";
  }
}
