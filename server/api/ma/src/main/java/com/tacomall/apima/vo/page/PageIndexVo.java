package com.tacomall.apima.vo.page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.entity.logistic.LogisticType;
import com.tacomall.common.entity.ma.MaCarousel;
import com.tacomall.common.entity.product.Product;
import com.tacomall.common.entity.product.ProductClassification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageIndexVo {

  private List<MaCarousel> carousalList;

  private List<ProductClassification> productClassificationForNavList;

  private List<ProductClassification> productClassificationForGridList;

  private List<Product> productForSecKill;

}
