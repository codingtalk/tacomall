/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:34:56
 * @LastEditTime: 2022-01-05 14:32:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/logic/SaleFormPayLogic.java
 */
package store.tacomall.common.logic.sale;

import store.tacomall.common.bo.pay.PayReqBo;
import store.tacomall.common.bo.pay.PayResBo;

public interface SaleFormPayLogic {

  PayResBo pay(Integer saleFormId, Integer isPreview, PayReqBo payReqBo) throws Exception;
}
