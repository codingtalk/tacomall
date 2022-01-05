/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:47:38
 * @LastEditTime: 2021-11-05 14:41:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/order/SaleFormMapper.java
 */
package store.tacomall.common.mapper.sale;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import store.tacomall.common.entity.sale.SaleForm;
import store.tacomall.common.vo.base.PageVo;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-11-01
 */
@Repository
public interface SaleFormMapper extends BaseMapper<SaleForm> {

  SaleForm queryInfo(@Param(Constants.WRAPPER) Wrapper<SaleForm> wrapper);

  IPage<PageVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<SaleForm> wrapper);

}
