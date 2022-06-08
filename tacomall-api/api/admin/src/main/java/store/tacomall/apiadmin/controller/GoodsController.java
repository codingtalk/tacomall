/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 15:03:08
 * @LastEditTime: 2021-10-27 10:59:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/controller/GoodsController.java
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.apiadmin.service.GoodsCategoryService;
import store.tacomall.apiadmin.service.GoodsService;
import store.tacomall.apiadmin.service.GoodsSpecCategoryService;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.entity.goods.GoodsSpecCategory;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;

@RestController
@RequestMapping(value = "/goods/")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private GoodsSpecCategoryService goodsSpecCategoryService;

    @PostMapping("page")
    public ResponsePageJson<List<PageVo>> page(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return goodsService.page(pageIndex, pageSize, json);
    }

    @PostMapping("info")
    public ResponseJson<Goods> info(@RequestParam(value = "id") Integer id) {
        return goodsService.info(id);
    }

    @PostMapping("add")
    public ResponseJson<Goods> add(@RequestBody JSONObject json) {
        return goodsService.add(json);
    }

    @PostMapping("update")
    public ResponseJson<String> update(@RequestBody JSONObject json) {
        return goodsService.update(json);
    }

    @PostMapping("updateField")
    public ResponseJson<String> updateField(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "type") Integer type, @RequestBody JSONObject json) {
        return goodsService.updateField(id, type, json);
    }

    @PostMapping("categoryPage")
    public ResponsePageJson<List<GoodsCategory>> categoryPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return goodsCategoryService.page(pageIndex, pageSize, json);
    }

    @PostMapping("categoryAdd")
    public ResponseJson<GoodsCategory> categoryAdd(@RequestBody JSONObject json) {
        return goodsCategoryService.add(json);
    }

    @PostMapping("categoryInfo")
    public ResponseJson<GoodsCategory> categoryInfo(@RequestParam(value = "id") Integer id) {
        return goodsCategoryService.info(id);
    }

    @PostMapping("categoryUpdate")
    public ResponseJson<String> categoryUpdate(@RequestBody JSONObject json) {
        return goodsCategoryService.update(json);
    }

    @PostMapping("specCategoryPage")
    public ResponsePageJson<List<GoodsSpecCategory>> specCategoryPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return goodsSpecCategoryService.page(pageIndex, pageSize, json);
    }

    @PostMapping("specCategoryInfo")
    public ResponseJson<GoodsSpecCategory> specCategoryInfo(@RequestParam(value = "id") Integer id) {
        return goodsSpecCategoryService.info(id);
    }

    @PostMapping("specCategoryAdd")
    public ResponseJson<GoodsSpecCategory> specCategoryAdd(@RequestBody JSONObject json) {
        return goodsSpecCategoryService.add(json);
    }

    @PostMapping("specCategoryUpdate")
    public ResponseJson<String> specCategoryUpdate(@RequestBody JSONObject json) {
        return goodsSpecCategoryService.update(json);
    }

}
