/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 13:51:29
 * @LastEditTime: 2021-10-10 14:08:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/tm/TmLoginLog.java
 */
package store.tacomall.common.entity.tm;

import java.time.LocalDateTime;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

@Data
public class TmLoginLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String ip;

    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}
