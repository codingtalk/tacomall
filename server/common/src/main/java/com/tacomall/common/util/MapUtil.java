package com.tacomall.common.util;

import org.apache.commons.beanutils.BeanMap;
import java.util.Map;
import java.util.HashMap;

public class MapUtil {
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        BeanMap beanMap = new BeanMap(obj);
        for (Object key : beanMap.keySet()) {
            if (key.toString().equals("class")) {
                continue;
            }
            map.put(key.toString(), beanMap.get(key));
        }
        return map;
    }
}
