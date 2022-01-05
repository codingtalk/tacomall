/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:34:56
 * @LastEditTime: 2022-01-05 14:32:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/logic/SaleFormPayLogic.java
 */
package store.tacomall.common.logic;

import store.tacomall.common.bo.sale.SaleFormPayReqBo;
import store.tacomall.common.bo.sale.SaleFormPayRetBo;

public interface SaleFormPayLogic {

  SaleFormPayRetBo calculate(Integer saleFormId, SaleFormPayReqBo saleFormPayReqBo) throws Exception;

  SaleFormPayRetBo settle(Integer saleFormId, SaleFormPayReqBo saleFormPayReqBo) throws Exception;
}
