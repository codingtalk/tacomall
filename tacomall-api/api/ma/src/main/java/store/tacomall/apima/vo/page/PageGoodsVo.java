/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 17:52:13
 * @LastEditTime: 2021-10-26 21:14:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/vo/page/PageGoodsVo.java
 */
package store.tacomall.apima.vo.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.goods.Goods;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageGoodsVo {
    Goods goods;
}