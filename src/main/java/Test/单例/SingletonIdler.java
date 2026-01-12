package Test.单例;


/**
 * @description: 懒汉模式
 */
public class SingletonIdler {

  // volatile 保证可见性 禁止指令重排
  private static volatile SingletonIdler instance;

  private SingletonIdler() {

  }

  public static SingletonIdler getInstance() {
    //双重检查
    if (instance == null) {
      synchronized (SingletonIdler.class) {
        if (instance == null) {
          instance = new SingletonIdler();
        }
      }
    }
    return instance;
  }


}
