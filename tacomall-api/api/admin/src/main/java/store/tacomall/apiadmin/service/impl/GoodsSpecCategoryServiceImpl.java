
package store.tacomall.apiadmin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
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

import store.tacomall.apiadmin.service.GoodsSpecCategoryService;
import store.tacomall.common.entity.goods.GoodsSpecCategory;
import store.tacomall.common.entity.goods.GoodsSpecKey;
import store.tacomall.common.entity.goods.GoodsSpecValue;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.goods.GoodsSpecCategoryMapper;
import store.tacomall.common.mapper.goods.GoodsSpecKeyMapper;
import store.tacomall.common.mapper.goods.GoodsSpecValueMapper;
import store.tacomall.common.util.ExceptionUtil;

@Service
public class GoodsSpecCategoryServiceImpl extends ServiceImpl<GoodsSpecCategoryMapper, GoodsSpecCategory>
        implements GoodsSpecCategoryService {

    @Autowired
    GoodsSpecKeyMapper goodsSpecKeyMapper;

    @Autowired
    GoodsSpecValueMapper goodsSpecValueMapper;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public ResponsePageJson<List<GoodsSpecCategory>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
        ResponsePageJson<List<GoodsSpecCategory>> responsePageVo = new ResponsePageJson<>();
        Page<GoodsSpecCategory> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<GoodsSpecCategory> lqw = new QueryWrapper<GoodsSpecCategory>();
        lqw.orderByDesc("create_time");
        IPage<GoodsSpecCategory> result = baseMapper.selectPage(page, lqw);
        responsePageVo.setData(result.getRecords());
        responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePageVo.ok();
        return responsePageVo;
    }

    @Override
    public ResponseJson<GoodsSpecCategory> info(Integer id) {
        ResponseJson<GoodsSpecCategory> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper
                .queryInfo(new QueryWrapper<GoodsSpecCategory>().lambda().eq(GoodsSpecCategory::getId, id)));
        responseJson.ok();
        return responseJson;
    }

    @Override
    public ResponseJson<GoodsSpecCategory> add(JSONObject json) {
        ResponseJson<GoodsSpecCategory> responseJson = new ResponseJson<>();
        GoodsSpecCategory goodsSpecCategory = JSON.toJavaObject(json, GoodsSpecCategory.class);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            baseMapper.insert(goodsSpecCategory);
            goodsSpecCategory.getGoodsSpecKeyList().stream().forEach((GoodsSpecKey goodsSpecKey) -> {
                goodsSpecKey.setSpecCategoryId(goodsSpecCategory.getId());
                goodsSpecKeyMapper.insert(goodsSpecKey);
                goodsSpecKey.getGoodsSpecValueList().stream().forEach((GoodsSpecValue goodsSpecValue) -> {
                    goodsSpecValue.setKeyId(goodsSpecKey.getId());
                    goodsSpecValueMapper.insert(goodsSpecValue);
                });
            });
            dataSourceTransactionManager.commit(transactionStatus);
            responseJson.ok();
            responseJson.setData(goodsSpecCategory);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwSqlException(e.toString());
        }
        return responseJson;
    }

    @Override
    public ResponseJson<String> update(JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        GoodsSpecCategory goodsSpecCategory = JSON.toJavaObject(json, GoodsSpecCategory.class);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            List<GoodsSpecKey> k = new ArrayList<>();
            baseMapper.updateById(goodsSpecCategory);
            List<GoodsSpecKey> goodsSpecKeyList = goodsSpecKeyMapper.selectList(new QueryWrapper<GoodsSpecKey>()
                    .lambda().eq(GoodsSpecKey::getSpecCategoryId, goodsSpecCategory.getId()));
            goodsSpecCategory.getGoodsSpecKeyList().stream().forEach((GoodsSpecKey goodsSpecKey) -> {
                if (ObjectUtil.equal(goodsSpecKey.getId(), 0)) {
                    goodsSpecKey.setSpecCategoryId(goodsSpecCategory.getId());
                    goodsSpecKeyMapper.insert(goodsSpecKey);
                    goodsSpecKey.getGoodsSpecValueList().stream().forEach((GoodsSpecValue goodsSpecValue) -> {
                        goodsSpecValue.setKeyId(goodsSpecKey.getId());
                        goodsSpecValueMapper.insert(goodsSpecValue);
                    });
                    return;
                }
                goodsSpecKeyMapper.updateById(goodsSpecKey);
                List<GoodsSpecValue> j = new ArrayList<>();
                List<GoodsSpecValue> goodsSpecValueList = goodsSpecValueMapper.selectList(
                        new QueryWrapper<GoodsSpecValue>().lambda().eq(GoodsSpecValue::getKeyId, goodsSpecKey.getId()));

                goodsSpecKey.getGoodsSpecValueList().stream().forEach((GoodsSpecValue goodsSpecValue) -> {
                    if (ObjectUtil.equal(goodsSpecValue.getId(), 0)) {
                        goodsSpecValue.setKeyId(goodsSpecKey.getId());
                        goodsSpecValueMapper.insert(goodsSpecValue);
                        return;
                    }
                    goodsSpecValueMapper.updateById(goodsSpecValue);
                    j.add(goodsSpecValue);
                });
                goodsSpecValueList.stream().forEach((GoodsSpecValue goodsSpecValue) -> {
                    if (!j.stream().map(GoodsSpecValue::getId).collect(Collectors.toList())
                            .contains(goodsSpecValue.getId())) {
                        goodsSpecValueMapper.deleteById(goodsSpecValue.getId());
                    }
                });
                k.add(goodsSpecKey);
            });
            goodsSpecKeyList.stream().forEach((GoodsSpecKey goodsSpecKey) -> {
                if (!k.stream().map(GoodsSpecKey::getId).collect(Collectors.toList()).contains(goodsSpecKey.getId())) {
                    goodsSpecKeyMapper.deleteById(goodsSpecKey.getId());
                }
            });
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
