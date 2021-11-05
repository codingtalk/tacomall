/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-05 16:35:27
 * @LastEditTime: 2021-11-05 16:49:36
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/vo/page/PageSearchVo.java
 */
package store.tacomall.apima.vo.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.goods.Goods;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSearchVo {
  List<String> keyword;
  List<Goods> hot;
  List<Goods> favour;
}