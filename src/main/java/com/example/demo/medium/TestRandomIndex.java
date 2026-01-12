package com.example.demo.medium;

/**
 * @author: YJY
 * @date: 2022/4/25 16:35
 * @description:
 */
public class TestRandomIndex {

  public static void main(String[] args) {

    int[] num = {1, 2, 3, 3, 3};
    RandomIndex randomIndex = new RandomIndex(num);
    System.out.print(randomIndex.pickByHash(3));
    System.out.print(randomIndex.pickByHash(1));
    System.out.print( randomIndex.pickByHash(3));

    System.out.println();
    RandomIndexSampling randomIndexSampling = new RandomIndexSampling(num);
    System.out.print(randomIndexSampling.pickByHash(3));
    System.out.print(randomIndexSampling.pickByHash(1));
    System.out.print(randomIndexSampling.pickByHash(3));
    System.out.println();

  }
}
