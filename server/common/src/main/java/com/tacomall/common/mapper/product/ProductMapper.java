package com.tacomall.common.mapper.product;

import com.tacomall.common.entity.product.Product;
import com.tacomall.common.vo.base.PaginatorVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2023-11-14
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
  
    Product queryInfo(@Param(Constants.WRAPPER) Wrapper<Product> wrapper);

    IPage<PaginatorVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<Product> wrapper);
}
