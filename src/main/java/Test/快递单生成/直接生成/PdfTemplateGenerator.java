package Test.快递单生成.直接生成;

import static Test.快递单生成.QRCodeGenerator.QR_CODE_SIZE;
import static Test.快递单生成.QRCodeGenerator.generateQRCodeByteArray;


import Test.快递单生成.BarcodeUtil;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.util.*;

public class PdfTemplateGenerator {


    //加载字体（Arial 字体路径可以改成你自己的）
   static PdfFont arialFont;

  static {
    try {
      arialFont = PdfFontFactory.createFont(
           "C:/Windows/Fonts/arial.ttf",
           PdfEncodings.IDENTITY_H,
           EmbeddingStrategy.FORCE_EMBEDDED
       );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 预设每个字段的文本位置 (x, y, width, height)
   static final Map<String, FieldPosition> FIELD_POSITIONS = new HashMap<String, FieldPosition>() {{
    put("sender_name", new FieldPosition(new Rectangle(10, 360, 175, 20), arialFont, 10));  // (x, y, width, height)
    put("sender_address", new FieldPosition(new Rectangle(10, 350, 175, 20), arialFont, 10));
    put("sender_city", new FieldPosition(new Rectangle(10, 340, 175, 20), arialFont, 10));
    put("sender_state", new FieldPosition(new Rectangle(10, 330, 175, 20), arialFont, 10));
   //  put("sender_zip",  new FieldPosition(new Rectangle(100, 480, 175, 20), arialFont, 10));
     put("receiver_name", new FieldPosition(new Rectangle(50, 270, 175, 20), arialFont, 10));
     put("receiver_address1", new FieldPosition(new Rectangle(50, 260, 175, 20), arialFont, 10));
//    put("receiver_address2", new Rectangle(100, 660, 400, 20));
    put("receiver_city", new FieldPosition(new Rectangle(50, 250, 175, 20), arialFont, 11));
//    put("receiver_state", new Rectangle(100, 620, 200, 20));
//    put("receiver_zip", new Rectangle(100, 600, 100, 20));
//    put("tracking_number", new Rectangle(100, 580, 200, 20));
      put("ship_date",  new FieldPosition(new Rectangle(100, 500, 175, 20), arialFont, 11));
    put("tracking_number",  new FieldPosition(new Rectangle(100, 60, 175, 20), arialFont, 11));
    }};

  public PdfTemplateGenerator() throws IOException {
  }


  // 主入口方法
  public static void batchGenerate(String templatePath, String excelPath, String outputDir)
      throws Exception {
    List<ShippingData> dataList = readExcel(excelPath);

    for (ShippingData data : dataList) {
      String outputPath = outputDir + File.separator + data.trackingNumber + ".pdf";
      fillPdfTemplate(templatePath, outputPath, data);
      System.out.println("生成文件：" + outputPath);
    }
  }

  // 读取Excel数据
  static List<ShippingData> readExcel(String filePath) throws Exception {
    List<ShippingData> dataList = new ArrayList<>();
    try (InputStream is = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(is)) {

      Sheet sheet = workbook.getSheetAt(0); // 第一个工作表
      for (Row row : sheet) {
        if (row.getRowNum() == 0) {
          continue; // 跳过标题行
        }

        ShippingData data = new ShippingData();
        data.senderName = getCellValue(row, 0);
        data.senderAddress = getCellValue(row, 1);
        data.senderCity = getCellValue(row, 2);
        data.senderState = getCellValue(row, 3);
        data.senderZip = getCellValue(row, 4);
        data.receiverName = getCellValue(row, 5);
        data.receiverAddress1 = getCellValue(row, 6);
        data.receiverAddress2 = getCellValue(row, 7);
        data.receiverCity = getCellValue(row, 8);
        data.receiverState = getCellValue(row, 9);
        data.receiverZip = getCellValue(row, 10);
        data.trackingNumber = getCellValue(row, 16);
        data.shipDate = getCellValue(row, 17);

        dataList.add(data);
      }
    }
    return dataList;
  }

  private static void fillPdfTemplate(String templatePath, String outputPath, ShippingData data)
      throws Exception {

    //加载模板文件
    PdfDocument pdfDoc = new PdfDocument(new PdfReader(templatePath), new PdfWriter(outputPath));

    // 获取页面对象
    PdfPage page = pdfDoc.getFirstPage();

    // 创建 PdfCanvas 用于绘制文本
    PdfCanvas pdfCanvas = new PdfCanvas(page);


    //填充文本
    for (Entry<String, FieldPosition> entry : FIELD_POSITIONS.entrySet()) {
      String fieldName = entry.getKey();
      String excelValue = getDataValue(fieldName, data);

      // 获取当前字段的预设位置
      FieldPosition fieldPosition = FIELD_POSITIONS.get(fieldName);
      if (fieldPosition != null) {
        // 使用 PdfCanvas 绘制文本并支持自动换行
        drawTextWithWrap(pdfCanvas, fieldPosition, excelValue);
      }
    }

    //添加条形码（需预先在模板中留出图片位置）
    addBarcode(pdfDoc, data.trackingNumber, 30, 20); // 调整坐标

    //生成二维码的字节流
    byte[] qrCodeBytes = generateQRCodeByteArray(data.trackingNumber);

    //插入二维码到PDF
    com.itextpdf.layout.element.Image qrImage = new com.itextpdf.layout.element.Image(
        ImageDataFactory.create(qrCodeBytes));
    qrImage.setFixedPosition(1, 5, 240, QR_CODE_SIZE);  // 设置二维码位置：页面1，x=100，y=400，宽高=100

    //插入二维码到PDF
    com.itextpdf.layout.element.Image qrImage2 = new com.itextpdf.layout.element.Image(
        ImageDataFactory.create(qrCodeBytes));
    qrImage2.setFixedPosition(1, 300, 10, QR_CODE_SIZE);  // 设置二维码位置：页面1，x=100，y=400，宽高=100

    //将二维码添加到PDF文档
    Document document = new Document(pdfDoc);
    document.add(qrImage);
    document.add(qrImage2);
    pdfDoc.close();
  }

  private static void addBarcode(PdfDocument pdfDoc, String trackingNumber, float x, float y)
      throws Exception {
    // 生成条形码字节
    byte[] barcodeBytes = BarcodeUtil.generateGs1Barcode(trackingNumber);

    // 创建Image对象
    ImageData imageData = ImageDataFactory.create(barcodeBytes);
    Image image = new Image(imageData);
    PdfPage page = pdfDoc.getFirstPage();
    Rectangle pageSize = page.getPageSize();
    float pageWidth = pageSize.getWidth();
    float pageHeight = pageSize.getHeight();

    // 设置条形码宽度
    float barcodeWidth = 320;
    image.setWidth(barcodeWidth);

    // 计算水平居中位置
    float centerX = (pageWidth - barcodeWidth) / 2;

    // 你希望条形码距离顶部 460pt（原代码中的计算方式）
    float targetYFromBottom = pageHeight - 450;

    // 设定固定位置（水平居中，垂直位置保持不变）
    image.setFixedPosition(centerX, targetYFromBottom);

    // 添加到PDF页面
    new Canvas(page, pageSize)
        .add(image)
        .close();
  }

  // 辅助方法：获取单元格值
  private static String getCellValue(Row row, int cellNum) {
    Cell cell = row.getCell(cellNum);
    return cell != null ? cell.toString().trim() : "";
  }

  // 辅助方法：映射数据字段
  private static String getDataValue(String fieldName, ShippingData data) {
    switch (fieldName) {
      case "sender_name":
        return data.senderName;
      case "sender_address":
        return data.senderAddress;
      case "sender_city":
        return data.senderCity;
      case "sender_state":
        return data.senderState;
      case "sender_zip":
        return data.senderZip;
      case "receiver_name":
        return data.receiverName;
      case "receiver_address1":
        return data.receiverAddress1;
      case "receiver_address2":
        return data.receiverAddress2;
      case "receiver_city":
        return data.receiverCity;
      case "receiver_state":
        return data.receiverState;
      case "receiver_zip":
        return data.receiverZip;
      case "tracking_number":
        return data.trackingNumber;
      case "ship_date":
        return "US POSTAGEAND FEES PAlD " + "  "+data.shipDate;
      default:
        return "";
    }
  }

  // 数据实体类
  static class ShippingData {

    String senderName;
    String senderAddress;
    String senderCity;
    String senderState;
    String senderZip;
    String receiverName;
    String receiverAddress1;
    String receiverAddress2;
    String receiverCity;
    String receiverState;
    String receiverZip;
    String trackingNumber;
    String shipDate;
  }


  // 绘制文本并支持自动换行
  private static void drawTextWithWrap(PdfCanvas pdfCanvas, FieldPosition fieldPosition, String text) {
    // 获取字段的位置、字体和字号
    Rectangle rect = fieldPosition.getPosition();
    PdfFont font = fieldPosition.getFont();
    float fontSize = fieldPosition.getFontSize();

    float x = rect.getX();
    float y = rect.getY();
    float width = rect.getWidth();
    float height = rect.getHeight();

    // 设置行高（每行的间距），可以尝试调整这个值来查看效果
    float lineHeight = fontSize * 1.2f; // 行高通常是字体大小的1.2倍
    if (lineHeight < 15) {  // 确保行高不会过小
      lineHeight = 15;
    }

    // 设置字体和字号
    pdfCanvas.setFontAndSize(font, fontSize);

    // 拆分文本为多行
    String[] lines = splitTextToLines(text, font, width);

    // 绘制每行文本
    for (String line : lines) {
      pdfCanvas.beginText();
      pdfCanvas.moveText(x, y);
      pdfCanvas.showText(line);
      pdfCanvas.endText();

      // 向下移动y坐标，绘制下一行
      y -= lineHeight;
    }
  }
  // 拆分文本为多行
  private static String[] splitTextToLines(String text, PdfFont font, float maxWidth) {
    List<String> lines = new ArrayList<>();
    StringBuilder currentLine = new StringBuilder();

    // 逐个单词检查，判断是否超出最大宽度
    String[] words = text.split(" ");
    for (String word : words) {
      String testLine = currentLine + " " + word;
     float width =  getTextWidth(testLine, font);
      if (width <= maxWidth) {
        currentLine.append(" ").append(word);
      } else {
        // 如果当前行宽度超出最大宽度，则将当前行添加到列表，并开始新的一行
        lines.add(currentLine.toString());
        currentLine = new StringBuilder(word);
      }
    }

    // 添加最后一行
    if (currentLine.length() > 0) {
      lines.add(currentLine.toString());
    }

    return lines.toArray(new String[0]);
  }

  // 获取文本宽度
  private static float getTextWidth(String text, PdfFont font) {
    // 获取宽度，iText7 中的 width 是以 1000/1000 字体单位为单位
    float widthInFontUnits = font.getWidth(text);

    // 转换为正常的 PDF 点单位（1 字符单位 = 1000/1000 字体单位）
    return widthInFontUnits / 1000f * 12;  // 这里的 12 是指默认字号，你可以调整为实际使用的字号
  }

  public static void main(String[] args) throws Exception {
    // 使用示例
    String template = "D:/template/template4.pdf";
    String excel = "D:/template/21单.xlsx";
    String output = "D:/template";

    batchGenerate(template, excel, output);
  }
}
