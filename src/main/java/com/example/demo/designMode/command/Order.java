package com.example.demo.designMode.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: YJY
 * @date: 2024/8/19 15:27
 * @description:  订单类
 */
public class Order {

  //餐桌号码
  private int diningTable;

  //存储菜名与份数
  private Map<String,Integer> foodMenu = new HashMap<>();


  public int getDiningTable() {
    return diningTable;
  }

  public void setDiningTable(int diningTable) {
    this.diningTable = diningTable;
  }

  public Map<String, Integer> getFoodMenu() {
    return foodMenu;
  }

  public void setFoodMenu(Map<String, Integer> foodMenu) {
    this.foodMenu = foodMenu;
  }
}
