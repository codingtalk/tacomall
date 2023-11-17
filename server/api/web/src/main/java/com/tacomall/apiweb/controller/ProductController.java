package com.tacomall.apiweb.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.tacomall.apiweb.dto.ProductUpdateDto;
import com.tacomall.apiweb.service.ProductService;
import com.tacomall.common.entity.product.Product;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

@RestController
@RequestMapping(value = "/product/")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping("page")
  public ResponsePageJson<List<PaginatorVo>> page(
      @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
    return productService.page(pageIndex, pageSize, json);
  }

  @PostMapping("info")
  public ResponseJson<Product> info(@RequestParam(value = "id") Integer id) {
    return productService.info(id);
  }

  @PostMapping("add")
  public ResponseJson<Product> update(
      @RequestBody JSONObject json) {
    return productService.add(json);
  }

  @PostMapping("update")
  public ResponseJson<String> update(@RequestParam(value = "id") Integer id,
      @RequestBody ProductUpdateDto productUpdateDto) {
    return productService.update(id, productUpdateDto);
  }

}