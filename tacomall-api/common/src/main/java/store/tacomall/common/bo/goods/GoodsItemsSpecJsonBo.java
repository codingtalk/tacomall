/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 13:50:33
 * @LastEditTime: 2021-10-26 13:54:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/goods/GoodsItemsSpecJsonBo.java
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
public class GoodsItemsSpecJsonBo {

    private String key;

    private String value;

    private Integer valueId;

}
