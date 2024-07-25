package com.example.demo.designMode.observer;

/**
 * @author: YJY
 * @date: 2024/7/25 15:21
 * @description:
 */
public interface SubjectAbstract {

  void addObserver(ObserverAbstract observerAbstract);

  void removeObserver(ObserverAbstract observerAbstract);

  void announcement();
}
