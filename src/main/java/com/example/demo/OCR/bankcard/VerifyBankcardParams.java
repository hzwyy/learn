package com.example.demo.OCR.bankcard;


import lombok.Data;
import lombok.ToString;

/**
 * @author: YJY
 * @date: 2021/11/4 16:36
 * @description: 银行卡三要素/四要素 入参
 */
@Data
@ToString(callSuper = true)
public class VerifyBankcardParams  {


  private String certificateNo;

  private String name;

  private String bankCardNo;

  private String certificateType;

  private String mobileNo;
}
