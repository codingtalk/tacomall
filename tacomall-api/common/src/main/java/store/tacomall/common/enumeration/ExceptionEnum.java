/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 16:33:07
 * @LastEditTime: 2022-01-05 14:00:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/enumeration/ExceptionEnum.java
 * @Just do what I think it is right
 */
package store.tacomall.common.enumeration;

public enum ExceptionEnum {

    SERVER_ERROR(4000, "服务器发送未知错误"), SQL_ERROR(4100, "SQL错误"), AUTHORIZED_ERROR(4200, "用户鉴权失败~"),
    PAGE_NOT_EXSIT(4300, "页面不存在");

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