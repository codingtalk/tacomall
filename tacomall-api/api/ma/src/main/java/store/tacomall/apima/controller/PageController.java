
/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-13 10:51:51
 * @LastEditTime: 2021-10-26 17:28:42
 * @LastEditors: 码上talk|RC
 * @Description: package store.tacomall.apima.controller;
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/controller/PageController.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.annotation.LoginUser;
import store.tacomall.apima.factory.PageFactory;

@RestController
@RequestMapping(value = "/page/")
public class PageController {

    @Autowired
    private PageFactory pageFactory;

    @PostMapping("info")
    @LoginUser(required = false)
    public ResponseJson<Object> info(@RequestParam(value = "key") String key, @RequestBody JSONObject json) {
        return pageFactory.getStrategy(key).loadPageInfo(json);
    }
}
