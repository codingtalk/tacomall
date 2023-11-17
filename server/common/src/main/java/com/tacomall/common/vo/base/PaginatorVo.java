package com.tacomall.common.vo.base;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class PaginatorVo extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    @Override
    public Object put(String key, Object value) {
        if (value != null) {
            String type = value.getClass().getSimpleName();
            if (type.equals("Timestamp")) {
                Timestamp times = (Timestamp) value;
                LocalDateTime time = times.toLocalDateTime();
                String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return super.put(key, format);
            } else {
                return super.put(key, value);
            }
        }
        return super.put(key, value);
    }
}
