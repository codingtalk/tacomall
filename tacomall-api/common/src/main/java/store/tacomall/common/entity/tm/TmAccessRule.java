/***
 * @Author: 码上talk|RC
 * @Date: 2021-02-19 16:32:43
 * @LastEditTime: 2021-05-29 08:37:24
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/cn/tacomall/common/entity/Tm/TmAccessRule.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.entity.tm;

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
public class TmAccessRule {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pId;

    private String chainIds;

    private String name;

    private String reg;

    private String description;

    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}