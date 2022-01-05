/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-10 16:58:58
 * @LastEditTime: 2022-01-05 14:01:52
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/factory/EnumFactory.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;
import store.tacomall.apiopen.strategy.EnumStrategy;
import store.tacomall.common.enumeration.ExceptionEnum;
import store.tacomall.common.util.ExceptionUtil;

@Service
public class EnumFactory {

    @Autowired
    Map<String, EnumStrategy> strategys = new ConcurrentHashMap<>(3);

    public EnumStrategy getStrategy(String id) {
        EnumStrategy strategy = strategys.get(id);
        if (ObjectUtil.isNull(strategy)) {
            ExceptionUtil.throwClientException(ExceptionEnum.PAGE_NOT_EXSIT.getMessage());
        }
        return strategy;
    }

}
