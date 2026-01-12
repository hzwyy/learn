package com.example.demo.作业;

/**
 * 在这里给用户赋访问权限
 */
public class UserPermission {

  public static boolean checkPermission(String userRole, String requiredRole) {
    // 这里简单的权限检查逻辑
    return userRole.equals(requiredRole);
  }
}
