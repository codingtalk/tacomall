/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:57:16
 * @LastEditTime: 2021-11-01 17:57:17
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/goods/GoodsItemsQuantityBo.java
 */
package store.tacomall.common.bo.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsItemsQuantityBo {

    private Integer goodsItemsId;

    private Integer quantity;

}
