package com.example.demo.designMode.observer;

/**
 * @author: YJY
 * @date: 2024/7/25 15:14
 * @description:
 */
public abstract class ObserverAbstract {

    String name;
    ASubjectAbstract aSubjectAbstract;

  public ObserverAbstract(String name, ASubjectAbstract aSubjectAbstract) {
    this.name = name;
    this.aSubjectAbstract = aSubjectAbstract;
  }

  public abstract void updateStatus();

}
