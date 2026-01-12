//package com.example.demo.压缩;
//
//import org.apache.pdfbox.multipdf.PDFMergerUtility;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//
//import java.io.File;
//import java.io.IOException;
//
//public class PdfCompressor {
//
//  public static void compressPdf(String inputPath, String outputPath) throws IOException {
//    // 加载PDF文档
//    PDDocument document = PDDocument.load(new File(inputPath));
//
//    // 设置压缩选项
//    document.setAllSecurityToBeRemoved(true); // 移除安全设置
//
//    // 保存时设置压缩选项
//    document.save(outputPath);
//
//    // 关闭文档
//    document.close();
//
//    System.out.println("PDF压缩完成，输出文件: " + outputPath);
//  }
//
//  public static void main(String[] args) {
//    try {
//      String inputFile = "input.pdf";
//      String outputFile = "compressed.pdf";
//      compressPdf(inputFile, outputFile);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//}