package com.example.demo.作业;

/**
 * 存储用户权限
 */
public class UserRoleBean {

  String userName;
  int[] role;

  public UserRoleBean(String userName, int[] role) {
    this.userName = userName;
    this.role = role;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int[] getRole() {
    return role;
  }

  public void setRole(int[] role) {
    this.role = role;
  }
}
