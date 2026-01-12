package Test.快递单生成.直接生成;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;

/**
 * @author: YJY
 * @date: 2025/4/23 14:03
 * @description:
 */
public class FieldPosition {
  Rectangle position;
  PdfFont font;
  float fontSize;

  // 构造函数
  public FieldPosition(Rectangle position, PdfFont font, float fontSize) {
    this.position = position;
    this.font = font;
    this.fontSize = fontSize;
  }

  // Getter方法
  public Rectangle getPosition() {
    return position;
  }

  public PdfFont getFont() {
    return font;
  }

  public float getFontSize() {
    return fontSize;
  }
}
