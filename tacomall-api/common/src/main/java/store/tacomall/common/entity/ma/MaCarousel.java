/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 16:10:23
 * @LastEditTime: 2021-10-26 16:10:56
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/ma/MaCarousel.java
 */
package store.tacomall.common.entity.ma;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
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
public class MaCarousel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer type;

    private String imgUrl;

    private Integer goodsId;

    private String maPath;

    private String url;

    private String title;

    private String description;

    private Integer priority;

    @TableLogic
    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

}
