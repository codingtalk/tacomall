/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:27:22
 * @LastEditTime: 2021-11-04 17:16:46
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/saleForm/SaleFormPayRetBo.java
 */
package store.tacomall.common.bo.pay;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.sale.SaleForm;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PayResBo {

  private SaleForm saleForm;

  private BigDecimal amountNeed;

  private BigDecimal amountPayed;

  private Integer activityId;

  private BigDecimal activityAmount;

  private Integer couponId;

  private BigDecimal couponAmount;

  private PayResPayWayBo payWay;
}