/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:29:34
 * @LastEditTime: 2021-11-04 16:33:33
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/saleForm/SaleFormPayRetPayWayBo.java
 */
package store.tacomall.common.bo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PayResPayWayBo {

  private Integer type;

  private Integer amount;

  private Integer trackSn;
}
