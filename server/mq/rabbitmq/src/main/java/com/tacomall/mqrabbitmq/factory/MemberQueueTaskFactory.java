package com.tacomall.mqrabbitmq.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;

import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.mqrabbitmq.strategy.MemberQueueTaskStrategy;

@Service
public class MemberQueueTaskFactory {

    @Autowired
    Map<String, MemberQueueTaskStrategy> strategys = new ConcurrentHashMap<>();

    public MemberQueueTaskStrategy getStrategy(String id) {
      MemberQueueTaskStrategy strategy = strategys.get(id);
        if (ObjectUtil.isNull(strategy)) {
            ExceptionUtil.throwClientException("会员队列任务不存在");
        }
        return strategy;
    }

}
