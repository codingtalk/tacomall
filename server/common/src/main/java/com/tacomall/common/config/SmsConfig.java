package com.tacomall.common.config;

import com.aliyun.teaopenapi.models.*;
import com.tacomall.common.properties.SmsProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(SmsProperties.class)
public class SmsConfig {
    private volatile static com.aliyun.dysmsapi20170525.Client client;

    public static String endpoint;

    public static String accessKey;

    public static String accessSecret;

    @Autowired
    public SmsConfig(SmsProperties properties) {
        endpoint = properties.getEndPoint();
        accessKey = properties.getAccesskey();
        accessSecret = properties.getAccessSecret();
    }

    public static com.aliyun.dysmsapi20170525.Client getSmsClient() {
        if (client == null) {
            synchronized (SmsConfig.class) {
                if (client == null) {
                    try {
                        Config config = new Config().setAccessKeyId(accessKey).setAccessKeySecret(accessSecret);
                        config.endpoint = endpoint;
                        client = new com.aliyun.dysmsapi20170525.Client(config);
                    } catch (Exception e) {

                    }
                }
            }
        }
        return client;
    }
}