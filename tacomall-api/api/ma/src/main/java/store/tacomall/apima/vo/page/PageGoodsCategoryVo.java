/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 16:13:57
 * @LastEditTime: 2021-10-27 13:52:05
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/vo/page/PageGoodsCategoryVo.java
 */
package store.tacomall.apima.vo.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.goods.GoodsCategory;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageGoodsCategoryVo {
    List<GoodsCategory> goodsCategoryList;
}