/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:48:55
 * @LastEditTime: 2021-10-26 15:24:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/member/MemberMapper.java
 */
package store.tacomall.common.mapper.member;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import store.tacomall.common.entity.member.Member;
import store.tacomall.common.vo.base.PageVo;

@Repository
public interface MemberMapper extends BaseMapper<Member> {
    Member queryInfo(@Param(Constants.WRAPPER) Wrapper<Member> wrapper);

    IPage<PageVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<Member> wrapper);
}