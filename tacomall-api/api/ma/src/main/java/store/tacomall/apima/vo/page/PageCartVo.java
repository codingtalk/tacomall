/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 21:17:09
 * @LastEditTime: 2021-10-26 21:18:21
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/vo/page/PageCartVo.java
 */
package store.tacomall.apima.vo.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.member.MemberCart;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageCartVo {
    List<MemberCart> cartList;
}