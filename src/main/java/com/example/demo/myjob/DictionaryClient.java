package com.example.demo.myjob;


import java.io.IOException;
import java.net.Socket;

public class DictionaryClient {

  static Socket client = null;
  public static void main(String[] args) {

    CilentUI cui = new CilentUI();
    try {
      client = new Socket("localhoat",8888);
    } catch (IOException ioException) {
      System.out.println("client:socket ioException");
    }
  }
}
