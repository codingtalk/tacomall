/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:48:55
 * @LastEditTime: 2021-10-29 14:19:00
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/goods/GoodsItemsMapper.java
 */
package store.tacomall.common.mapper.goods;

import store.tacomall.common.entity.goods.GoodsItems;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-10-12
 */
@Repository
public interface GoodsItemsMapper extends BaseMapper<GoodsItems> {

    GoodsItems queryInfoById(@Param("id") int id);

    List<GoodsItems> queryGoodsItemsListByGoodsId(@Param("goodsId") int goodsId);

}
