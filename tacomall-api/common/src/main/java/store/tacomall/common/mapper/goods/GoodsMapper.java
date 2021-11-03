/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 16:47:30
 * @LastEditTime: 2021-10-22 13:34:00
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/goods/GoodsMapper.java
 */
package store.tacomall.common.mapper.goods;

import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.vo.base.PageVo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-10-21
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {

    Goods queryInfo(@Param(Constants.WRAPPER) Wrapper<Goods> wrapper);

    IPage<PageVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<Goods> wrapper);
}
