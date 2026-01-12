package com.example.demo.MyDictionary;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class MyDictionaryServer extends UnicastRemoteObject implements MyDictionaryService {

  private Map<String, String> dictionary;

  protected MyDictionaryServer() throws RemoteException {
    super();
    dictionary = new HashMap<String, String>();
  }

  public static void main(String[] args) throws RemoteException {
    try {
      Registry registry = LocateRegistry.createRegistry(1027);
      registry.rebind("myDictionary", new MyDictionaryServer());
      System.out.println("Server ready");
    } catch (Exception e) {
      System.out.println("Server exception:"+e);
    }
  }

  @Override
  public void add(String word, String meaning) throws RemoteException {
    dictionary.put(word, meaning);
  }

  @Override
  public String search(String word) throws RemoteException {
    return dictionary.get(word);
  }

  @Override
  public void delete(String word) throws RemoteException {
    dictionary.remove(word);
  }
}
