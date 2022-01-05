/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-02 15:54:27
 * @LastEditTime: 2021-11-02 15:55:36
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/impl/SaleFormGoodsItemsServiceImpl.java
 */
package store.tacomall.apima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import store.tacomall.apima.service.SaleFormGoodsItemsService;
import store.tacomall.common.entity.sale.SaleFormGoodsItems;
import store.tacomall.common.mapper.sale.SaleFormGoodsItemsMapper;

@Service
public class SaleFormGoodsItemsServiceImpl extends ServiceImpl<SaleFormGoodsItemsMapper, SaleFormGoodsItems>
    implements SaleFormGoodsItemsService {

}