package com.example.demo.OCR.bankcard;

import lombok.Data;

/**
 * @author: YJY
 * @date: 2025/3/12 17:24
 * @description:
 */
@Data
public class VerifyBankcardResult {

  private Boolean cardStatus;
  private String cardStatusMsg;
  private String bank;
}
