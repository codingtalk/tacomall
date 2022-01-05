/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:35:32
 * @LastEditTime: 2021-11-04 17:43:37
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/logic/impl/SaleFormPayLogicImpl.java
 */
package store.tacomall.common.logic.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;
import store.tacomall.common.bo.sale.SaleFormPayReqBo;
import store.tacomall.common.bo.sale.SaleFormPayRetBo;
import store.tacomall.common.entity.sale.SaleForm;
import store.tacomall.common.logic.SaleFormPayLogic;
import store.tacomall.common.mapper.sale.SaleFormGoodsItemsMapper;
import store.tacomall.common.mapper.sale.SaleFormMapper;

@Service
public class SaleFormPayLogicImpl implements SaleFormPayLogic {

  @Autowired
  SaleFormMapper saleFormMapper;

  @Autowired
  SaleFormGoodsItemsMapper saleFormGoodsItemMapper;

  private SaleFormPayRetBo _calculate(Integer saleFormId, SaleFormPayReqBo saleFormPayReqBo) throws Exception {
    SaleForm saleForm = Optional
        .ofNullable(saleFormMapper.selectOne(
            new QueryWrapper<SaleForm>().lambda().in(SaleForm::getStatus, Arrays.asList(1, 2))))
        .orElseThrow(() -> new RuntimeException("订单不存在~"));
    BigDecimal activityAmount = BigDecimal.ZERO;
    BigDecimal couponAmount = BigDecimal.ZERO;
    if (ObjectUtil.isNotNull(saleFormPayReqBo.getActivityId())) {

    }
    if (ObjectUtil.isNotNull(saleFormPayReqBo.getCouponId())) {

    }
    return SaleFormPayRetBo.builder().saleForm(saleForm)
        .amountPay(saleForm.getAmountTotal().subtract(activityAmount).subtract(couponAmount))
        .activityAmount(activityAmount).couponAmount(couponAmount).build();
  };

  @Override
  public SaleFormPayRetBo calculate(Integer saleFormId, SaleFormPayReqBo saleFormPayReqBo) throws Exception {
    return _calculate(saleFormId, saleFormPayReqBo);

  };

  @Override
  public SaleFormPayRetBo settle(Integer saleFormId, SaleFormPayReqBo saleFormPayReqBo) throws Exception {
    SaleFormPayRetBo saleFormPayRetBo = _calculate(saleFormId, saleFormPayReqBo);
    SaleForm saleForm = saleFormPayRetBo.getSaleForm();
    LambdaUpdateWrapper<SaleForm> luw = new UpdateWrapper<SaleForm>().lambda();
    if (saleFormPayRetBo.getAmountPay().compareTo(BigDecimal.ZERO) == 1) {
      if (ObjectUtil.isNull(saleFormPayReqBo.getPayWay())) {
        throw new Exception("支付方式未选择~");
      }
      // @TODO
    }
    saleForm.setAmountPay(saleFormPayRetBo.getAmountPay());
    saleForm.setActivityId(saleFormPayRetBo.getActivityId());
    saleForm.setCouponId(saleFormPayRetBo.getCouponId());
    saleForm.setCouponAmount(saleFormPayRetBo.getCouponAmount());
    luw.set(SaleForm::getAmountPay, saleForm.getAmountPay());
    luw.set(SaleForm::getActivityId, saleForm.getActivityId());
    luw.set(SaleForm::getCouponId, saleForm.getCouponId());
    luw.set(SaleForm::getCouponAmount, saleForm.getCouponAmount());
    saleFormMapper.update(null, luw);
    return saleFormPayRetBo;
  };
}
