/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:22:39
 * @LastEditTime: 2021-11-04 17:20:15
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/orderForm/OrderFormPayReqBo.java
 */
package store.tacomall.common.bo.orderForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormPayReqBo {

    private Boolean isUseShopBalance;

    private Integer activityId;

    private Integer couponId;

    private Integer payWay;
}
