package Test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: YJY
 * @date: 2023/8/13 12:43
 * @description:
 */
public class aaa {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String lens = scanner.next();
    scanner.nextLine();

    String[] series = scanner.nextLine().split(" ");

    if (!lens.matches("^[1-9]\\d*$")) {
      System.out.println("Error in input");
      return;
    }
    int len = Integer.parseInt(lens);

    if (series.length != len) {
      System.out.println("Invalid input");
      return;
    }

    Set<Integer> elements = new HashSet<>();
    Set<Integer> differences = new HashSet<>();

    for (String num : series) {
      int intlenum = Integer.parseInt(num);
      if (intlenum < 0 || intlenum >= len) {
        System.out.println("Invalid input");
        return;
      }
      elements.add(intlenum);
    }

    int prev = Integer.parseInt(series[0]);
    for (int i = 1; i < len; i++) {
      int current = Integer.parseInt(series[i]);
      int diff = Math.abs(current - prev);
      if (diff < 1 || diff >= len || differences.contains(diff)) {
        System.out.println("It is not a Magical Series");
        return;
      }
      differences.add(diff);
      prev = current;
    }

    if (elements.size() != len || differences.size() != len - 1) {
      System.out.println("It is not a Magical Series");
      return;
    }

    System.out.println("It is a Magical Series");
  }

}
