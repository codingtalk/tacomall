/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 15:06:09
 * @LastEditTime: 2021-10-26 15:25:47
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/controller/MemberController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.apiadmin.service.MemberService;
import store.tacomall.apiadmin.vo.member.MemberInfoVo;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;

@RestController
@RequestMapping(value = "/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("page")
    public ResponsePageJson<List<PageVo>> page(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return memberService.page(pageIndex, pageSize, json);
    }

    @PostMapping("info")
    public ResponseJson<MemberInfoVo> info(@RequestParam(value = "id") Integer id) {
        return memberService.info(id);
    }

    @PostMapping("updateField")
    public ResponseJson<String> updateField(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "type") Integer type, @RequestBody JSONObject json) {
        return memberService.updateField(id, type, json);
    }

}
