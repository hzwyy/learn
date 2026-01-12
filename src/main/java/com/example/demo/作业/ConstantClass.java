package com.example.demo.作业;

import java.util.ArrayList;

/**
 * 用到的常量
 */
public class ConstantClass {

  //存储商品
  public static ArrayList<Product> products = new ArrayList<>();
  //存储记录
  public static ArrayList<SaleRecord> sales = new ArrayList<>();
  //存储用户权限
  public static ArrayList<UserRoleBean> roleBeans = new ArrayList<>();

  static class InitUserRole {
    public InitUserRole() {
      UserRoleBean userRoleBean = new UserRoleBean("小明", new int[]{1, 2, 3, 4, 5,6, 7,8,9});
      roleBeans.add(userRoleBean);
    }
  }
}
