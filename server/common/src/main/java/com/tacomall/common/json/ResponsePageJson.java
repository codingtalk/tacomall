package com.tacomall.common.json;

import java.util.HashMap;
import java.util.Map;

import com.tacomall.common.enumeration.BizEnum;

import lombok.Data;

@Data
public class ResponsePageJson<T> {

    private Boolean status = false;

    private Integer code = BizEnum.ERROR.getCode();

    private String message = BizEnum.ERROR.getMessage();

    private T data;

    private Map<String, Object> page = new HashMap<>();

    public void ok() {
        this.status = true;
        this.code = BizEnum.OK.getCode();
        this.message = BizEnum.OK.getMessage();
    }

    public void buildPage(long pageIndex, long pageSize, long total) {
        page.put("pageIndex", pageIndex);
        page.put("pageSize", pageSize);
        page.put("total", total);
    }

}
