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
public class ProductSKUServiceImpl extends ServiceImpl<ProductSKUMapper, ProductSKU> implements ProductSKUService {

}
