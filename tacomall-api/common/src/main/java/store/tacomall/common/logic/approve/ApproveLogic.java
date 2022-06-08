/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-12-02 11:10:15
 * @LastEditTime: 2021-12-28 13:59:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /server-api/common/src/main/java/com/tszl/common/logic/approve/ApproveLogic.java
 */
package store.tacomall.common.logic.approve;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.entity.approve.Approve;
import store.tacomall.common.json.ResponseJson;

public interface ApproveLogic extends IService<Approve> {

    ResponseJson<String> doAgree(Integer id, Integer isAgree, JSONObject json);

    ResponseJson<String> cancel(Integer id, JSONObject json);
}
