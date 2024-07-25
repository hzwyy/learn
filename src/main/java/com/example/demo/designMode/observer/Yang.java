package com.example.demo.designMode.observer;

/**
 * @author: YJY
 * @date: 2024/7/25 15:19
 * @description:
 */
public class Yang extends ObserverAbstract {


  public Yang(String name,
      ASubjectAbstract aSubjectAbstract) {
    super(name, aSubjectAbstract);
  }

  @Override
  public void updateStatus() {
    System.out.println(name + " 停止了撸毛,开始工作,因为:"+aSubjectAbstract.getStatus());
  }
}
