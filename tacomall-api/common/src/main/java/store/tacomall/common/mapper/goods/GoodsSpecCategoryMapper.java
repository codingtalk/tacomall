/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 16:46:29
 * @LastEditTime: 2021-10-22 13:46:58
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/goods/GoodsSpecCategoryMapper.java
 */
package store.tacomall.common.mapper.goods;

import store.tacomall.common.entity.goods.GoodsSpecCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-10-21
 */
@Repository
public interface GoodsSpecCategoryMapper extends BaseMapper<GoodsSpecCategory> {

    GoodsSpecCategory queryInfo(@Param(Constants.WRAPPER) Wrapper<GoodsSpecCategory> wrapper);

}
