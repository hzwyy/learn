package com.example.demo.designMode.observer;

/**
 * @author: YJY
 * @date: 2024/7/25 15:19
 * @description:
 */
public class XiaoMing extends ObserverAbstract {

  public XiaoMing(String name, ASubjectAbstract aSubjectAbstract) {
    super(name, aSubjectAbstract);
  }

  @Override
  public void updateStatus() {
    System.out.println(name + " 停止了玩游戏,开始工作,因为" + aSubjectAbstract.getStatus());
  }
}
