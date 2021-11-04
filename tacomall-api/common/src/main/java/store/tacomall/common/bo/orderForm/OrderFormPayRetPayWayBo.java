/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:29:34
 * @LastEditTime: 2021-11-04 16:33:33
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/orderForm/OrderFormPayRetPayWayBo.java
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
public class OrderFormPayRetPayWayBo {

    private Integer type;

    private Integer amount;

    private Integer trackSn;
}
