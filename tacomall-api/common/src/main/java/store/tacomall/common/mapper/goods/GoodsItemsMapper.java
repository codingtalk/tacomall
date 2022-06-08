/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:48:55
 * @LastEditTime: 2022-01-17 17:07:03
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/goods/GoodsItemsMapper.java
 */
package store.tacomall.common.mapper.goods;

import store.tacomall.common.entity.goods.GoodsItems;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

    GoodsItems queryInfoByMap(Map<String, Object> map);

    List<GoodsItems> queryListByMap(Map<String, Object> map);

}
