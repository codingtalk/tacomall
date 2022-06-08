/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:50:39
 * @LastEditTime: 2021-11-05 15:58:41
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/impl/SaleFormServiceImpl.java
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionDefinition;

import store.tacomall.apima.service.SaleFormService;
import store.tacomall.apima.vo.sale.SaleFormPayVo;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.entity.member.MemberCart;
import store.tacomall.common.entity.sale.SaleForm;
import store.tacomall.common.entity.sale.SaleFormGoodsItems;
import store.tacomall.common.entity.shop.ShopStock;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.logic.sale.SaleFormPayLogic;
import store.tacomall.common.mapper.goods.GoodsMapper;
import store.tacomall.common.mapper.member.MemberCartMapper;
import store.tacomall.common.mapper.sale.SaleFormMapper;
import store.tacomall.common.mapper.shop.ShopStockMapper;
import store.tacomall.common.bo.goods.GoodsItemsQuantityBo;
import store.tacomall.common.bo.pay.PayReqBo;
import store.tacomall.common.bo.pay.PayResBo;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.RequestUtil;
import store.tacomall.common.util.SnUtil;
import store.tacomall.common.util.StringUtil;
import store.tacomall.common.vo.base.PageVo;

@Service
public class SaleFormServiceImpl extends ServiceImpl<SaleFormMapper, SaleForm> implements SaleFormService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  GoodsMapper goodsMapper;

  @Autowired
  MemberCartMapper memberCartMapper;

  @Autowired
  ShopStockMapper shopStockMapper;

  @Autowired
  SaleFormGoodsItemsServiceImpl saleFormGoodsItemsServiceImpl;

  @Autowired
  SaleFormPayLogic saleFormPayLogic;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<SaleForm> add(String type, JSONObject json) {
    ResponseJson<SaleForm> responseJson = new ResponseJson<>();
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
      SaleForm saleForm = SaleForm.builder().sn(SnUtil.gen("ORDER_FORM")).shopId(1)
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
      baseMapper.insert(saleForm);
      saleFormGoodsItemsServiceImpl.saveBatch(goodsItemsQuantityBoList
          .stream().map(x -> SaleFormGoodsItems.builder().formId(saleForm.getId())
              .goodsItemsId(x.getGoodsItemsId()).quantity(x.getQuantity()).build())
          .collect(Collectors.toList()));
      dataSourceTransactionManager.commit(transactionStatus);
      responseJson.setData(saleForm);
    } catch (Exception e) {
      logger.error(e.toString());
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwSqlException(e.toString());
    }
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<PageVo>> responsePageVo = new ResponsePageJson<>();
    Page<PageVo> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<SaleForm> qw = new QueryWrapper<SaleForm>();
    qw.eq("of.member_id", RequestUtil.getLoginUser().getInteger("id"));
    qw.eq("of.is_delete", 0);
    qw.orderByDesc("of.create_time");
    IPage<PageVo> result = baseMapper.queryPage(page, qw);
    responsePageVo.setData(result.getRecords());
    responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageVo.ok();
    return responsePageVo;
  }

  @Override
  public ResponseJson<SaleForm> info(Integer id) {
    ResponseJson<SaleForm> responseJson = new ResponseJson<>();
    responseJson
        .setData(baseMapper.queryInfo(new QueryWrapper<SaleForm>().lambda().eq(SaleForm::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<SaleFormPayVo> pay(Integer id, Integer isPreview, PayReqBo payReqBo) {
    ResponseJson<SaleFormPayVo> responseJson = new ResponseJson<>();
    PayResBo payResBo = null;
    try {
      payResBo = saleFormPayLogic.pay(id, isPreview, payReqBo);
      baseMapper.update(null, new UpdateWrapper<SaleForm>().lambda()
          .set(SaleForm::getAmountPay, payResBo.getAmountPayed())
          .set(SaleForm::getActivityId, payResBo.getActivityId())
          .set(SaleForm::getCouponId, payResBo.getCouponId())
          .set(SaleForm::getCouponAmount, payResBo.getCouponAmount()));
    } catch (Exception e) {
      ExceptionUtil.throwBizException("结算失败");
    }
    responseJson
        .setData(SaleFormPayVo.builder().saleFormId(id).payResBo(payResBo).build());
    responseJson.ok();
    return responseJson;
  }

}