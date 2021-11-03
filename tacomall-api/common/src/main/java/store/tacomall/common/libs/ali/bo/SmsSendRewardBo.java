/***
 * @Author: 码上talk|RC
 * @Date: 2021-06-25 19:16:30
 * @LastEditTime: 2021-10-06 14:57:50
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/libs/ali/bo/SmsSendRewardBo.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.libs.ali.bo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SmsSendRewardBo {

  private String name;

  private String time;

  private String member;

  private BigDecimal money;

  private BigDecimal totalamount;

  private BigDecimal integral;

  private String grade;
}
