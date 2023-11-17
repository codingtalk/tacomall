package com.tacomall.common.entity.approve;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.entity.org.OrgStaff;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Approve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String sn;

    private Integer creatorId;

    private Integer executorId;

    private Integer typeId;

    private Integer nodeId;

    /**
     * pendding->审核中;approved->已通过；disapproved->未通过；cancel->已取消
     */
    private String status;

    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    @TableField(exist = false)
    private OrgStaff creator;

    @TableField(exist = false)
    private OrgStaff executor;

    @TableField(exist = false)
    private ApproveType approveType;

    @TableField(exist = false)
    private ApproveNode approveNode;

    @TableField(exist = false)
    private ApproveProcess approveProcess;

    @TableField(exist = false)
    private List<ApproveNode> approveNodeList;

    @TableField(exist = false)
    private List<ApproveProcess> approveProcessList;

    @TableField(exist = false)
    private FormOrder formOrder;

}
