/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-10 16:58:58
 * @LastEditTime: 2022-01-05 14:02:01
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/factory/PageFactory.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;

import store.tacomall.common.enumeration.ExceptionEnum;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.apima.strategy.PageStrategy;

@Service
public class PageFactory {

    @Autowired
    Map<String, PageStrategy> strategys = new ConcurrentHashMap<>();

    public PageStrategy getStrategy(String id) {
        PageStrategy strategy = strategys.get(id);
        if (ObjectUtil.isNull(strategy)) {
            ExceptionUtil.throwClientException(ExceptionEnum.PAGE_NOT_EXSIT.getMessage());
        }
        return strategy;
    }

}
