package com.example.demo.designMode.garnisher;

/**
 * @author: YJY
 * @date: 2023/6/9 9:56
 * @description: 工厂模式: 第一个抽象类定义了子类的所有方法
 * 而子类实现了这个抽象类.此时有些特定的方法需要扩展.而不是所有类都需要扩展
 * 即可以再实现一个抽象类继承第一个抽象类并进行扩展 也不影响原来的抽象类
 */
public abstract class Component {

  public abstract void operation();
}
