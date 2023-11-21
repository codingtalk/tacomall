<template>
  <view class="page index">
    <weixin-wrapper>
      <template v-slot:nav>
        <view class="index_slogan">
          <view class="slogan_logo">
            <image :src="STATIC_PATH('logo-icon_small.png')" />
          </view>
          <view class="slogan_search">
            <view class="search_left">
              <text class="iconfont">&#xe86e;</text>
              <text class="left_text">搜索好品，共有16523件商品</text>
            </view>
          </view>
        </view>
      </template>
      <view class="index_main">
        <view class="main_tabs">
          <scroll-view>
            <view class="tabs_wrapper">
              <view class="wrapper_item" :key="key" v-for="(item, key) in getVal('productClassificationForNavList', [])">
                <text>{{ item.name }}</text>
              </view>
            </view>
          </scroll-view>
        </view>
        <view class="main_banner">
          <swiper class="banner_swiper" :indicator-dots="true" :autoplay="true" interval="2000" duration="3000">
            <swiper-item :key="key" v-for="(item, key) in getVal('carousalList', [])">
              <view class="swiper_item">
                <image :src="item.url" />
              </view>
            </swiper-item>
          </swiper>
        </view>
        <view class="main_service">
          <view class="service_item">
            <text class="iconfont">&#xe6a4;</text>
            <text class="item_text">精选品牌</text>
          </view>
          <view class="service_item">
            <text class="iconfont">&#xe609;</text>
            <text class="item_text">30天无忧退换</text>
          </view>
          <view class="service_item">
            <text class="iconfont">&#xe600;</text>
            <text class="item_text">48小时快速退款</text>
          </view>
        </view>
        <view class="main_category">
          <view class="category_item" :key="key" v-for="(item, key) in getVal('productClassificationForGridList', [])">
            <image src="http://yanxuan.nosdn.127.net/fede8b110c502ec5799702d5ec824792.png" />
            <text>{{ item.name }}</text>
          </view>
        </view>
        <view class="main_flash">
          <view class="flash_header">
            <div class="header_left">
              <view class="left_text">
                <text>限时购</text>
              </view>
              <view class="left_time">
                <view class="time_item">
                  <text>01</text>
                </view>
                <view class="time_item">
                  <text>23</text>
                </view>
                <view class="time_item">
                  <text>12</text>
                </view>
              </view>
            </div>
            <div class="header_right">
              <text>更多</text>
              <text class="iconfont">&#xe601;</text>
            </div>
          </view>
          <view class="flash_content">
            <view class="content_item" :key="key" v-for="(item, key) in getVal('productForSecKill', [])">
              <view class="item_image">
                <image
                  src="http://yanxuan.nosdn.127.net/598a7792fdef09260c6c6fb0ca4fa5cc.png?imageView&thumbnail=216x216&quality=75" />
              </view>
              <view class="item_price">
                <text>￥10</text>
                <text>￥5.0</text>
              </view>
            </view>
          </view>
        </view>
        <template v-for="(item, key) in []">
          <view class="main_direct-supply" :key="key" v-if="item.k == 'HOT'">
            <view class="supply_header">
              <div class="header_left">
                <text>{{ item.name }}</text>
              </div>
              <div class="header_right">
                <text>更多</text>
                <text class="iconfont">&#xe93d;</text>
              </div>
            </view>
            <view class="supply_content">
              <view class="content_item" :key="key" v-for="(item, key) in []">
                <view class="item_desc">
                  <text>海外制造商</text>
                  <text>24元起</text>
                </view>
                <image
                  src="http://yanxuan.nosdn.127.net/74e2ea8f81004d0a60f90fc8e4649058.png?imageView&thumbnail=343y260&enlarge=1" />
              </view>
            </view>
          </view>
          <view class="main_newest" :key="key" v-if="false">
            <view class="newest_header">
              <div class="header_left">
                <text>{{ item.name }}</text>
              </div>
              <div class="header_right">
                <text>更多</text>
                <text class="iconfont">&#xe93d;</text>
              </div>
            </view>
            <view class="newest_content">
              <view class="content_item" :key="key" v-for="(item, key) in []">
                <view class="item_image">
                  <image
                    src="http://yanxuan.nosdn.127.net/598a7792fdef09260c6c6fb0ca4fa5cc.png?imageView&thumbnail=216x216&quality=75" />
                </view>
                <view class="i-price">
                  <text>￥3.2</text>
                  <text>￥5.0</text>
                </view>
              </view>
            </view>
          </view>
        </template>
      </view>
    </weixin-wrapper>
  </view>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import weixinWrapper from '@/components/weixin-wrapper'
import { checkToken, pullDownRefresh, pageLoad } from "@/composables/page";
import { STATIC_PATH } from '@/config'
import { $toast, go, ready } from 'codingtalk-uni-toolkit';

const { queryInfo, getVal, resetInfo } = pageLoad('index', {}, null, { autoLoad: false })

pullDownRefresh((ok) => {
  queryInfo().then(() => {
    ok()
  })
})
ready().then(() => {
  queryInfo()
})
</script>


<style lang="less">
@import url("../../assets/style/var.less");
@import url("../../assets/style/mixins.less");
@import url("./__style__/index.less");
</style>
