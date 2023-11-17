
package com.tacomall.common.enumeration;

public enum BizEnum {
    OK(2000, "正确响应"), ERROR(2001, "服务异常"), REQUEST_QUERY_REQUIRE(2002, "json query 不能为空"),
    REQUEST_QUERY_UNMATCH(2002, "json query 参数错误"), REQUEST_TOO_BUSY(2003, "系统繁忙，稍后重试~"), USER_NOT_EXIST(2100, "员工不存在"),
    PAGE_NOT_EXSIT(2200, "页面不存在");

    private Integer code;
    private String message;

    private BizEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(Integer code) {
        for (BizEnum c : BizEnum.values()) {
            if (c.getCode() == code) {
                return c.message;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
