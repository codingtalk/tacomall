package com.tacomall.common.entity.member;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.tacomall.common.bo.member.MemberChainIdsBo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@TableName(autoResultMap = true)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private Integer inviteMemberId;

    private Integer inviteUpperMemberId;

    private String passwd;

    private String mobile;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<MemberChainIdsBo> chainIds;

    private String inviteCode;

    private String openId;

    private String nickname;

    private String realName;

    private String avatar;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}