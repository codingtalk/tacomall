/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-04 13:50:39
 * @LastEditTime: 2021-11-04 13:59:31
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/shop/ShopStockMapper.java
 */
package store.tacomall.common.mapper.shop;

import store.tacomall.common.entity.shop.ShopStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-11-04
 */
@Repository
public interface ShopStockMapper extends BaseMapper<ShopStock> {

    @Update("UPDATE shop_stock SET stock = stock - ${quantity}, stock_freeze =  stock_freeze + ${quantity} WHERE shop_id = ${shopId} and goods_items_id = ${goodsItemsId} AND stock >= ${quantity} AND is_delete = 0")
    Integer freezeStock(@Param("shopId") Integer shopId, @Param("goodsItemsId") Integer goodsItemsId,
            @Param("quantity") Integer quantity);

    @Update("UPDATE shop_stock SET stock_freeze =  stock_freeze = ${quantity} WHERE shop_id = ${shopId} and goods_items_id = ${goodsItemsId} AND stock_freeze >= ${quantity} AND is_delete = 0")
    Integer confirmFreeze(@Param("shopId") Integer shopId, @Param("goodsItemsId") Integer goodsItemsId,
            @Param("quantity") Integer quantity);

    @Update("UPDATE shop_stock SET stock = stock + ${quantity}, stock_freeze =  stock_freeze = ${quantity} WHERE shop_id = ${shopId} and goods_items_id = ${goodsItemsId} AND stock_freeze >= ${quantity} AND is_delete = 0")
    Integer unFreeze(@Param("shopId") Integer shopId, @Param("goodsItemsId") Integer goodsItemsId,
            @Param("quantity") Integer quantity);
}
