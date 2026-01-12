package Test.单例;


/**
 * @description: 饿汉模式
 */
public class SingletonHungry {

 private  volatile static SingletonHungry instance = new SingletonHungry();

  private SingletonHungry() {

  }

  public static SingletonHungry getInstance() {
    return instance;
  }


}
