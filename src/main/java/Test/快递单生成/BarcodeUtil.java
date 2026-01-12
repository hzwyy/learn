package Test.快递单生成;

import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.EAN128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author: YJY
 * @date: 2025/4/15 14:20
 * @description:
 */
public class BarcodeUtil {
  public static byte[] generateGs1Barcode(String code) throws Exception {
    EAN128Bean barcodeGenerator = new EAN128Bean();

    // ✅ 样式参数：完全照搬你之前的设置
    barcodeGenerator.setModuleWidth(0.3);         // 模块宽度（mm）
    barcodeGenerator.setBarHeight(12.0);          // 条码高度（mm）
    barcodeGenerator.setQuietZone(3);             // 左右留白（模块数）
    //barcodeGenerator.setFontSize(2.0);            // 字体大小（mm）
    //barcodeGenerator.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM); // 显示文字位置
    barcodeGenerator.setFontSize(0);              // 关键点：字体大小设为 0（不显示文字）
    barcodeGenerator.setMsgPosition(HumanReadablePlacement.HRP_NONE); // 关键点：不显示文字
    // ✅ 图像输出配置
    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
        300, // 分辨率 DPI
        BufferedImage.TYPE_BYTE_BINARY,
        false,
        0
    );

    try {
      // ✅ 不需要手动加 FNC1，EAN128Bean 会自动添加
      // 注意 code 要是合法 GS1 结构（不加括号）
      // 示例: (01)12345678901231 -> "0112345678901231"
      barcodeGenerator.generateBarcode(canvas, code);

      canvas.finish();
      BufferedImage bufferedImage = canvas.getBufferedImage();

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(bufferedImage, "PNG", baos);
      return baos.toByteArray();
    } finally {
      canvas.finish();
    }
  }
}
