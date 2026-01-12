package com.example.demo.myjob;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class DictionaryServer {

  static String fileName = "dictionary.txt";
  static ConcurrentHashMap<String, String> dict = new ConcurrentHashMap<String, String>();

  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    Socket request = null;

    try {
      serverSocket = new ServerSocket(8888);
    } catch (BindException e) {
      System.out.println("The port number already exists! Replace it");
    } catch (IOException e) {
      System.out.println("IO exception! Please check your service");
    }
    try {
      readDictionary();
    }catch (IOException ioException){
      System.out.println("readDictionary method fail! Please check the method");
    }

    while (true){
      try {
        request = serverSocket.accept();
      } catch (IOException ioException) {
        System.out.println("server:socket ioException");
        return;
      }

      ThreadPoolUtils.getThread().execute(()->{
        //实现交互
      });
    }



  }


  public static void readDictionary() throws IOException {

    File file = new File(fileName);
    if (file.isFile() && file.exists()) {

      InputStreamReader inputStreamReader = null;
      BufferedReader bufferedReader = null;

      try {
        inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        bufferedReader = new BufferedReader(inputStreamReader);
      } catch (UnsupportedEncodingException e) {
        System.out.println("unsupported encoding type! Please check the file");
      } catch (FileNotFoundException e) {
        System.out.println("File not found! Please check the file path");
      }

      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        String[] lines = line.split(":");
        dict.put(lines[0], lines[1]);
      }
      inputStreamReader.close();
      bufferedReader.close();

    }
  }
}
