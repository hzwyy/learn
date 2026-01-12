package Test.单例;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: YJY
 * @date: 2025/4/11 10:28
 * @description: 反射攻击
 */
public class testSing {


  public static void main(String[] args)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    // 获取私有构造函数
    Constructor<SingletonIdler> constructor = SingletonIdler.class.getDeclaredConstructor();
    // 强制访问
    constructor.setAccessible(true);
    // 通过反射创建实例
    SingletonIdler instance = constructor.newInstance(); // ✅ 允许

    System.out.println(instance);

    SingletonHungry s = SingletonHungry.getInstance();

    SingletonHungry s2 = SingletonHungry.getInstance();
    System.out.println(s.hashCode() == instance.hashCode());
    System.out.println(s.hashCode() == s2.hashCode());
  }
}
