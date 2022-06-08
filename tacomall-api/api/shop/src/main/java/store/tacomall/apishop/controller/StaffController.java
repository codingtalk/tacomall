/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 21:26:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/shop/src/main/java/store/tacomall/apishop/controller/StaffController.java
 * @Just do what I think it is right
 */
package store.tacomall.apishop.controller;

import store.tacomall.apishop.service.ShopStaffService;
import store.tacomall.common.annotation.LoginUser;
import store.tacomall.common.entity.shop.ShopStaff;
import store.tacomall.common.json.ResponseJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/staff/")
public class StaffController {

    @Autowired
    private ShopStaffService shopStaffService;

    @PostMapping("loginByMobile")
    public ResponseJson<String> loginByMobile(@RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "passwd") String passwd) throws Exception {
        return shopStaffService.loginByMobile(mobile, passwd);
    }

    @LoginUser
    @PostMapping("info")
    public ResponseJson<ShopStaff> info(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        return shopStaffService.info(id);
    }

}
