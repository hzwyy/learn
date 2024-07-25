package com.example.demo.designMode.buildermodel;

/**
 * @author: YJY
 * @date: 2024/7/25 11:25
 * @description:
 */
public class Builder {

  private Person person;

  public Builder(Person person) {
    this.person = person;
  }

  public void CreatePerson() {
    person.builderHead();
    person.builderHand();
    person.builderBody();
    person.builderFoot();
  }
}
