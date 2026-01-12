package Test.list排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: YJY
 * @date: 2025/4/2 14:23
 * @description:
 */
public class ListTest {


  public static void main(String[] args) {

    //排序写在类里面
    List<Student> list = new ArrayList<>();
    list.add(new Student(25, "Alice"));
    list.add(new Student(18, "Bob"));
    list.add(new Student(20, "Charlie"));

    Collections.sort(list);

    System.out.println(list.size());


//    //以下是定制排序
//
//    //匿名内部类
//    Collections.sort(list, new Comparator<Student>() {
//      @Override
//      public int compare(Student s1, Student s2) {
//        int ageDiff = s1.getAge() - s2.getAge();
//        if (ageDiff != 0) return ageDiff;
//        return s1.getName().compareTo(s2.getName()); // 年龄相同则按姓名排序
//      }
//    });

  }
}
