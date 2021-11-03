/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 13:47:47
 * @LastEditTime: 2021-10-26 15:23:08
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/member/Member.java
 */
package store.tacomall.common.entity.member;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import store.tacomall.common.bo.member.MemberChainIdsBo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

@Data
@TableName(autoResultMap = true)
public class Member {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer typeId;

    private Integer levelId;

    private Integer inviteMemberId;

    private String passwd;

    private String mobile;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<MemberChainIdsBo> chainIds;

    private String inviteCode;

    private String inviteWxPic;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}
