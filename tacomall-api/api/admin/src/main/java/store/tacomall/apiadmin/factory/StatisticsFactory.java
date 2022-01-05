/***
 * @Author: 码上talk|RC
 * @Date: 2021-03-17 20:36:25
 * @LastEditTime: 2022-01-05 14:02:14
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/factory/StatisticsFactory.java
 * Just do what I think it is right
 */
package store.tacomall.apiadmin.factory;

import store.tacomall.common.enumeration.ExceptionEnum;
import store.tacomall.common.util.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import store.tacomall.apiadmin.strategy.StatisticsStrategy;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class StatisticsFactory {
    @Autowired
    Map<String, StatisticsStrategy> strategys = new ConcurrentHashMap<>(3);

    public StatisticsStrategy getStrategy(String id) {
        StatisticsStrategy strategy = strategys.get(id);
        if (ObjectUtil.isNull(strategy)) {
            ExceptionUtil.throwClientException(ExceptionEnum.PAGE_NOT_EXSIT.getMessage());
        }
        return strategy;
    }
}
