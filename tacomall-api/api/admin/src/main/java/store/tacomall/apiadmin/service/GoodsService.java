/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 15:03:36
 * @LastEditTime: 2021-10-22 14:05:04
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/GoodsService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.vo.base.PageVo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsService extends IService<Goods> {

  ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<Goods> info(Integer id);

  ResponseJson<Goods> add(JSONObject json);

  ResponseJson<String> update(JSONObject json);

  ResponseJson<String> updateField(Integer id, Integer type, JSONObject json);

}