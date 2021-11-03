/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 21:07:42
 * @LastEditTime: 2021-10-10 21:08:19
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/sys/SysRegion.java
 */
package store.tacomall.common.entity.sys;

import java.time.LocalDateTime;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

@Data
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