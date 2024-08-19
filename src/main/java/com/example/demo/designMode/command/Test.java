package com.example.demo.designMode.command;

/**
 * @author: YJY
 * @date: 2024/8/19 15:35
 * @description:
 */
public class Test {

  public static void main(String[] args) {

    Order order1 = new Order();
    order1.setDiningTable(1);
    order1.getFoodMenu().put("鲍鱼炒饭",1);
    order1.getFoodMenu().put("茅台迎宾",1);

    Order order2 = new Order();
    order2.setDiningTable(3);
    order2.getFoodMenu().put("海参炒面",1);
    order2.getFoodMenu().put("五粮液",1);

    //创建接收者
    Chef receiver = new Chef();

    //将订单和接收者封装成命令对象
    OrderCommand cmd1 = new OrderCommand(receiver,order1);
    OrderCommand cmd2 = new OrderCommand(receiver,order2);

    //创建调用者
    Waiter invoke = new Waiter();
    invoke.setCommands(cmd1);
    invoke.setCommands(cmd2);

    //将订单发送到后厨
    invoke.orderUp();
  }

}
