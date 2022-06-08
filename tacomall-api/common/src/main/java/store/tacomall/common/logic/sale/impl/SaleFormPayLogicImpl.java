/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:35:32
 * @LastEditTime: 2021-11-04 17:43:37
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/logic/impl/SaleFormPayLogicImpl.java
 */
package store.tacomall.common.logic.sale.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;
import store.tacomall.common.bo.pay.PayReqBo;
import store.tacomall.common.bo.pay.PayResBo;
import store.tacomall.common.entity.sale.SaleForm;
import store.tacomall.common.logic.sale.SaleFormPayLogic;
import store.tacomall.common.mapper.sale.SaleFormGoodsItemsMapper;
import store.tacomall.common.mapper.sale.SaleFormMapper;

@Service
public class SaleFormPayLogicImpl implements SaleFormPayLogic {

  @Autowired
  SaleFormMapper saleFormMapper;

  @Autowired
  SaleFormGoodsItemsMapper saleFormGoodsItemMapper;

  @Override
  public PayResBo pay(Integer saleFormId, Integer isPreview, PayReqBo payReqBo)
      throws Exception {
    PayResBo payResBo = new PayResBo();
    BigDecimal activityAmount = BigDecimal.ZERO;
    BigDecimal couponAmount = BigDecimal.ZERO;
    SaleForm saleForm = Optional
        .ofNullable(saleFormMapper.selectOne(
            new QueryWrapper<SaleForm>().lambda().in(SaleForm::getStatus, Arrays.asList(1, 2))))
        .orElseThrow(() -> new RuntimeException("订单不存在~"));
    if (ObjectUtil.isNotNull(payReqBo.getActivityId())) {

    }
    if (ObjectUtil.isNotNull(payReqBo.getCouponId())) {

    }
    payResBo.toBuilder()
        .amountNeed(saleForm.getAmountTotal())
        .amountPayed(saleForm.getAmountTotal().subtract(activityAmount).subtract(couponAmount))
        .activityAmount(activityAmount).couponAmount(couponAmount).build();
    return payResBo;
  };
}
