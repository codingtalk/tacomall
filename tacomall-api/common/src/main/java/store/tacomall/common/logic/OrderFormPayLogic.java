/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:34:56
 * @LastEditTime: 2021-11-04 17:43:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/logic/OrderFormPayLogic.java
 */
package store.tacomall.common.logic;

import store.tacomall.common.bo.orderForm.OrderFormPayReqBo;
import store.tacomall.common.bo.orderForm.OrderFormPayRetBo;

public interface OrderFormPayLogic {

    OrderFormPayRetBo calculate(Integer orderFormId, OrderFormPayReqBo orderFormPayReqBo) throws Exception;

    OrderFormPayRetBo settle(Integer orderFormId, OrderFormPayReqBo orderFormPayReqBo) throws Exception;
}
