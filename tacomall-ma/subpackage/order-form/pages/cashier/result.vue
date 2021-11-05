<!--
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2021-11-02 17:23:39
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/subpackage/order-form/pages/cashier/result.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <view class="page cashier-result">
    <view class="r-result">
      <text class="iconfont">&#xe62b;</text>
      <text class="text">交易成功</text>
    </view>
    <view class="r-info">
      <view class="i-item">
        <view class="item-title">
          <text>订单金额</text>
        </view>
        <view class="item-text">
          <text>￥{{0 | amountFormat}}元</text>
        </view>
      </view>
      <view class="i-item">
        <view class="item-title">
          <text>订单编号</text>
        </view>
        <view class="item-text">
          <text>201803121213242100</text>
        </view>
      </view>
      <view class="i-item">
        <view class="item-title">
          <text>收货地址</text>
        </view>
        <view class="item-text">
          <text>中国 广东 佛山 顺德 北滘镇莘村中学（528300） 邓海标 13680065830</text>
        </view>
      </view>
      <view class="i-item">
        <view class="item-title">
          <text>送货时间</text>
        </view>
        <view class="item-text">
          <text>不限送货时间</text>
        </view>
      </view>
      <view class="i-item">
        <view class="item-title">
          <text>支付方式</text>
        </view>
        <view class="item-text">
          <text>微信支付</text>
        </view>
      </view>
    </view>
    <view class="r-footer">
      <view class="f-info">
        <text class="i-text">合计</text>
        <text class="i-price">￥1236.00</text>
      </view>
      <view class="f-check" @tap="red('/sub-pages/order/list')">
        <text>查看订单</text>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  methods: {
  },
  onLoad (params) {

  }
}
</script>

<style lang="less">
@import "./less/result";
</style>
