package com.example.demo.作业;

import static com.example.demo.作业.ConstantClass.products;
import static com.example.demo.作业.ConstantClass.roleBeans;
import static com.example.demo.作业.ConstantClass.sales;
import static com.example.demo.作业.SalesFileManage.loadSalesRecordsFromFile;
import static com.example.demo.作业.SalesFileManage.saveSalesRecordsToFile;

import com.example.demo.作业.ConstantClass.InitUserRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小商店方法类
 */
public class MyLittleStoreSystem implements BasicMethodInterface {

  //初始商品ID
  public static int nextProductId = 1;
  public String storeName;

  public MyLittleStoreSystem(String storeName) {
    //初始化权限
    new InitUserRole();
    this.storeName = storeName;
  }


  public void run() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("--- " + storeName + "的小店铺 ---");
      System.out.println("1. 添加商品");
      System.out.println("2. 修改商品");
      System.out.println("3. 删除商品");
      System.out.println("4. 查看货架");
      System.out.println("5. 搜素货架");
      System.out.println("6. 出售商品");
      System.out.println("7. 出售记录");
      System.out.println("8. 存储出售记录");
      System.out.println("9. 读取本地存储");
      System.out.println("0. 退出");
      int choice = -1;
      while (true) {
        System.out.print("选择编号进行所需的操作: ");
        if (scanner.hasNextInt()) {
          choice = scanner.nextInt();
          break;
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }
      switch (choice) {
        case 1:
          addProduct(scanner);
          break;
        case 2:
          updateProduct(scanner);
          break;
        case 3:
          deleteProduct(scanner);
          break;
        case 4:
          viewAllProducts();
          break;
        case 5:
          searchProduct(scanner);
          break;
        case 6:
          purchaseProduct(scanner);
          break;
        case 7:
          viewSalesRecords();
          break;
        case 8:
          saveRecords();
          break;
        case 9:
          readRecords();
          break;
        case 0:
          System.out.println("已退出系统");
          return;
        default:
          System.out.println("未知选项,请重新选择!");
          System.out.println("");
      }
    }
  }

  /**
   * 添加商品
   **/
  @Override
  public void addProduct(Scanner scanner) {
    try {
      //判断权限
      if (!checkRole(1)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }
      System.out.print("输入商品名称: ");
      String name = scanner.next();
      double price = 0.0d;
      while (true) {
        System.out.print("请输入价格: ");
        if (scanner.hasNextDouble()) {
          price = Math.round(scanner.nextDouble());
          if (price <= 0) {
            System.out.println("请输入正数类型的价格,勿输入其他");
          } else {
            break;
          }
        } else {
          System.out.println("请输入正整数类型的价格,勿输入其他");
          scanner.next();
        }
      }
      int number = 0;
      while (true) {
        System.out.print("输入商品数量: ");
        if (scanner.hasNextInt()) {
          number = scanner.nextInt();
          if (number <= 0) {
            System.out.println("请输入正整数类型的数量,勿输入其他");
          } else {
            break;
          }
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }
      //知识点: nextProductId++ 先赋值再加一  ++nextProductId 先加一再赋值
      Product product = new Product(nextProductId++, name, price, number);
      products.add(product);
      System.out.println("------------");
      System.out.println("商品添加成功!!!");
      System.out.println("------------");
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 修改商品
   **/
  @Override
  public void updateProduct(Scanner scanner) {
    try {
      //判断权限
      if (!checkRole(2)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }
      int id = 0;
      while (true) {
        System.out.print("请输入想要修改的商品ID: ");
        if (scanner.hasNextInt()) {
          id = scanner.nextInt();
          break;
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }

      Product product = findProductByCondition(id);
      if (product == null) {
        System.out.println("此商品不存在!");
        return;
      }

      double price = 0.0d;
      while (true) {
        System.out.print("请输入最新价格: ");
        if (scanner.hasNextDouble()) {
          price = Math.round(scanner.nextDouble());
          if (price <= 0) {
            System.out.println("请输入正数类型的价格,勿输入其他");
          } else {
            break;
          }
        } else {
          System.out.println("请输入正整数类型的价格,勿输入其他");
          scanner.next();
        }
      }

      int number = 0;
      while (true) {
        System.out.print("输入最新商品数量: ");
        if (scanner.hasNextInt()) {
          number = scanner.nextInt();
          if (number <= 0) {
            System.out.println("请输入正整数类型的数量,勿输入其他");
          } else {
            break;
          }
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }

      product.setPrice(price);
      product.setNumber(number);
      System.out.println("修改成功,最新数据为: " + product);
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 删除商品
   **/
  @Override
  public void deleteProduct(Scanner scanner) {
    try {

      //判断权限
      if (!checkRole(3)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }
      int id = 0;
      while (true) {
        System.out.print("请输入想要删除的商品ID: ");
        if (scanner.hasNextInt()) {
          id = scanner.nextInt();
          break;
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }
      Product product = findProductByCondition(id);
      if (product == null) {
        System.out.println("商品不存在!");
        return;
      }
      products.remove(product);
      System.out.println("商品删除成功");
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 货架功能
   **/
  @Override
  public void viewAllProducts() {
    try {

      //判断权限
      if (!checkRole(4)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }
      if (products.isEmpty()) {
        System.out.println("货架暂无商品!");
      } else {
        System.out.println("--- 货架 ---");
        for (Product product : products) {
          System.out.println(product);
        }
      }
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 搜索商品
   **/
  @Override
  public void searchProduct(Scanner scanner) {
    try {

      //判断权限
      if (!checkRole(5)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }
      if (products.isEmpty()) {
        System.out.println("货架暂无商品!");
      } else {
        System.out.print("输入想要搜索的商品名称: ");
        List<Product> productList = findProductByCondition(scanner.next());
        System.out.println("--- 搜索到的商品 ---");
        for (Product product : productList) {
          System.out.println(product);
        }
      }
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 购买商品
   **/
  @Override
  public void purchaseProduct(Scanner scanner) {
    try {
      //判断权限
      if (!checkRole(6)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }

      int id = 0;
      while (true) {
        System.out.print("输入想要购买的商品ID: ");
        if (scanner.hasNextInt()) {
          id = scanner.nextInt();
          break;
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }

      Product product = findProductByCondition(id);
      if (product == null) {
        System.out.println("商品未找到!");
        return;
      }

      int number = 0;
      while (true) {
        System.out.print("输入想要购买的数量: ");
        if (scanner.hasNextInt()) {
          number = scanner.nextInt();
          if (number <= 0) {
            System.out.println("请输入正整数!");
          } else {
            break;
          }
        } else {
          System.out.println("请输入int类型的数字.勿输入其他");
          scanner.next();
        }
      }

      if (number > product.getNumber()) {
        System.out.println("商品数量不足,购买失败!");
        return;
      }

      double totalCost = Math.round(number * product.getPrice());
      product.setNumber(product.getNumber() - number);

      SaleRecord sale = new SaleRecord(id, product.getName(), number, totalCost);
      sales.add(sale);
      System.out.println("购买成功,数据为:" + sale);
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  @Override
  public void saveRecords() {
    try {
      saveSalesRecordsToFile("records.txt");
      System.out.println("存储成功!");
    }catch (Exception e){
      System.out.println("发生错误,请重试");
    }

  }

  @Override
  public void readRecords() {
    try {
      ArrayList<String> records = loadSalesRecordsFromFile("records.txt");
      System.out.println("--- 本地存储的历史记录 ---");
      for (String sale : records) {
        System.out.println(sale);
      }
    }catch (Exception e){
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 出售的历史记录
   **/
  @Override
  public void viewSalesRecords() {

    try {

      //判断权限
      if (!checkRole(7)) {
        System.out.println("您暂无权限操作此选项!");
        return;
      }
      if (sales.isEmpty()) {
        System.out.println("暂无出售记录!");
      } else {
        System.out.println("--- 出售记录 ---");
        for (SaleRecord sale : sales) {
          System.out.println(sale);
        }
      }
    } catch (Exception e) {
      System.out.println("发生错误,请重试");
    }
  }

  /**
   * 检查此用户的权限
   **/
  @Override
  public boolean checkRole(int operation) {
    try {
      if (null == storeName) {
        return false;
      }
      for (UserRoleBean userRoleBean : roleBeans) {
        if (userRoleBean.getUserName().equals(storeName)) {
          for (int o : userRoleBean.getRole()) {
            if (o == operation) {
              return true;
            }
          }

        }
      }
      return false;
    } catch (Exception e) {
      System.out.println("权限检查错误");
      return false;
    }

  }

  /**
   * 根据ID 查询存储的商品
   **/
  public Product findProductByCondition(int id) {
    for (Product product : products) {
      if (product.getId() == id) {
        return product;
      }
    }
    return null;
  }

  /**
   * 根据名称 查询存储的商品 重载
   **/
  public List<Product> findProductByCondition(String storeName) {

    List<Product> productList = new ArrayList<>();
    if (storeName.isEmpty()) {
      return productList;
    }
    for (Product product : products) {
      if (storeName.contains(product.getName()) || product.getName().contains(storeName)) {
        productList.add(product);
      }
    }
    return productList;
  }
}
