package excelTest.upload;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: YJY
 * @date: 2023/4/24 15:24
 * @description:
 */
public class ServiceUpLoad {

  public static void main(String[] args) {


    while (true) {
    try {
      ServerSocket serverSocket = new ServerSocket(8888);
      Socket socket = serverSocket.accept();
      InputStream inputStream = socket.getInputStream();
      // 读取客户端发送的文件名
      DataInputStream dis = new DataInputStream(inputStream);
      String fileName = dis.readUTF();
      // 将文件数据写入文件
      FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/service/"+fileName);
      byte[] buffer = new byte[1024];
      int len;
      while ((len = inputStream.read(buffer)) > 0) {
        fileOutputStream.write(buffer, 0, len);
      }
      fileOutputStream.close();
      inputStream.close();
      socket.close();
      serverSocket.close();
      } catch(Exception ioException){
        System.out.println(ioException);
      }
  }
  }
}
