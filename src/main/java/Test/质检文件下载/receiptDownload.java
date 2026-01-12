package Test.质检文件下载;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class receiptDownload {

  public static void main(String[] args) {
    // 配置参数
    String inputFile = "F:\\urls.txt";      // 包含URL列表的文本文件
    String outputDir = "F:\\质检旧模式\\11月份\\安徽志湃信息科技有限公司\\回单";    // 下载文件保存目录

    // 创建下载目录
    createDirectory(outputDir);

    try {
      // 读取URL列表
      List<String> urls = readUrlsFromFile(inputFile);
      System.out.println("找到 " + urls.size() + " 个URL");

      // 批量下载
      for (int i = 0; i < urls.size(); i++) {
        String url = urls.get(i);
        try {
          String fileName = getFileNameFromUrl(url, i+1);
          System.out.printf("正在下载 (%d/%d): %s%n", i+1, urls.size(), fileName);

          downloadFile(url, outputDir, fileName);
        } catch (Exception e) {
          System.err.println("下载失败 [" + url + "]: " + e.getMessage());
        }
      }

      System.out.println("下载完成！文件保存在: " + new File(outputDir).getAbsolutePath());
    } catch (Exception e) {
      System.err.println("程序出错: " + e.getMessage());
    }
  }

  // 从文件读取URL列表
  private static List<String> readUrlsFromFile(String filePath) throws IOException {
    return Files.lines(Paths.get(filePath))
        .map(String::trim)
        .filter(line -> !line.isEmpty())
        .filter(line -> line.startsWith("http"))
        .collect(Collectors.toList());
  }

  // 从URL提取文件名
  private static String getFileNameFromUrl(String url, int index) {
    try {
      String path = new URL(url).getPath();
      String fileName = path.substring(path.lastIndexOf('/') + 1);
      return fileName.isEmpty() ? "file_" + index : fileName;
    } catch (Exception e) {
      return "file_" + index;
    }
  }

  // 下载文件方法
  private static void downloadFile(String url, String dir, String fileName) throws IOException {
    URL website = new URL(url);
    Path outputPath = Paths.get(dir, fileName);

    try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(outputPath.toFile())) {
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
  }

  // 创建目录
  private static void createDirectory(String dir) {
    File directory = new File(dir);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }
}