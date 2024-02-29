
package com.tacomall.apiadmin.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;

import com.tacomall.apiadmin.strategy.ApproveStrategy;
import com.tacomall.common.enumeration.BizEnum;
import com.tacomall.common.util.ExceptionUtil;

@Service
public class ApproveFactory {

    @Autowired
    Map<String, ApproveStrategy> strategys = new ConcurrentHashMap<>();

    public ApproveStrategy getStrategy(String action) {
        ApproveStrategy strategy = strategys.get(action);
        if (ObjectUtil.isNull(strategy)) {
            ExceptionUtil.throwClientException(BizEnum.PAGE_NOT_EXSIT.getMessage());
        }
        return strategy;
    }

}
