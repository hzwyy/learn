package com.example.demo.designMode.observer;

/**
 * @author: YJY
 * @date: 2024/7/25 15:23
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    ASubjectAbstract subjectAbstract = new ASubjectAbstract();
    XiaoMing xiaoMing = new XiaoMing("小明", subjectAbstract);
    Yang yang = new Yang("杨", subjectAbstract);
    subjectAbstract.addObserver(xiaoMing);
    subjectAbstract.addObserver(yang);

    subjectAbstract.setStatus("老板回来啦");
    subjectAbstract.announcement();
  }
}
