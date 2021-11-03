/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 10:55:15
 * @LastEditTime: 2021-10-27 10:55:16
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/GoodsService.java
 */
package store.tacomall.apima.service;

import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsService extends IService<Goods> {

    ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

}
