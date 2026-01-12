package com.example.demo.作业;

/**
 * 商品类
 */
public class Product {
  //商品ID
  private int id;
  //商品名称
  private String name;
  //商品价格
  private double price;
  //商品数量
  private int number;
  //构造方法
  public Product(int id, String name, double price, int number) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.number = number;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getNumber() {
    return number;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String toString() {
    return "编号: " + id + ", 商品名称: " + name + ", 商品价格: " + price + ", 剩余数量: " + number;
  }
}
