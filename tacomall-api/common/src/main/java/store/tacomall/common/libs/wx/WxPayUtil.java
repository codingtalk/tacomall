/***
 * @Author: 码上talk|RC
 * @Date: 2021-07-07 08:23:21
 * @LastEditTime: 2021-10-11 22:26:48
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/libs/wx/WxPayUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.libs.wx;

import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.common.libs.wx.bo.OrderCreateBo;

@Component
public class WxPayUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxPayService wxService;

    public String createOrder(OrderCreateBo orderCreateBo) throws Exception {
        orderCreateBo.setTradeType("NATIVE");
        WxPayUnifiedOrderRequest reqObj = (WxPayUnifiedOrderRequest) orderCreateBo;
        try {
            WxPayNativeOrderResult result = this.wxService.createOrder(reqObj);
            return result.getCodeUrl();
        } catch (Exception e) {
            logger.error(e.toString());
            throw new Exception("创建微信订单错误");
        }
    }

}