package Test.快递单生成;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.ImageDataFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
/**
 * @author: YJY
 * @date: 2025/4/23 10:25
 * @description:
 */
public class QRCodeGenerator {
    public final static int QR_CODE_SIZE = 46;
  // 生成二维码并返回字节流
  public static byte[] generateQRCodeByteArray(String data) throws WriterException, IOException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    Map<EncodeHintType, Object> hintMap = new HashMap<>();
    hintMap.put(EncodeHintType.MARGIN, 1); // 设置二维码的边距
    BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hintMap);

    BufferedImage image = new BufferedImage(QR_CODE_SIZE, QR_CODE_SIZE, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < QR_CODE_SIZE; x++) {
      for (int y = 0; y < QR_CODE_SIZE; y++) {
        image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
      }
    }

    // 将二维码图像写入字节流
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, "PNG", byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
}
