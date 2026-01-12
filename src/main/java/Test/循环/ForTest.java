package Test.循环;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * @author: YJY
 * @date: 2025/3/28 14:40
 * @description:
 */
public class ForTest {

  public static void main(String[] args) {
//    List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g"));
//    Iterator<String> iterator = list.iterator();
//    System.out.println("元素剔除前的数量为:"+list.size());
//    while (iterator.hasNext()) {
//      String it = iterator.next();
//      if (it.equals("a")) {
//        iterator.remove();
//      }
//    }
//    System.out.println("元素剔除后的数量为:"+list.size());

    //  List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f", "f", "g"));
//    list.removeIf(item -> "b".equals(item));
//
//    System.out.println(list);

//    List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f", "f", "g"));
//    //从前向后进行移除 会出现问题
//    for (int i = 0; i < list.size(); i++) {
//      if ("f".equals(list.get(i))) {
//        list.remove(i);
//      }
//    }
//    System.out.println("list中的f元素没有删除干净只删除了第一个");
//    //从后往前移除元素
//    for (int i = list.size() - 1; i >= 0; i--) {
//      if ("b".equals(list.get(i))) {
//        list.remove(i);
//      }
//    }
//    List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f", "f", "g"));
//    for (String item : list) {
//      if ("b".equals(item)) {
//        list.remove(item);
//      }
//    }
//    System.out.println(list.size());

    // List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f", "f", "g"));
    //从前向后进行移除 会出现问题
//    for (int i = 0; i < list.size(); i++) {
//      if ("f".equals(list.get(i))) {
//        list.remove(i);
//        i --;
//      }
//    }
//    System.out.println("list中的f元素没有删除干净只删除了第一个");
//    List<Integer> listA = new ArrayList<>(
//        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
//    List<Integer> listB = new ArrayList<>(
//        Arrays.asList(4, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
//    int number = 0;
//    for (int i = 0; i < listA.size(); i++) {
//      for (int j = 0; j < listB.size(); j++) {
//        if (Objects.equals(listA.get(i), listB.get(j))) {
//          System.out.println("找到了相同的元素:" + listB.get(j));
//          listB.remove(j);
//          j--;
//        }
//        number++;
//      }
//    }
//    System.out.println("查找过程运行的次数为:" + number);






  }
}
