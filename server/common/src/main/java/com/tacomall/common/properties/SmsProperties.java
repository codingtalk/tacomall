package com.tacomall.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ali.sms")
public class SmsProperties {

    private String endPoint;

    private String accesskey;

    private String accessSecret;

}
