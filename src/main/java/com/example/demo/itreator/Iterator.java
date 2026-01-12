package com.example.demo.itreator;

/**
 * @author: YJY
 * @date: 2023/7/24 16:27
 * @description:
 */
public abstract class Iterator {

  public abstract Object First();

  public abstract Object Next();

  public abstract boolean IsDone();

  public abstract Object CurrentItem();

}
