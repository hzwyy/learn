package Test.list排序;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: YJY
 * @date: 2025/4/2 14:43
 * @description:
 */
public class Student implements Comparable<Student> {
  private int age;
  private String name;

  public Student(int age, String name) {
    this.age = age;
    this.name = name;
  }

  @Override
  public int compareTo(Student o) {
    System.out.println("比较: " + this.age + " vs " + o.age);
    return this.age - o.age;
  }

  public static void main(String[] args) {
    List<Student> list = new ArrayList<>();
    list.add(new Student(25, "A"));
    list.add(new Student(18, "B"));
    list.add(new Student(17, "C"));
    list.add(new Student(20, "C"));
    Collections.sort(list);

    System.out.println(list.size());
  }


}
