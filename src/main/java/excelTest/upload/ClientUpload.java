package excelTest.upload;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: YJY
 * @date: 2023/4/24 15:24
 * @description:
 */
public class ClientUpload {

  public static void main(String[] args) {


    try {
      Socket socket = new Socket("localhost", 8888);
      OutputStream outputStream = socket.getOutputStream();
      File file = new File("src/main/resources/clientFile.txt");
      String fileName = file.getName();
      // 将文件名发送到服务器端
      DataOutputStream dos = new DataOutputStream(outputStream);
      dos.writeUTF(fileName);
      // 发送文件数据
      FileInputStream fileInputStream = new FileInputStream(file);
      byte[] buffer = new byte[1024];
      int len;
      while ((len = fileInputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, len);
      }
      fileInputStream.close();
      outputStream.close();
      socket.close();
    }catch (Exception e){
      System.out.println(e);
    }

  }
}
