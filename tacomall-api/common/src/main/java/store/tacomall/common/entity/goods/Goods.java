/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-21 16:47:30
 * @LastEditTime: 2021-10-23 16:43:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/goods/Goods.java
 */
package store.tacomall.common.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String categoryId;

    private Integer specCategoryId;

    private String name;

    private String description;

    private String thumbnail;

    private String gallery;

    private String detailContent;

    private BigDecimal amount;

    private BigDecimal amountCost;

    private Integer isOnSale;

    private Integer isHot;

    private Integer priority;

    private Integer status;

    @TableLogic
    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    @TableField(exist = false)
    private GoodsCategory goodsCategory;

    @TableField(exist = false)
    private GoodsSpecCategory goodsSpecCategory;

    @TableField(exist = false)
    private List<GoodsItems> goodsItemsList;

}
