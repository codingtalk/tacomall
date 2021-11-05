<!--
 * @Author: 码上talk|RC
 * @Date: 2020-10-26 14:44:28
 * @LastEditTime: 2021-11-05 17:18:14
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/components/goods-card/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <view class="goods-card" @tap="$emit('selected', goods)">
    <view class="c-top">
      <view class="t-pic">
        <img-loader :src="goods.cover"></img-loader>
      </view>
      <view class="t-desc">
        <text>120X 超远变焦、120W秒充科技、120Hz 专业屏幕</text>
      </view>
    </view>
    <view class="c-bottom">
      <view class="b-name">
        <text>{{goods.name}}</text>
      </view>
      <view class="b-price">
        <text>￥1999.00</text>
      </view>
    </view>
  </view>
</template>

<script>
import imgLoader from '../img-loader'
export default {
  components: {
    imgLoader
  },
  props: ['goods']
}
</script>

<style lang="less">
.goods-card {
  width: 100%;
  .c-top {
    padding: 28rpx;
    background: white;
    .t-pic {
      width: 100%;
      height: 319rpx;
    }
    .t-desc {
      width: 100%;
      text {
        width: 100%;
        display: inline-block;
        overflow: hidden;
        padding: 14rpx 0;
        white-space: nowrap;
        overflow-x: hidden;
        text-overflow: ellipsis;
        color: #845f3f;
      }
    }
  }
  .c-bottom {
    padding: 0 28rpx;
    background: #f7f7f7;
    .b-name {
      padding-top: 15rpx;
      text {
        display: inline-block;
        width: 100%;
        white-space: nowrap;
        overflow-x: hidden;
        text-overflow: ellipsis;
        font-size: 32rpx;
        color: #333;
      }
    }
    .b-price {
      padding: 15rpx 0;
      text {
        color: #a21c20;
      }
    }
  }
}
</style>