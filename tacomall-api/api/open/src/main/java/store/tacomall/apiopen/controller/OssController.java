/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 17:19:11
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/controller/OssController.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.apiopen.service.OssService;

@RestController
@RequestMapping(value = "/oss")
public class OssController {

  @Autowired
  private OssService ossService;

  @PostMapping("authorize")
  public ResponseJson<Map<String, Object>> authorize(@RequestParam(value = "dir", defaultValue = "") String dir,
      @RequestParam(value = "insertDb", defaultValue = "0") Integer insertDb) {
    return ossService.authorize(dir, insertDb);
  }

  @PostMapping("callback")
  public ResponseJson<Object> callback(HttpServletRequest request) {
    return ossService.callback(request);
  }
}
