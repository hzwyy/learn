package excelTest.download;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: YJY
 * @date: 2023/4/24 15:44
 * @description:
 */
public class ClientDownload {

  public static void main(String[] args) {
    try {


      Socket socket = new Socket("localhost", 9999);
      OutputStream outputStream = socket.getOutputStream();
      // 将文件名发送到服务器端
      DataOutputStream dos = new DataOutputStream(outputStream);
      dos.writeUTF("clientFile.txt");
      // 接收服务器端发送的文件数据
      InputStream inputStream = socket.getInputStream();
      FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/clientFile1.txt");
      byte[] buffer = new byte[1024];
      int len;
      while ((len = inputStream.read(buffer)) > 0) {
        fileOutputStream.write(buffer, 0, len);
      }
      fileOutputStream.close();
      inputStream.close();
      dos.close();
      outputStream.close();
      socket.close();

    }catch (Exception e){

    }

  }
}
