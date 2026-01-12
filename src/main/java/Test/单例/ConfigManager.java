package Test.单例;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * 枚举单例实现全局配置管理
 */
public enum ConfigManager {
  INSTANCE;

  private Properties config;

  // 私有构造函数，由JVM保证只调用一次
  ConfigManager() {
    loadConfig();
  }

  // 加载配置文件（模拟延迟加载）
  private void loadConfig() {
    config = new Properties();
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
      if (input != null) {
        config.load(input);
        System.out.println("配置文件加载完成");
      }
    } catch (IOException e) {
      throw new RuntimeException("加载配置文件失败", e);
    }
  }

  // 获取配置项
  public String getProperty(String key) {
    return config.getProperty(key);
  }

  // 示例：获取数据库URL
  public String getDatabaseUrl() {
    return getProperty("db.url");
  }

  public static void main(String[] args) {
    // 获取配置
    String dbUrl = ConfigManager.INSTANCE.getDatabaseUrl();
    System.out.println("数据库地址: " + dbUrl);

    // 尝试反射攻击（会抛出异常）
    try {
      Constructor<ConfigManager> constructor = ConfigManager.class.getDeclaredConstructor();
      constructor.setAccessible(true);
      ConfigManager hacked = constructor.newInstance(); // ❌ 抛出IllegalArgumentException
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}