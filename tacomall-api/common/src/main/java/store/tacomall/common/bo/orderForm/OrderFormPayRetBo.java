/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:27:22
 * @LastEditTime: 2021-11-04 17:16:46
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/orderForm/OrderFormPayRetBo.java
 */
package store.tacomall.common.bo.orderForm;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.order.OrderForm;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormPayRetBo {

    private OrderForm orderForm;

    private Integer activityId;

    private BigDecimal activityAmount;

    private Integer couponId;

    private BigDecimal couponAmount;

    private BigDecimal amountPay;

    private OrderFormPayRetPayWayBo payWay;
}