/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 10:56:21
 * @LastEditTime: 2021-11-04 16:48:45
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/impl/GoodsServiceImpl.java
 */
package store.tacomall.apima.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import store.tacomall.apima.service.GoodsService;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.goods.GoodsMapper;
import store.tacomall.common.vo.base.PageVo;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
        ResponsePageJson<List<PageVo>> responsePageVo = new ResponsePageJson<>();
        Page<PageVo> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<Goods> qw = new QueryWrapper<Goods>();
        qw.eq("g.is_delete", 0);
        qw.orderByDesc("g.create_time");
        IPage<PageVo> result = baseMapper.queryPage(page, qw);
        responsePageVo.setData(result.getRecords());
        responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePageVo.ok();
        return responsePageVo;
    }

}
