package com.tacomall.common.bo.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormOrderInfoDataBo {
    // KD

    private String logistic;

    private String senderRealName;

    private String senderMobile;

    private String senderProvince;

    private String senderCity;

    private String senderDistrict;

    private String senderDetail;

    private String receiverRealName;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverDetail;

    private String objectType;

    private BigDecimal objectWeight;

    private BigDecimal ensure;

    private LocalDateTime takeTime;

    private String note;

    // CHF

    private String account;

    private BigDecimal amount;
}