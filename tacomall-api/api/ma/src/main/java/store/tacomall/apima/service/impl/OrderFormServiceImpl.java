/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:50:39
 * @LastEditTime: 2021-11-02 17:25:29
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/impl/OrderFormServiceImpl.java
 */
package store.tacomall.apima.service.impl;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;

import store.tacomall.apima.service.OrderFormService;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.entity.member.MemberCart;
import store.tacomall.common.entity.order.OrderForm;
import store.tacomall.common.entity.order.OrderFormGoodsItems;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsMapper;
import store.tacomall.common.mapper.member.MemberCartMapper;
import store.tacomall.common.mapper.order.OrderFormMapper;
import store.tacomall.common.bo.goods.GoodsItemsQuantityBo;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.RequestUtil;
import store.tacomall.common.util.SnUtil;
import store.tacomall.common.util.StringUtil;

@Service
public class OrderFormServiceImpl extends ServiceImpl<OrderFormMapper, OrderForm> implements OrderFormService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    MemberCartMapper memberCartMapper;

    @Autowired
    OrderFormGoodsItemsServiceImpl orderFormGoodsItemsServiceImpl;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public ResponseJson<OrderForm> add(String type, JSONObject json) {
        ResponseJson<OrderForm> responseJson = new ResponseJson<>();
        List<GoodsItemsQuantityBo> goodsItemsQuantityBoList = new ArrayList<GoodsItemsQuantityBo>();
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            switch (type) {
            case "cart":
                String cartIds = json.getString("cartIds");
                if (StringUtil.isBlank(cartIds)) {
                    ExceptionUtil.throwClientException("Req body cartIds is required");
                }
                List<MemberCart> memberCartList = memberCartMapper.selectList(new QueryWrapper<MemberCart>().lambda()
                        .in(MemberCart::getId, Arrays.asList(cartIds.split(","))));
                memberCartList.stream().forEach((MemberCart memberCart) -> {
                    goodsItemsQuantityBoList.add(GoodsItemsQuantityBo.builder()
                            .goodsItemsId(memberCart.getGoodsItemsId()).quantity(memberCart.getQuantity()).build());
                });
                memberCartMapper.update(null, new UpdateWrapper<MemberCart>().lambda().set(MemberCart::getStatus, 2)
                        .in(MemberCart::getId, Arrays.asList(cartIds.split(","))));
                break;
            case "goodItems":
                // @TODO
                break;
            default:
                ExceptionUtil.throwClientException("Params type is prohibited");
            }
            if (goodsItemsQuantityBoList.size() == 0) {
                ExceptionUtil.throwClientException("GoodsItems data is required");
            }
            OrderForm orderForm = OrderForm.builder().sn(SnUtil.gen("ORDER_FORM")).shopId(1)
                    .memberId(RequestUtil.getLoginUser().getInteger("id"))
                    .amountTotal(goodsItemsQuantityBoList.stream().map(x -> {
                        return Optional
                                .ofNullable(goodsMapper.selectOne(new QueryWrapper<Goods>().lambda()
                                        .apply(String.format("id in (select goods_id from goods_items where id = %s)",
                                                x.getGoodsItemsId()))))
                                .flatMap(g -> Optional.ofNullable(g.getAmount()))
                                .orElseThrow(() -> new RuntimeException("商品规格不存在~"))
                                .multiply(new BigDecimal(x.getQuantity()));
                    }).reduce(BigDecimal.ZERO, BigDecimal::add)).status(1).build();
            baseMapper.insert(orderForm);
            orderFormGoodsItemsServiceImpl.saveBatch(goodsItemsQuantityBoList
                    .stream().map(x -> OrderFormGoodsItems.builder().formId(orderForm.getId())
                            .goodsItemsId(x.getGoodsItemsId()).quantity(x.getQuantity()).build())
                    .collect(Collectors.toList()));
            dataSourceTransactionManager.commit(transactionStatus);
            responseJson.setData(orderForm);
        } catch (Exception e) {
            logger.error(e.toString());
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwSqlException(e.toString());
        }
        responseJson.ok();
        return responseJson;
    }

}