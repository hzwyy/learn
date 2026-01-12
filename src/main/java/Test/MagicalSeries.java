package Test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagicalSeries {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int len = scanner.nextInt();
    scanner.nextLine();
    String[] series = scanner.nextLine().split(" ");
    if (len <= 1) {
      System.out.println("Error in input");
      return;
    }

    for (String num : series) {
      if (!num.matches("\\d+")) {
        System.out.println("Invalid input");
        return;
      }

      int intlenum = Integer.parseInt(num);
      if (intlenum < 0 || intlenum >= len) {
        System.out.println("Invalid input");
        return;
      }
    }

    if (isMagicalSeries(series, len)) {
      System.out.println("It is a Magical Series!");
    } else {
      System.out.println("It is not a Magical Series!");
    }
  }

//  private static boolean isMagicalSeries(String[] series, int len) {
//
//    if (series.length != len) {
//      return false;
//    }
//
//    Set<Integer> differences = new HashSet<>();
//    Set<Integer> elements = new HashSet<>();
//
//    for (int i = 1; i < len; i++) {
//      int diff = Math.abs(Integer.parseInt(series[i]) - Integer.parseInt(series[i - 1]));
//      differences.add(diff);
//      elements.add(Integer.parseInt(series[i]));
//    }
//
//    if (differences.size() != len - 1 || !differences.contains(len - 1)) {
//      return false;
//    }
//
//    for (int i = 1; i < len; i++) {
//      if (!elements.contains(i)) {
//        return false;
//      }
//    }
//
//    return true;
//
//
//  }


  private static boolean isMagicalSeries(String[] series, int len) {

    if (series.length != len) {
      return false;
    }
    Set<Integer> differences = new HashSet<>();
    Set<Integer> elements = new HashSet<>();

    for (int i = 1; i < len; i++) {
      int diff = Math.abs(Integer.parseInt(series[i]) - Integer.parseInt(series[i - 1]));
      differences.add(diff);
      elements.add(Integer.parseInt(series[i]));
    }

    if (differences.size() != len - 1 || !differences.contains(len - 1)) {
      return false;
    }

    for (int i = 1; i < len; i++) {
      if (!differences.contains(i)) {
        return false;
      }
    }

    return true;
  }

}