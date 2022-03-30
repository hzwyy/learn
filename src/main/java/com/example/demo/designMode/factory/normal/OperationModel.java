package com.example.demo.designMode.factory.normal;


public class OperationModel {

  private Double numberA = 0D;
  private Double numberB = 0D;

  public OperationModel(Double numberA, Double numberB) {
    this.numberA = numberA;
    this.numberB = numberB;
  }

  public OperationModel() {
  }

  public Double getNumberA() {
    return numberA;
  }

  public void setNumberA(Double numberA) {
    this.numberA = numberA;
  }

  public Double getNumberB() {
    return numberB;
  }

  public void setNumberB(Double numberB) {
    this.numberB = numberB;
  }


  public Double getResult() {
    Double result = 0D;
    return result;
  }
}
