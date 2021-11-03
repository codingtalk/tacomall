/***
 * @Author: 码上talk|RC
 * @Date: 2021-02-19 16:32:43
 * @LastEditTime: 2021-10-10 14:08:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/tm/TmStaff.java
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
public class TmStaff {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer deptId;

    private Integer jobId;

    private Integer isAdmin;

    private String accessExtraJson;

    private String username;

    private String mobile;

    private String passwd;

    private String nickname;

    private Integer status;

    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    @TableField(exist = false)
    private TmDept tmDept;

    @TableField(exist = false)
    private TmJob tmJob;

}