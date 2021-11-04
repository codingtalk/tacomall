/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 16:35:32
 * @LastEditTime: 2021-11-04 17:43:37
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/logic/impl/OrderFormPayLogicImpl.java
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
import store.tacomall.common.bo.orderForm.OrderFormPayReqBo;
import store.tacomall.common.bo.orderForm.OrderFormPayRetBo;
import store.tacomall.common.entity.order.OrderForm;
import store.tacomall.common.logic.OrderFormPayLogic;
import store.tacomall.common.mapper.order.OrderFormGoodsItemsMapper;
import store.tacomall.common.mapper.order.OrderFormMapper;

@Service
public class OrderFormPayLogicImpl implements OrderFormPayLogic {

    @Autowired
    OrderFormMapper orderFormMapper;

    @Autowired
    OrderFormGoodsItemsMapper orderFormGoodsItemMapper;

    private OrderFormPayRetBo _calculate(Integer orderFormId, OrderFormPayReqBo orderFormPayReqBo) throws Exception {
        OrderForm orderForm = Optional
                .ofNullable(orderFormMapper.selectOne(
                        new QueryWrapper<OrderForm>().lambda().in(OrderForm::getStatus, Arrays.asList(1, 2))))
                .orElseThrow(() -> new RuntimeException("订单不存在~"));
        BigDecimal activityAmount = BigDecimal.ZERO;
        BigDecimal couponAmount = BigDecimal.ZERO;
        if (ObjectUtil.isNotNull(orderFormPayReqBo.getActivityId())) {

        }
        if (ObjectUtil.isNotNull(orderFormPayReqBo.getCouponId())) {

        }
        return OrderFormPayRetBo.builder().orderForm(orderForm)
                .amountPay(orderForm.getAmountTotal().subtract(activityAmount).subtract(couponAmount))
                .activityAmount(activityAmount).couponAmount(couponAmount).build();
    };

    @Override
    public OrderFormPayRetBo calculate(Integer orderFormId, OrderFormPayReqBo orderFormPayReqBo) throws Exception {
        return _calculate(orderFormId, orderFormPayReqBo);

    };

    @Override
    public OrderFormPayRetBo settle(Integer orderFormId, OrderFormPayReqBo orderFormPayReqBo) throws Exception {
        OrderFormPayRetBo orderFormPayRetBo = _calculate(orderFormId, orderFormPayReqBo);
        OrderForm orderForm = orderFormPayRetBo.getOrderForm();
        LambdaUpdateWrapper<OrderForm> luw = new UpdateWrapper<OrderForm>().lambda();
        if (orderFormPayRetBo.getAmountPay().compareTo(BigDecimal.ZERO) == 1) {
            if (ObjectUtil.isNull(orderFormPayReqBo.getPayWay())) {
                throw new Exception("支付方式未选择~");
            }
            // @TODO
        }
        orderForm.setAmountPay(orderFormPayRetBo.getAmountPay());
        orderForm.setActivityId(orderFormPayRetBo.getActivityId());
        orderForm.setCouponId(orderFormPayRetBo.getCouponId());
        orderForm.setCouponAmount(orderFormPayRetBo.getCouponAmount());
        luw.set(OrderForm::getAmountPay, orderForm.getAmountPay());
        luw.set(OrderForm::getActivityId, orderForm.getActivityId());
        luw.set(OrderForm::getCouponId, orderForm.getCouponId());
        luw.set(OrderForm::getCouponAmount, orderForm.getCouponAmount());
        orderFormMapper.update(null, luw);
        return orderFormPayRetBo;
    };
}
