package com.tacomall.common.entity.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

import cn.hutool.json.JSONArray;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(autoResultMap = true)
public class SysApp implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONObject infoData;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONArray menu;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONObject api;

  @TableField(typeHandler = FastjsonTypeHandler.class)
  private JSONObject entity;

  @TableLogic
  private Integer isDelete;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private LocalDateTime deleteTime;

}
