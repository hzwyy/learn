package com.example.demo.eth;

import org.bitcoinj.crypto.MnemonicCode;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

public class MultiChainMnemonicScanner {

  static final String ETH_RPC = "https://mainnet.infura.io/v3/YOUR_INFURA_KEY"; // 替换为你的 Infura Key
  static final String BSC_RPC = "https://bsc-dataseed.binance.org/";
  static final String POLYGON_RPC = "https://polygon-rpc.com/";

  static final String OUTPUT_FILE = "found_wallets.txt";
  static final BigDecimal MIN_USD_VALUE = BigDecimal.valueOf(10); // 10 USD 阈值

  // 手动设定汇率（也可以后期接入 API 获取）
  static final Map<String, BigDecimal> PRICE_USD = new HashMap<>();

  static {
    PRICE_USD.put("ETH", BigDecimal.valueOf(3000));
    PRICE_USD.put("BNB", BigDecimal.valueOf(600));
    PRICE_USD.put("MATIC", BigDecimal.valueOf(0.7));
  }

  public static void main(String[] args) throws Exception {

    // 设置全局代理
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "7890");
    System.setProperty("https.proxyHost", "127.0.0.1");
    System.setProperty("https.proxyPort", "7890");

    SecureRandom random = new SecureRandom();
    MnemonicCode mnemonicCode = new MnemonicCode();

    // 初始化节点
    Map<String, Web3j> web3Map = new HashMap<>();
    web3Map.put("ETH", Web3j.build(new HttpService(ETH_RPC)));
    web3Map.put("BNB", Web3j.build(new HttpService(BSC_RPC)));
    web3Map.put("MATIC", Web3j.build(new HttpService(POLYGON_RPC)));

    int count = 0;
    while (true) {
      count++;

      // 生成助记词
      byte[] entropy = new byte[16];
      random.nextBytes(entropy);
      List<String> mnemonicWords = mnemonicCode.toMnemonic(entropy);
      String mnemonic = String.join(" ", mnemonicWords);

      // 派生地址（m/44'/60'/0'/0/0）
      byte[] seed = MnemonicUtils.generateSeed(mnemonic, null);
      Bip32ECKeyPair masterKey = Bip32ECKeyPair.generateKeyPair(seed);
      int[] path = {44 | Bip32ECKeyPair.HARDENED_BIT, 60 | Bip32ECKeyPair.HARDENED_BIT,
          0 | Bip32ECKeyPair.HARDENED_BIT, 0, 0};
      Bip32ECKeyPair derived = Bip32ECKeyPair.deriveKeyPair(masterKey, path);
      Credentials credentials = Credentials.create(derived);
      String address = credentials.getAddress();

      System.out.printf("尝试 #%d | 地址: %s%n", count, address);

      // 遍历链查询余额
      for (Map.Entry<String, Web3j> entry : web3Map.entrySet()) {
        String chain = entry.getKey();
        Web3j web3 = entry.getValue();
        try {
          EthGetBalance balanceResponse = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
          BigInteger wei = balanceResponse.getBalance();
          BigDecimal nativeBalance = Convert.fromWei(new BigDecimal(wei), Convert.Unit.ETHER);
          BigDecimal usdValue = nativeBalance.multiply(PRICE_USD.get(chain));

          System.out.printf(" - [%s] 余额: %.8f，≈ $%.2f%n", chain, nativeBalance, usdValue);

          if (usdValue.compareTo(MIN_USD_VALUE) >= 0) {
            saveToFile(chain, address, mnemonic, nativeBalance, usdValue);
            System.out.println(" 🎯 满足条件，已写入文件");
          }

        } catch (Exception e) {
          System.err.println("❌ 链 " + chain + " 查询失败: " + e.getMessage());
        }
      }
    }
  }

  private static void saveToFile(String chain, String address, String mnemonic,
      BigDecimal nativeBalance, BigDecimal usdValue) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
      writer.write("链: " + chain + "\n");
      writer.write("地址: " + address + "\n");
      writer.write("助记词: " + mnemonic + "\n");
      writer.write("原生币余额: " + nativeBalance.toPlainString() + "\n");
      writer.write("估算美元: $" + usdValue.toPlainString() + "\n");
      writer.write("========================================\n");
    } catch (Exception e) {
      System.err.println("❌ 写入文件失败: " + e.getMessage());
    }
  }
}
