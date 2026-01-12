package Test.循环;

import java.util.Random;
import java.util.Scanner;

/**
 * @author: YJY
 * @date: 2025/3/26 14:21
 * @description:
 */
public class whileTest {

  //比如做一个游戏 你的角色A有一百滴血 被怪物攻击 怪物的攻击伤害范围在10以内 请模拟他收到攻击后的状态
  public static void main(String[] args) {
    Random random = new Random();
    int money = 100;
    while (money > 0) {
      int c = random.nextInt(10);
      money = money - c;
      System.out.println("A受到了" + c + "点攻击,目前剩余血量" + money);
    }
    System.out.println("A死了");
  }
}
