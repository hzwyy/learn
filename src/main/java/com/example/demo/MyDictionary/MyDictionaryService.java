package com.example.demo.MyDictionary;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyDictionaryService extends Remote {

  void add(String word, String meaning) throws RemoteException;

  String search(String word) throws RemoteException;

  void delete(String word) throws RemoteException;
}
