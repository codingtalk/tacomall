<template>
  <view class="page center">
    <view class="c-header">
      <view class="h-right">
        <text class="iconfont">&#xe674;</text>
      </view>
    </view>
    <view
      class="c-account c-account-is-login"
      @tap="nav('/subpackage/member/pages/account/index')"
      v-if="isLogin"
    >
      <view class="a-left">
        <view class="l-avatar">
          <img :src="info.avatar" alt />
        </view>
        <view class="l-name">
          <text>{{info.realName}}</text>
        </view>
      </view>
      <view class="a-right">
        <text class="iconfont">&#xe93d;</text>
      </view>
    </view>
    <view class="c-account c-account-no-login" @tap="nav('/pages/login/index')" v-else>
      <view class="a-left">
        <view class="l-avatar">
          <img src="https://m.mi.com/static/img/avatar.76a75b8f17.png" alt />
        </view>
        <view class="l-name">
          <text>请登录</text>
        </view>
      </view>
      <view class="a-right">
        <text class="iconfont">&#xe93d;</text>
      </view>
    </view>
    <view class="c-order">
      <view class="o-header">
        <view class="h-left">
          <text>我的订单</text>
        </view>
        <view class="h-right" @tap="nav('/subpackage/order-form/pages/list/index')">
          <text>全部订单</text>
          <text class="iconfont">&#xe93d;</text>
        </view>
      </view>
      <view class="o-content border-1px-top">
        <view class="c-item">
          <text class="iconfont">&#xe673;</text>
          <text class="i-text">待付款</text>
          <text
            class="i-tag"
            v-if="$_.get(PAGE_INFO, 'sumOrderUnPay')"
          >{{$_.get(PAGE_INFO, 'sumOrderUnPay')}}</text>
        </view>
        <view class="c-item">
          <text class="iconfont">&#xe62f;</text>
          <text class="i-text">待收货</text>
          <text
            class="i-tag"
            v-if="$_.get(PAGE_INFO, 'sumOrderPayed')"
          >{{$_.get(PAGE_INFO, 'sumOrderPayed')}}</text>
        </view>
        <view class="c-item">
          <text class="iconfont">&#xe672;</text>
          <text class="i-text">待评价</text>
          <text
            class="i-tag"
            v-if="$_.get(PAGE_INFO, 'sumOrderFinished')"
          >{{$_.get(PAGE_INFO, 'sumOrderFinished')}}</text>
        </view>
        <view class="c-item">
          <text class="iconfont">&#xe622;</text>
          <text class="i-text">退款/售后</text>
          <text
            class="i-tag"
            v-if="$_.get(PAGE_INFO, 'sumOrderReturn')"
          >{{$_.get(PAGE_INFO, 'sumOrderReturn')}}</text>
        </view>
      </view>
    </view>
    <view class="c-service">
      <view class="s-header">
        <view class="h-left">
          <text>我的服务</text>
        </view>
      </view>
      <view class="s-content">
        <view class="c-line border-1px-top">
          <view class="l-item">
            <text class="iconfont">&#xe69b;</text>
            <text>拼团订单</text>
          </view>
          <view class="l-item">
            <text class="iconfont">&#xe63c;</text>
            <text>优惠券</text>
          </view>
          <view class="l-item">
            <text class="iconfont">&#xe608;</text>
            <text>新人邀请</text>
            <text>立享红包</text>
          </view>
          <view class="l-item">
            <text class="iconfont">&#xe616;</text>
            <text>分享</text>
          </view>
        </view>
        <view class="c-line border-1px-top">
          <view class="l-item">
            <text class="iconfont">&#xe617;</text>
            <text>我的收藏</text>
          </view>
          <view class="l-item">
            <text class="iconfont">&#xe600;</text>
            <text>地址管理</text>
          </view>
          <view class="l-item">
            <text class="iconfont">&#xe675;</text>
            <text>意见反馈</text>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import mixinPageInfo from '@/mixins/type/pageInfo';

export default {
  mixins: [mixinPageInfo],
  onPullDownRefresh () {
    this._boot().then(res => {
      uni.stopPullDownRefresh();
    });
  },
  data () {
    return {
      PAGE_CONFIG: {
        key: 'center'
      }
    }
  },
};
</script>

<style lang="less">
@import "./index";
</style>
