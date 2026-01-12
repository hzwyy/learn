package Test.循环;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: YJY
 * @date: 2025/3/25 10:13
 * @description:
 */
public class test {

  public static void main(String[] args) {

//    int i = 1;
//    int a = ++i;
//    int b = i++;
//
//    System.out.println(i);
//    System.out.println(a);
//    System.out.println(b);

//    int number = 0;
//    for (String s = "1"; s.equals("1"); ++number) {
//      if (number == 2) {
//        s = "2";
//      }
//    }
//    System.out.println("number输出了" + number + "次");
//
//    int i = 0;
//    for (; i < 3; i++) {
//      if(i == 2){
//        i = 3;
//      }
//    }
//    System.out.println("i输出了" + i + "次");


//    for (int i = 1; i <= 9; i++) {
//      for (int j = 1; j <= i; j++) {
//        System.out.print(j + "×" + i + "=" + (i*j) + "\t");
//      }
//      System.out.println();
//    }
//    List<Integer> list = new ArrayList<>();
//
//    list.add(1);
//    list.add(2);
//    list.add(3);
//
//    for (int i : list){
//      System.out.println(i);
//    }

         a: for(int i = 0; i < 10; i++){
         b: for(int j = 0; j < 10; j++){
            if(i * 10 + j > 29){
              break a;
            }
            System.out.print("" + i + j +"  ");
          }
          System.out.println("\n -------------------------------------- \n");
        }
        System.out.println("输出完毕！");


  }
}
