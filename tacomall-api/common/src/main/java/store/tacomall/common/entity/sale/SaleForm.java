/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:47:38
 * @LastEditTime: 2022-01-05 14:30:51
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/entity/sale/SaleForm.java
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
public class SaleForm implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String sn;

  /**
   * 1->零售订单；2->会员订单；
   */
  private Integer type;

  private Integer shopId;

  private Integer memberId;

  private BigDecimal amountTotal;

  private BigDecimal amountPay;

  private Integer activityId;

  private BigDecimal activityAmount;

  private Integer couponId;

  private BigDecimal couponAmount;

  private String payWay;

  private Integer status;

  @TableLogic
  private Integer isDelete;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  private LocalDateTime deleteTime;

  @TableField(exist = false)
  private List<SaleFormGoodsItems> saleFormGoodsItemsList;

}
