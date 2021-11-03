/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:48:54
 * @LastEditTime: 2021-10-29 14:18:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/goods/GoodsItems.java
 */
package store.tacomall.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import store.tacomall.common.bo.goods.GoodsItemsSpecJsonBo;

/**
 * <p>
 * 
 * </p>
 *
 * @author 码上talk
 * @since 2021-10-12
 */
@Data
@TableName(autoResultMap = true)
public class GoodsItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goodsId;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<GoodsItemsSpecJsonBo> specJson;

    private Integer stock;

    private Integer stockFreeze;

    @TableLogic
    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    @TableField(exist = false)
    private Goods goods;

}
