package com.tacomall.apiweb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.tacomall.apiweb.dto.ProductUpdateDto;
import com.tacomall.apiweb.service.ProductSKUService;
import com.tacomall.apiweb.service.ProductService;
import com.tacomall.common.entity.product.Product;
import com.tacomall.common.entity.product.ProductSKU;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.product.ProductMapper;
import com.tacomall.common.mapper.product.ProductSKUMapper;
import com.tacomall.common.mapper.logistic.LogisticMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.vo.base.PaginatorVo;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

  @Autowired
  LogisticMapper logisticMapper;

  @Autowired
  ProductSKUMapper productSKUMapper;

  @Autowired
  ProductSKUService productSKUService;

  @Autowired
  Environment environment;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<Product> info(Integer id) {
    ResponseJson<Product> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper
        .selectOne(
            new QueryWrapper<Product>().lambda().eq(Product::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<PaginatorVo>> responsePaginatorVo = new ResponsePageJson<>();
    Page<Product> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<Product> qw = new QueryWrapper<Product>();

    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query)) {
      if (ObjectUtil.isNotEmpty(query.get("keyword"))) {
        qw.like("p.sn", query.get("keyword"));
      }
      if (ObjectUtil.isNotEmpty(query.getString("status"))) {
        qw.in("p.status", Arrays.asList(query.getString("status").split(",")));
      }
    }
    qw.eq("p.is_delete", 0);
    IPage<PaginatorVo> result = baseMapper.queryPage(page, qw);
    responsePaginatorVo.setData(result.getRecords());
    responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePaginatorVo.ok();
    return responsePaginatorVo;
  }

  @Override
  public ResponseJson<Product> add(JSONObject json) {
    ResponseJson<Product> responseJson = new ResponseJson<>();
    responseJson.setStatus(false);
    Product product = JSON.toJavaObject(json, Product.class);
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      baseMapper.insert(product);
      productSKUService
          .saveBatch(product.getProductSKUList().stream().map(x -> x.toBuilder().productId(product.getId()).build())
              .collect(Collectors.toList()));
      responseJson.ok();
      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwServerException(e.toString());
    }
    responseJson.ok();
    responseJson.setData(product);
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(Integer id, ProductUpdateDto productUpdateDto) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    Product Product = JSON
        .toJavaObject(JSONObject.parseObject(JSON.toJSONString(productUpdateDto)), Product.class)
        .toBuilder()
        .id(id).build();
    Logistic logistic = logisticMapper.selectOne(new QueryWrapper<Logistic>()
        .inSql("id", String.format("select logistic_id from order_form where id = %s", id)));
    if (ObjectUtil.isNotNull(logistic)) {
      // @TODO Cancel the order from third-part
    }
    baseMapper.updateById(Product);
    responseJson.ok();
    responseJson.setData("更新成功");
    return responseJson;
  }

}
