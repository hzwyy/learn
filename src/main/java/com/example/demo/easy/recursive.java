package com.example.demo.easy;

/**
 * @author: YJY
 * @date: 2022/3/17 15:52
 * @description:
 */
public class recursive {


 public static int recursive (int n){
   if(n == 1){
     return n;
   }
   System.out.println("n = "+n);
   return n * recursive(n-1);
 }

  public static void main(String[] args) {

    System.out.println(recursive(8));
    System.out.println(1*2*3*4*5*6*7*8);
  }
}
