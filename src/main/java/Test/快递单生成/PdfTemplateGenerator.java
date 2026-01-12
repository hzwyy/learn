package Test.快递单生成;

/**
 * @author: YJY
 * @date: 2025/4/15 14:13
 * @description:
 */

import static Test.快递单生成.QRCodeGenerator.generateQRCodeByteArray;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;
import java.io.File;

import java.util.*;


public class PdfTemplateGenerator {

  // 模板字段映射配置（需先用Adobe Acrobat创建表单字段）
  private static final Map<String, String> FIELD_MAPPING = new HashMap<String, String>() {{
    put("sender_name", "寄件人");
    put("sender_address", "寄件地址");
    put("sender_city", "寄件城市");
    put("sender_state", "寄件省份");
    put("sender_zip", "寄件邮编");
    put("receiver_name", "收件人");
    put("receiver_address1", "收件地址1");
    put("receiver_address2", "收件地址2");
    put("receiver_city", "收件城市");
    put("receiver_state", "收件省份");
    put("receiver_zip", "收件邮编");
    put("tracking_number", "运单号");
    put("ship_date", "打单日期");
  }};

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

  // 填充PDF模板
  private static void fillPdfTemplate(String templatePath, String outputPath, ShippingData data)
      throws Exception {
    //加载字体（Arial 字体路径可以改成你自己的）
//    PdfFont font = PdfFontFactory.createFont(
//        "C:/Windows/Fonts/arial.ttf",
//        PdfEncodings.IDENTITY_H,
//        EmbeddingStrategy.FORCE_EMBEDDED
//    );
    // 1. 加载模板文件
    PdfDocument pdfDoc = new PdfDocument(new PdfReader(templatePath), new PdfWriter(outputPath));

    PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

    // 2. 填充文本字段
    for (Map.Entry<String, String> entry : FIELD_MAPPING.entrySet()) {
      String fieldName = entry.getKey();
      String excelValue = getDataValue(fieldName, data);

      PdfFormField field = form.getField(fieldName);
      if (field != null) {
        // 字段处理增强（在填充字段的循环内）
          field.setValue(excelValue)
              //.setFont(font)
              .setBackgroundColor(null) // ✅ 彻底清除背景
              .setBorderWidth(0)
              .setVisibility(PdfFormField.VISIBLE)
              .setReadOnly(false)
              .setColor(ColorConstants.BLACK)
              .setBorderColor(null); // 额外清除边框颜色
          // 关键：强制生成新外观
          field.regenerateField();

      }
    }
    // 3. 添加条形码（需预先在模板中留出图片位置）
    addBarcode(pdfDoc, data.trackingNumber, 30, 20); // 调整坐标

    // 3. 生成二维码的字节流
    byte[] qrCodeBytes = generateQRCodeByteArray(data.trackingNumber);

    // 4. 插入二维码到PDF
    com.itextpdf.layout.element.Image qrImage = new com.itextpdf.layout.element.Image(ImageDataFactory.create(qrCodeBytes));
    qrImage.setFixedPosition(1, 5, 240, 40);  // 设置二维码位置：页面1，x=100，y=400，宽高=100

    // 将二维码添加到PDF文档
    Document document = new Document(pdfDoc);
    document.add(qrImage);
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
        return "Ship Date: "+data.shipDate;
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


  public static void main(String[] args) throws Exception {
    // 使用示例
    String template = "D:/template/template3.pdf";
    String excel = "D:/template/21单.xlsx";
    String output = "D:/template";

    batchGenerate(template, excel, output);
  }
}
