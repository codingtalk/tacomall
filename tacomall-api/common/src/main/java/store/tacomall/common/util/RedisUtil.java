/***
 * @Author: 码上talk|RC
 * @Date: 2021-09-18 14:01:11
 * @LastEditTime: 2021-10-06 14:56:52
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/RedisUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
/***
 * @Author: 码上talk|RC
 * @Date: 2021-08-02 11:32:38
 * @LastEditTime: 2021-09-14 20:05:07
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/cn/tacomall/common/util/RedisUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.common.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.PostConstruct;

@Component
public class RedisUtil {

    protected static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static JedisPool jedisPool;

    @Autowired
    private RedisConfig redisConfig;

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