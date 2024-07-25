package com.example.demo.designMode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: YJY
 * @date: 2024/7/25 15:22
 * @description:
 */
public class ASubjectAbstract implements SubjectAbstract {

  private List<ObserverAbstract> abstractList = new ArrayList<>();

  private String status;

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public List<ObserverAbstract> getAbstractList() {
    return abstractList;
  }

  @Override
  public void addObserver(ObserverAbstract observerAbstract) {
    abstractList.add(observerAbstract);
  }

  @Override
  public void removeObserver(ObserverAbstract observerAbstract) {
    abstractList.remove(observerAbstract);
  }

  @Override
  public void announcement() {
    for (ObserverAbstract obs : abstractList) {
      obs.updateStatus();
    }
  }
}
