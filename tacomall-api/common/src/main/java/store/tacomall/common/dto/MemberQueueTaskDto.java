/***
 * @Author: 码上talk|RC
 * @Date: 2021-04-09 16:59:16
 * @LastEditTime: 2021-10-10 14:19:57
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/dto/MemberQueueTaskDto.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberQueueTaskDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer type;

    // Transfer
    private Integer transferType;

    private Integer memberTransferId;

    private Integer shopShareholderContractId;

    // Member up to distributor
    private Integer memberId;

    // Reward
    private Integer rewardType;

    private Integer saleFormId;

    private Integer saleFormSwapId;

    private Integer shopTopUpId;

    // integralOwn
    private Integer ownType;

    private Integer saleFormReturnId;

    private Integer shopTopUpReturnId;

    // Blacklist of member

    private Integer blackListMemberId;

}
