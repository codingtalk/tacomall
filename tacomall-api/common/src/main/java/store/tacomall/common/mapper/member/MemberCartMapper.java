/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 17:20:08
 * @LastEditTime: 2021-10-28 17:51:42
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/member/MemberCartMapper.java
 */
package store.tacomall.common.mapper.member;

import store.tacomall.common.entity.member.MemberCart;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-10-26
 */
@Mapper
public interface MemberCartMapper extends BaseMapper<MemberCart> {

    List<MemberCart> queryList(@Param(Constants.WRAPPER) Wrapper<MemberCart> wrapper);

}
