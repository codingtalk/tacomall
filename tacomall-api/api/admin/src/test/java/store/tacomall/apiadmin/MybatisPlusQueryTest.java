/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-19 22:03:22
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/test/java/store/tacomall/apiadmin/MybatisPlusQueryTest.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin;

import java.util.List;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import store.tacomall.common.entity.tm.TmStaff;
import store.tacomall.common.mapper.tm.TmStaffMapper;

public class MybatisPlusQueryTest {
    @Resource
    private TmStaffMapper TmStaffMapper;

    @Test
    public void test1() {
        LambdaQueryWrapper<TmStaff> lqw = new QueryWrapper<TmStaff>().lambda();
        lqw.eq(TmStaff::getIsDelete, 0);
        List<TmStaff> TmStaffList = TmStaffMapper.selectList(lqw);
        Assertions.assertEquals(10, TmStaffList.size());
    }
}
