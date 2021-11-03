/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-12 17:37:54
 * @LastEditTime: 2021-10-22 17:22:58
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/tm/TmStaffMapper.java
 * @Just do what I think it is right
 */
package store.tacomall.common.mapper.tm;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import store.tacomall.common.entity.tm.TmStaff;
import store.tacomall.common.vo.base.PageVo;

@Repository
public interface TmStaffMapper extends BaseMapper<TmStaff> {

    TmStaff queryInfo(@Param(Constants.WRAPPER) Wrapper<TmStaff> wrapper);

    IPage<PageVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<TmStaff> wrapper);
}
