package com.example.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class PdfToWordUtil {

  private PdfToWordUtil() {
  }

  public static void convertPdfToWord(String inputPdfPath, String outputWordPath)
      throws IOException {
    convertPdfToWord(new File(inputPdfPath), new File(outputWordPath));
  }

  public static void convertPdfToWord(File inputPdfFile, File outputWordFile)
      throws IOException {
    validateInput(inputPdfFile, outputWordFile);
    createParentDirectory(outputWordFile);

    try (PDDocument pdfDocument = PDDocument.load(inputPdfFile);
        XWPFDocument wordDocument = new XWPFDocument();
        FileOutputStream outputStream = new FileOutputStream(outputWordFile)) {

      PDFTextStripper stripper = new PDFTextStripper();
      int pageCount = pdfDocument.getNumberOfPages();

      for (int page = 1; page <= pageCount; page++) {
        stripper.setStartPage(page);
        stripper.setEndPage(page);

        String pageText = stripper.getText(pdfDocument);
        writePage(wordDocument, pageText);

        if (page < pageCount) {
          XWPFParagraph paragraph = wordDocument.createParagraph();
          XWPFRun run = paragraph.createRun();
          run.addBreak(BreakType.PAGE);
        }
      }

      wordDocument.write(outputStream);
    }
  }

  private static void writePage(XWPFDocument wordDocument, String pageText) {
    if (pageText == null || pageText.trim().isEmpty()) {
      wordDocument.createParagraph();
      return;
    }

    String[] lines = pageText.split("\\R");
    for (String line : lines) {
      XWPFParagraph paragraph = wordDocument.createParagraph();
      XWPFRun run = paragraph.createRun();
      run.setText(line == null ? "" : line);
    }
  }

  private static void validateInput(File inputPdfFile, File outputWordFile) {
    if (inputPdfFile == null) {
      throw new IllegalArgumentException("Input PDF file cannot be null.");
    }
    if (outputWordFile == null) {
      throw new IllegalArgumentException("Output Word file cannot be null.");
    }
    if (!inputPdfFile.exists() || !inputPdfFile.isFile()) {
      throw new IllegalArgumentException("Input PDF file does not exist: "
          + inputPdfFile.getAbsolutePath());
    }
    if (!inputPdfFile.getName().toLowerCase().endsWith(".pdf")) {
      throw new IllegalArgumentException("Input file must be a PDF: "
          + inputPdfFile.getAbsolutePath());
    }
    if (!outputWordFile.getName().toLowerCase().endsWith(".docx")) {
      throw new IllegalArgumentException("Output file must end with .docx: "
          + outputWordFile.getAbsolutePath());
    }
  }

  private static void createParentDirectory(File outputWordFile) throws IOException {
    File parentFile = outputWordFile.getParentFile();
    if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
      throw new IOException("Failed to create output directory: "
          + parentFile.getAbsolutePath());
    }
  }

  public static void main(String[] args) throws IOException {
    String inputPath = "E:\\学生要学习的知识点\\互联网&银行科技岗JAVA常见面试题合集.pdf";
    String outputPath = "E:\\学生要学习的知识点\\互联网&银行科技岗JAVA常见面试题合集.docx";

    if (args.length == 2) {
      inputPath = args[0];
      outputPath = args[1];
    }

    convertPdfToWord(inputPath, outputPath);
    System.out.println("PDF converted to Word: " + outputPath);
  }
}
