package com.tacomall.common.json;

import com.tacomall.common.enumeration.BizEnum;

import lombok.Data;

@Data
public class ResponseJson<T> {

    private Boolean status = false;

    private Integer code = BizEnum.ERROR.getCode();

    private String message = BizEnum.ERROR.getMessage();

    private T data;

    public void ok() {
        this.status = true;
        this.code = BizEnum.OK.getCode();
        this.message = BizEnum.OK.getMessage();
    }

}