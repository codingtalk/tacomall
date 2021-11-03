/***
 * @Author: 码上talk|RC
 * @Date: 2021-05-29 08:35:33
 * @LastEditTime: 2021-05-29 08:36:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/cn/tacomall/common/entity/Tm/TmJob.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.entity.tm;

import java.time.LocalDateTime;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

@Data
public class TmJob {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer deptId;

    private String name;

    private String description;

    private String ignoreAccessRuleIds;

    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}