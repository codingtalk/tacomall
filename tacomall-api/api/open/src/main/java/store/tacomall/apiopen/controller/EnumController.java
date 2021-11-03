/***
 * @Author: 码上talk|RC
 * @Date: 2020-11-14 10:56:39
 * @LastEditTime: 2021-10-06 14:23:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/controller/EnumController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.apiopen.factory.EnumFactory;

@RestController
@RequestMapping(value = "/enum")
public class EnumController {

  @Autowired
  private EnumFactory enumFactory;

  @PostMapping("lst")
  public ResponseJson<List<Map<String, Object>>> lst(@RequestParam(value = "key") String key,
      @RequestBody JSONObject json) {
    return enumFactory.getStrategy(key).query(json);
  }
}
