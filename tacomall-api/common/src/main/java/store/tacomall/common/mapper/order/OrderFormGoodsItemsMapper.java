/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:46:32
 * @LastEditTime: 2021-11-05 14:48:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/mapper/order/OrderFormGoodsItemsMapper.java
 */
package store.tacomall.common.mapper.order;

import store.tacomall.common.entity.order.OrderFormGoodsItems;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 码上talk
 * @since 2021-11-01
 */
@Repository
public interface OrderFormGoodsItemsMapper extends BaseMapper<OrderFormGoodsItems> {

    List<OrderFormGoodsItems> queryOrderFormGoodsItemsListByFormId(@Param("formId") Integer formId);

}
