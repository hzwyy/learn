package com.example.demo.作业;

import java.util.Scanner;

/**
 * 接口方法类
 **/
public interface BasicMethodInterface {

  //添加
  void addProduct(Scanner scanner);
  //修改
  void updateProduct(Scanner scanner);
  //删除
  void deleteProduct(Scanner scanner);
  //查看全部
  void viewAllProducts();
  //搜索商品
  void searchProduct(Scanner scanner);
  //购买
  void purchaseProduct(Scanner scanner);
  //写入文件
  void saveRecords();
  //读取文件
  void readRecords();
  //出售记录
  void viewSalesRecords();

  boolean checkRole(int operation);
}
