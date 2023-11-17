
package com.tacomall.apiweb.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;

import com.tacomall.apiweb.strategy.PageStrategy;
import com.tacomall.common.enumeration.BizEnum;
import com.tacomall.common.util.ExceptionUtil;

@Service
public class PageFactory {

    @Autowired
    Map<String, PageStrategy> strategys = new ConcurrentHashMap<>();

    public PageStrategy getStrategy(String action) {
        PageStrategy strategy = strategys.get(action);
        if (ObjectUtil.isNull(strategy)) {
            ExceptionUtil.throwClientException(BizEnum.PAGE_NOT_EXSIT.getMessage());
        }
        return strategy;
    }

}
