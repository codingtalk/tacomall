<!--
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-02 16:37:47
 * @LastEditTime: 2021-11-02 17:23:08
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/subpackage/order-form/pages/cashier/settle.vue
-->
<template>
  <view class="page cashier-settle">
    <view class="s-address">
      <view class="a-left">
        <view class="l-top">
          <text class="t-name">张三</text>
          <text class="t-phone">136******30</text>
        </view>
        <view class="l-bottom">
          <text>中国 广东 白云区 xx xxxxx（528300）</text>
        </view>
      </view>
      <view class="a-right">
        <text class="iconfont">&#xe93d;</text>
      </view>
    </view>
    <view class="s-pay">
      <view class="p-item">
        <view class="i-left">
          <text class="iconfont" style="color: #00c800;">&#xe7e5;</text>
          <text class="l-name">微信支付</text>
          <text class="l-tag">推荐</text>
        </view>
        <view class="i-right">
          <text class="iconfont" style="color:#845d32;">&#xe640;</text>
        </view>
      </view>
      <view class="p-item border-1px-top">
        <view class="i-left">
          <text class="iconfont" style="color: #00aaef;">&#xe64b;</text>
          <text class="l-name">支付宝支付</text>
        </view>
        <view class="i-right">
          <text class="iconfont">&#xe651;</text>
        </view>
      </view>
    </view>
    <view class="s-goods">
      <view class="g-header">
        <text>商品详情</text>
      </view>
      <view class="g-content border-1px-top border-1px-bottom">
        <view class="c-item" :key="key" v-for="(item, key) in []">
          <view class="i-pic">
            <image
              src="https://static.home.mi.com/app/shop/img?id=shop_f4e9e198a07c9888cd3a4295a0f54b0b.jpeg&w=240&h=240"
            />
          </view>
          <view class="i-info">
            <view class="i-name">
              <text>{{item.goodsItem.name}}</text>
            </view>
          </view>
          <view class="i-price-num">
            <text class="single-price">￥{{item.goodsItem.amount | amountFormat}}</text>
            <text class="num">x{{item.quantity}}</text>
          </view>
        </view>
      </view>
    </view>
    <view class="s-others">
      <view class="o-cell">
        <view class="c-left">
          <text>优惠券/优惠码</text>
        </view>
        <view class="c-right">
          <text class="iconfont">&#xe93d;</text>
        </view>
      </view>
      <view class="o-cell">
        <view class="c-left">
          <text>配送方式</text>
        </view>
        <view class="c-right">
          <text>快递配送</text>
          <text class="iconfont">&#xe93d;</text>
        </view>
      </view>
      <view class="o-cell">
        <view class="c-left">
          <text>送货时间</text>
        </view>
        <view class="c-right">
          <text>不限时间</text>
          <text class="iconfont">&#xe93d;</text>
        </view>
      </view>
    </view>
    <view class="s-footer">
      <view class="f-info">
        <text class="i-text">合计</text>
        <text class="i-price">￥{{0 | amountFormat}}</text>
      </view>
      <view class="f-action">
        <text>去支付</text>
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
@import "./less/settle";
</style>
