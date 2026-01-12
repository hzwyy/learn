package Test.单例;

import java.io.Serializable;

/**
 * @author: YJY
 * @date: 2025/4/11 11:02
 * @description:
 */
public class Singleton implements Serializable {

  private static volatile Singleton instance;
  // 标志位：记录构造函数是否被调用过
  private static volatile boolean isInitialized = false;

  private Singleton() {
    synchronized (Singleton.class) {
      // 如果已初始化，抛出异常
      if (isInitialized) {
        throw new IllegalStateException("禁止通过反射创建单例！");
      }
      isInitialized = true;
    }
  }
  protected Object readResolve() {
    return instance;
  }

  public static Singleton getInstance() {
    if (instance == null) {
      synchronized (Singleton.class) {
        if (instance == null) {
          instance = new Singleton();
        }
      }
    }
    return instance;
  }

}
