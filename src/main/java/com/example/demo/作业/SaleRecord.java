package com.example.demo.作业;

/**
 * 商品类出售记录
 */
public class SaleRecord {

  //出售的商品ID
  private int productId;
  //出售的商品名称
  private String productName;
  //出售的数量
  private int productNumber;
  //出售的价格
  private double totalCost;
 //构造方法
  public SaleRecord(int productId, String productName, int productNumber, double totalCost) {
    this.productId = productId;
    this.productName = productName;
    this.productNumber = productNumber;
    this.totalCost = totalCost;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getProductNumber() {
    return productNumber;
  }

  public void setProductNumber(int productNumber) {
    this.productNumber = productNumber;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public String toString() {
    return "商品ID: " + productId + ", 商品名称: " + productName + ", 出售的数量: " + productNumber + ", 出售的价格: " + totalCost;
  }
}
