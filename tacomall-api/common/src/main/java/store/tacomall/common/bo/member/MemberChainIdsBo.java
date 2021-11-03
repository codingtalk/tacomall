/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 14:51:36
 * @LastEditTime: 2021-10-21 15:21:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/bo/member/MemberChainIdsBo.java
 */
package store.tacomall.common.bo.member;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberChainIdsBo {
    private Integer memberId;
    private LocalDateTime updateTime;
}