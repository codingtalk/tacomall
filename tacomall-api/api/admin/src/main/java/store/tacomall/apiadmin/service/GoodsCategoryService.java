/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-18 14:29:16
 * @LastEditTime: 2021-10-22 14:04:58
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/GoodsCategoryService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsCategoryService extends IService<GoodsCategory> {

  ResponsePageJson<List<GoodsCategory>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<GoodsCategory> info(Integer id);

  ResponseJson<GoodsCategory> add(JSONObject json);

  ResponseJson<String> update(JSONObject json);

}
