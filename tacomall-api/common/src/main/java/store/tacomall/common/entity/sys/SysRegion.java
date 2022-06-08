/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 21:07:42
 * @LastEditTime: 2021-10-10 21:08:19
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/sys/SysRegion.java
 */
package store.tacomall.common.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 码上talk
 * @since 2022-06-08
 */
@Getter
@Setter
@TableName(autoResultMap = true)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysRegion {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pId;

    private String name;

    private String adcode;

    private String level;

    private String lat;

    private String lng;

    private String center;

    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;
}