/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 15:03:54
 * @LastEditTime: 2021-10-23 10:07:43
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/GoodsServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import store.tacomall.apiadmin.service.GoodsService;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.entity.goods.GoodsItems;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.goods.GoodsItemsMapper;
import store.tacomall.common.mapper.goods.GoodsMapper;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.vo.base.PageVo;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

  @Autowired
  GoodsItemsMapper goodsItemsMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  private void updateStatus(Integer id, String status) {
    baseMapper.update(null,
        new UpdateWrapper<Goods>().lambda().eq(Goods::getId, id).set(Goods::getStatus, status));
  }

  @Override
  public ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<PageVo>> responsePageVo = new ResponsePageJson<>();
    Page<PageVo> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<Goods> qw = new QueryWrapper<Goods>();
    qw.eq("g.is_delete", 0);
    qw.orderByDesc("g.create_time");
    IPage<PageVo> result = baseMapper.queryPage(page, qw);
    responsePageVo.setData(result.getRecords());
    responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageVo.ok();
    return responsePageVo;
  }

  @Override
  public ResponseJson<Goods> info(Integer id) {
    ResponseJson<Goods> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper.queryInfo(new QueryWrapper<Goods>().lambda().eq(Goods::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<Goods> add(JSONObject json) {
    ResponseJson<Goods> responseJson = new ResponseJson<>();
    Goods goods = JSON.toJavaObject(json, Goods.class);
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      baseMapper.insert(goods);
      goods.getGoodsItemsList().stream().forEach((GoodsItems goodsItems) -> {
        goodsItems.setGoodsId(goods.getId());
        goodsItemsMapper.insert(goodsItems);
      });
      dataSourceTransactionManager.commit(transactionStatus);
      responseJson.ok();
      responseJson.setData(goods);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwSqlException(e.toString());
    }
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    Goods goods = JSON.toJavaObject(json, Goods.class);
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      List<GoodsItems> goodsItemsList = goodsItemsMapper
          .selectList(new QueryWrapper<GoodsItems>().lambda().eq(GoodsItems::getGoodsId, goods.getId()));
      List<GoodsItems> k = new ArrayList<>();
      baseMapper.updateById(goods);
      goods.getGoodsItemsList().stream().forEach((GoodsItems goodsItems) -> {
        if (ObjectUtil.equal(goodsItems.getId(), 0)) {
          goodsItems.setGoodsId(goods.getId());
          goodsItemsMapper.insert(goodsItems);
        } else {
          goodsItemsMapper.update(null, new UpdateWrapper<GoodsItems>().lambda()
              .eq(GoodsItems::getId, goodsItems.getId()).set(GoodsItems::getSpecJson, goodsItems.getSpecJson()));
          k.add(goodsItems);
        }
      });
      goodsItemsList.stream().forEach((GoodsItems goodsItems) -> {
        if (!k.stream().map(GoodsItems::getId).collect(Collectors.toList()).contains(goodsItems.getId())) {
          goodsItemsMapper.deleteById(goodsItems.getId());
        }
      });
      dataSourceTransactionManager.commit(transactionStatus);
      responseJson.setData("更新成功");
      responseJson.ok();
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwSqlException(e.toString());
    }
    return responseJson;
  }

  @Override
  public ResponseJson<String> updateField(Integer id, Integer type, JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    String value = "";
    if (ObjectUtil.isNotEmpty(json) && ObjectUtil.isNotEmpty(json.get("value"))) {
      value = json.getString("value");
    }
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      switch (type) {
      case 1:
        updateStatus(Integer.valueOf(id), value);
      default:
        ExceptionUtil.throwClientException("非法参数：type");
      }
      dataSourceTransactionManager.commit(transactionStatus);
      responseJson.ok();
      responseJson.setData("更新成功");
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwSqlException(e.toString());
    }
    return responseJson;
  }

}
