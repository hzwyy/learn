package excelTest.download;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: YJY
 * @date: 2023/4/24 15:44
 * @description:
 */
public class ServiceDownload {

  public static void main(String[] args) {

    while (true){
    try {
      ServerSocket serverSocket = new ServerSocket(9999);
      Socket socket = serverSocket.accept();
      OutputStream outputStream = socket.getOutputStream();
      // 读取客户端发送的文件名
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      String fileName = dis.readUTF();
      // 发送文件数据
      FileInputStream fileInputStream = new FileInputStream("src/main/resources/service/"+fileName);
      byte[] buffer = new byte[1024];
      int len;
      while ((len = fileInputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, len);
      }
      fileInputStream.close();
      outputStream.close();
      socket.close();
      serverSocket.close();
    }catch (Exception e){}
  }
  }
}
