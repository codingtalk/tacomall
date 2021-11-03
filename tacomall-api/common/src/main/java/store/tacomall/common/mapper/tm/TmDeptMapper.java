/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-12 17:37:54
 * @LastEditTime: 2021-10-22 13:34:39
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/tm/TmDeptMapper.java
 * @Just do what I think it is right
 */
package store.tacomall.common.mapper.tm;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import store.tacomall.common.entity.tm.TmDept;

@Repository
public interface TmDeptMapper extends BaseMapper<TmDept> {

}
