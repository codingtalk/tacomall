/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 16:32:58
 * @LastEditTime: 2021-10-06 14:59:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/enumeration/BizEnum.java
 * @Just do what I think it is right
 */
package store.tacomall.common.enumeration;

public enum BizEnum {
    OK(2000, "正确响应"), ERROR(2001, "服务异常"), REQUEST_QUERY_REQUIRE(2002, "json query 不能为空"),
    REQUEST_QUERY_UNMATCH(2002, "json query 参数错误"), REQUEST_TOO_BUSY(2003, "系统繁忙，稍后重试~"), USER_NOT_EXIST(2100, "员工不存在"),
    USER_NOT_LOGGED_IN(2101, "员工未登陆"), STAFF_SHOP_STATUS_FREEZE(2102, "店铺已被冻结"), STAFF_STATUS_FREEZE(2103, "员工已被冻结"),
    VERIFY_CODE_ERROR(2104, "验证码错误"), USER_BIZ_ERROR(2102, "用户业务信息错误"), PAGE_NOT_EXSIT(2200, "页面不存在"),
    ORDER_FROM_TYPE_NOT_EXSIT(2300, "订单来源方式不存在"), GOODS_ITEM_STOCK_NONE(2400, "商品库存不足"),
    PURCHANSE_NOT_EXSIT(2500, "订单类型不存在"), APPROVAL_NOT_ALLOW(2600, "申请受限"), ILLEGAL_OPERATION(2700, "非法操作"),
    NULL_OPERATION(2701, "查询结果为空"), PAY_REPEAT_ERROR(2702, "订单已支付完成"), PAY_AMOUNT_ERROR(2703, "支付金额不对"),
    MEMBER_TRANSFER_ERROR(2704, "会员类型转换受限");

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
