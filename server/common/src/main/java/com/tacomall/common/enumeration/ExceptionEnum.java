
package com.tacomall.common.enumeration;

public enum ExceptionEnum {

    SERVER_ERROR(4000, "服务器发送未知错误"), SQL_ERROR(4100, "SQL错误"), UNAUTHORIZED_ERROR(4200, "未授权");

    private Integer code;
    private String message;

    private ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(Integer code) {
        for (ExceptionEnum c : ExceptionEnum.values()) {
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