package com.tacomall.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tacomall.common.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.PostConstruct;

@Component
public class RedisUtil {

    protected static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static JedisPool jedisPool;

    @Autowired
    RedisConfig redisConfig;

    @PostConstruct
    public void init() {
        jedisPool = redisConfig.redisPoolFactory();
    }

    public String set(String key, String value, Integer indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error("jedis set error：" + e.getMessage());
            return "0";
        } finally {
            jedis.close();
        }
    }

    public String set(String key, String value, Integer indexdb, Long expireSecond) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            jedis.select(indexdb);
            SetParams params = new SetParams().nx().ex(expireSecond);
            return jedis.set(key, value, params);
        } catch (Exception e) {
            logger.error("jedis set error：" + e.getMessage());
            return "0";
        } finally {
            jedis.close();
        }
    }

    public long incrBy(String key, long increment, Integer indexdb) {
        Jedis jedis = null;
        long value = 0L;

        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.incrBy(key, increment);
        } catch (Exception e) {
            logger.error("jedis get error：" + e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }

    public String get(String key, Integer indexdb) {
        Jedis jedis = null;
        String value = null;

        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("jedis get error：" + e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }

    public String del(String key, Integer indexdb) {
        Jedis jedis = null;
        String value = null;

        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            jedis.del(key);
        } catch (Exception e) {
            logger.error("jedis get error：" + e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }

    public String getSet(String key, String val, Integer indexdb) {
        Jedis jedis = null;
        String value = null;

        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.getSet(key, val);
        } catch (Exception e) {
            logger.error("jedis get error：" + e.getMessage());
        } finally {
            jedis.close();
        }
        return value;
    }

}