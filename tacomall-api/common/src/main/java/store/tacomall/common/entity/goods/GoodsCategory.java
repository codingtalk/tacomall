/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 16:46:16
 * @LastEditTime: 2021-10-27 13:46:52
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/goods/GoodsCategory.java
 */
package store.tacomall.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class GoodsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pId;

    private String name;

    private String thumbnail;

    @TableLogic
    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    @TableField(exist = false)
    private List<GoodsCategory> sub;

}
