/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-08 14:29:52
 * @LastEditTime: 2021-11-08 15:06:36
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/impl/PageCenterStrategyImpl.java
 */
package store.tacomall.apima.strategy.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apima.strategy.PageStrategy;
import store.tacomall.apima.vo.page.PageCenterVo;
import store.tacomall.common.constant.DataResultConstant;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.entity.sale.SaleForm;
import store.tacomall.common.mapper.sale.SaleFormMapper;
import store.tacomall.common.util.RequestUtil;

@Component("center")
public class PageCenterStrategyImpl implements PageStrategy {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  SaleFormMapper saleFormMapper;

  @SuppressWarnings("unchecked")
  @Override
  public ResponseJson<Object> loadPageInfo(JSONObject json) {
    ResponseJson<Object> responseJson = new ResponseJson<>();
    List<SaleForm> saleFormList = Optional.ofNullable(RequestUtil.getLoginUser()).map(j -> {
      return saleFormMapper
          .selectList(new QueryWrapper<SaleForm>().lambda().eq(SaleForm::getMemberId, j.getInteger("id")));
    }).orElse(DataResultConstant.EMPTY_LIST);
    responseJson.setData(
        PageCenterVo.builder().SumOrderUnPay((int) saleFormList.stream().filter(x -> x.getStatus().equals(2)).count())
            .SumOrderPayed((int) saleFormList.stream().filter(x -> x.getStatus().equals(3)).count())
            .SumOrderFinished((int) saleFormList.stream().filter(x -> x.getStatus().equals(4)).count())
            .SumOrderReturn((int) saleFormList.stream().filter(x -> x.getStatus().equals(5)).count()).build());
    responseJson.ok();
    return responseJson;
  }
}
