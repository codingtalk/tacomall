package com.tacomall.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    protected static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.data.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.data.redis.port:6379}")
    private Integer port;

    @Value("${spring.data.redis.jedis.pool.max-active:500}")
    private Integer maxTotal;

    @Value("${spring.data.redis.jedis.pool.max-idle:500}")
    private Integer maxIdle;

    @Value("${spring.data.redis.jedis.pool.min-idle:500}")
    private Integer minIdle;

    @Value("${spring.data.redis.password:123456}")
    private String password;

    @Value("${spring.data.redis.timeout:500}")
    private Integer timeout;

    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        logger.info("JedisPool injected successfully");
        logger.info("redis address：" + host + ":" + port);
        return jedisPool;
    }
}