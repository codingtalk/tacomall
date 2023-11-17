package com.tacomall.apiopen.config;

import com.aliyun.oss.OSSClient;
import com.tacomall.apiopen.properties.OssProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfig {
    private volatile static OSSClient client;

    public static String endpoint;

    public static String accessKey;

    public static String accessSecret;

    public static String bucket;

    public static Long expire;

    public static String host;

    public static String callback;

    @Autowired
    public OssConfig(OssProperties properties) {
        endpoint = properties.getEndPoint();
        accessKey = properties.getAccesskey();
        accessSecret = properties.getAccessSecret();
        bucket = properties.getBucket();
        expire = properties.getExpire();
        host = properties.getHost();
        callback = properties.getCallback();
    }

    public static OSSClient getOSSClient() {
        if (client == null) {
            synchronized (OssConfig.class) {
                if (client == null) {
                    client = new OSSClient(endpoint, accessKey, accessSecret);
                }
            }
        }
        return client;
    }
}
