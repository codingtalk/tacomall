/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 21:26:16
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/shop/src/main/java/store/tacomall/apishop/service/ShopStaffService.java
 * @Just do what I think it is right
 */
package store.tacomall.apishop.service;

import store.tacomall.common.entity.shop.ShopStaff;
import store.tacomall.common.json.ResponseJson;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ShopStaffService extends IService<ShopStaff> {

    ResponseJson<String> loginByMobile(JSONObject body) throws Exception;

    ResponseJson<ShopStaff> info(JSONObject body);

}
