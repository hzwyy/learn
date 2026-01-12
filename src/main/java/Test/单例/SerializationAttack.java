package Test.单例;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: YJY
 * @date: 2025/4/11 14:08
 * @description:
 */
public class SerializationAttack {
  public static void main(String[] args) throws Exception {
    Singleton s1 = Singleton.getInstance();

    // 序列化到文件
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\singleton.ser"))) {
      oos.writeObject(s1);
    }

    // 反序列化
    Singleton s2;
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\singleton.ser"))) {
      s2 = (Singleton) ois.readObject();
    }

    System.out.println("s1 == s2 : " + (s1 == s2)); // 输出 false，单例被破坏！
  }
}
