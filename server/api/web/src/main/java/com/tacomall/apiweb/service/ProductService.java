package com.tacomall.apiweb.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.apiweb.dto.ProductUpdateDto;
import com.tacomall.apiweb.dto.ProductUpdateDto;
import com.tacomall.common.entity.product.Product;
import com.tacomall.common.entity.product.Product;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface ProductService extends IService<Product> {

  ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<Product> info(Integer id);

  ResponseJson<Product> add(JSONObject json);

  ResponseJson<String> update(Integer id, ProductUpdateDto productUpdateDto);
}
