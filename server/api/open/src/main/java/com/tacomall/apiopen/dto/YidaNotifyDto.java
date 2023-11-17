package com.tacomall.apiopen.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YidaNotifyDto {

    private String thirdNo;

    private Integer pushType;

    private JSONObject contextObj;

}
