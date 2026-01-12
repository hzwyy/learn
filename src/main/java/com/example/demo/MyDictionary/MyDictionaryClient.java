package com.example.demo.MyDictionary;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class MyDictionaryClient {

  public static void main(String[] args) {
    try {

      Registry myRegistry = LocateRegistry.getRegistry("localhost", 1027);
      MyDictionaryService myDictionary = (MyDictionaryService) myRegistry.lookup("myDictionary");
      // add a new word
      myDictionary.add("hamburger", "my favourite food");
      // search for a word
      String meaning = myDictionary.search("hamburger");
      System.out.println("meaning is: " + meaning);
      // delete a word
      myDictionary.delete("hamburger");
    } catch (Exception e) {
      System.err.println("client exception:" + e);
    }
  }
}
