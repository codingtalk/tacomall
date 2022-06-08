/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-20 15:01:44
 * @LastEditTime: 2021-10-26 15:24:42
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/MemberServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import store.tacomall.apiadmin.service.MemberService;
import store.tacomall.apiadmin.vo.member.MemberInfoVo;
import store.tacomall.common.entity.member.Member;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.member.MemberMapper;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.vo.base.PageVo;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

    private void updateStatus(int id, Integer value) {
        memberMapper.update(null,
                new UpdateWrapper<Member>().lambda().eq(Member::getId, id).set(Member::getStatus, value));
    }

    @Override
    public ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
        ResponsePageJson<List<PageVo>> responsePageVo = new ResponsePageJson<>();
        Page<Member> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<Member> lqw = new QueryWrapper<Member>();
        JSONObject query = json.getJSONObject("query");
        if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("type"))) {
            lqw.eq("m.type", query.get("type"));
        }
        if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("keyword"))) {
            lqw.and(k -> k.like("m.real_name", query.get("keyword")).or().eq("m.mobile", query.get("keyword")));
        }
        if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("status"))) {
            lqw.eq("m.status", query.getString("status"));
        }
        lqw.eq("m.is_delete", 0);
        IPage<PageVo> result = baseMapper.queryPage(page, lqw);
        responsePageVo.setData(result.getRecords());
        responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePageVo.ok();
        return responsePageVo;
    }

    @Override
    public ResponseJson<MemberInfoVo> info(Integer id) {
        ResponseJson<MemberInfoVo> responseJson = new ResponseJson<>();
        responseJson.setData(MemberInfoVo.builder()
                .member(baseMapper.queryInfo(new QueryWrapper<Member>().lambda().eq(Member::getId, id))).build());
        responseJson.ok();
        return responseJson;
    }

    @Override
    public ResponseJson<String> updateField(Integer id, Integer type, JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            switch (type) {
                case 1:
                    updateStatus(id, json.getInteger("value"));
                    break;
                default:
                    ExceptionUtil.throwClientException("非法参数：type");
            }
            dataSourceTransactionManager.commit(transactionStatus);
            responseJson.ok();
            responseJson.setData("更新成功");
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwSqlException(e.toString());
        }
        return responseJson;
    }

}