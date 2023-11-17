package com.tacomall.common.entity.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class SysEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String domain;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONObject config;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONObject form;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONObject detail;

  @TableLogic
  private Integer isDelete;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private LocalDateTime deleteTime;

}
