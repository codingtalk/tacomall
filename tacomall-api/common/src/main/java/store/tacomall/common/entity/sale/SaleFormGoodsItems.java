/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:46:32
 * @LastEditTime: 2021-11-05 15:12:32
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/order/SaleFormGoodsItems.java
 */
package store.tacomall.common.entity.sale;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import store.tacomall.common.entity.goods.GoodsItems;

/**
 * <p>
 * 
 * </p>
 *
 * @author 码上talk
 * @since 2021-11-01
 */
@Data
@Builder
public class SaleFormGoodsItems implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private Integer formId;

  private Integer goodsItemsId;

  private Integer quantity;

  private BigDecimal amount;

  @TableLogic
  private Integer isDelete;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private LocalDateTime deleteTime;

  @TableField(exist = false)
  private GoodsItems goodsItems;

}
