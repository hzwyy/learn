package com.example.demo.designMode.template;

/**
 * @author: YJY
 * @date: 2022/4/12 14:54
 * @description:
 */
public class TestQuestionOne extends TestPaper {

  public TestQuestionOne(String userName) {
    super(userName);
  }

  @Override
  public String answerOne() {
    return "A";
  }

  @Override
  public String answerTwo() {
    return "A";
  }
}
