/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:22:39
 * @LastEditTime: 2022-01-05 14:32:59
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/sale/SaleFormPayReqBo.java
 */
package store.tacomall.common.bo.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleFormPayReqBo {

  private Boolean isUseShopBalance;

  private Integer activityId;

  private Integer couponId;

  private Integer payWay;
}
