/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 17:22:02
 * @LastEditTime: 2021-10-27 10:46:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/vo/page/PageIndexVo.java
 */
package store.tacomall.apima.vo.page;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.entity.ma.MaCarousel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageIndexVo {
    List<MaCarousel> maCarouselList;
    List<GoodsCategory> goodsCategoryList;
    List<Map<String, Object>> sections;
}