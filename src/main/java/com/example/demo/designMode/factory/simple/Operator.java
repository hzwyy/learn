package com.example.demo.designMode.factory.simple;

public enum Operator {

  ADD("+","加法"),
  SUBTRACT("-","减法"),
  MULTIPLY("*","乘法"),
  DIVIDE("/","除法");

  private String operate;
  private String desc;

  Operator(String operate, String desc) {
    this.operate = operate;
    this.desc = desc;
  }

  public String getOperate() {
    return operate;
  }

  public String getDesc() {
    return desc;
  }

  static Operator getByOperate(String operate){
    for (Operator operator :Operator.values()){
      if(operator.getOperate().equals(operate)){
        return operator;
      }
    }
    return null;
  }

}
