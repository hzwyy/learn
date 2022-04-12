package com.example.demo.designMode.template;

/**
 * @author: YJY
 * @date: 2022/4/12 14:56
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    TestPaper xiaoMing = new TestQuestionOne("小明");
    xiaoMing.questionOne();
    xiaoMing.questionTwo();
    TestPaper xiaoFang = new TestQuestionTwo("小芳");
    xiaoFang.questionOne();
    xiaoFang.questionTwo();
  }
}
