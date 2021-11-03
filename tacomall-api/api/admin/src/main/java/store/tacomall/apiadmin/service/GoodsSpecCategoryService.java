/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 15:03:36
 * @LastEditTime: 2021-10-22 14:05:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/GoodsSpecCategoryService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import store.tacomall.common.entity.goods.GoodsSpecCategory;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsSpecCategoryService extends IService<GoodsSpecCategory> {

  ResponsePageJson<List<GoodsSpecCategory>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<GoodsSpecCategory> info(Integer id);

  ResponseJson<GoodsSpecCategory> add(JSONObject json);

  ResponseJson<String> update(JSONObject json);

}