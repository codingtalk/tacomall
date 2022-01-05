/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 15:12:41
 * @LastEditTime: 2022-01-05 13:53:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/vo/member/MemberInfoVo.java
 */
package store.tacomall.apiadmin.vo.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.entity.member.Member;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoVo {

    private Member member;

}
