<!--
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2021-11-05 16:19:04
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/pages/category/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <view class="page category">
    <view class="c-header border-1px-bottom">
      <view class="header-search" @tap="nav('/subpackage/goods/pages/search/index')">
        <text class="iconfont">&#xe652;</text>
        <text class="keyword">润米超级品牌日</text>
      </view>
    </view>
    <view class="c-left">
      <scroll-view :scroll-y="true">
        <view
          class="l-item"
          :class="{'l-item-active': activeIndex === key}"
          :key="key"
          @tap="activeIndex = key"
          v-for="(item, key) in $_.get(PAGE_INFO, 'goodsCategoryList', [])"
        >
          <text>{{item.name}}</text>
        </view>
      </scroll-view>
    </view>
    <view class="c-right">
      <scroll-view :scroll-y="true">
        <view class="sub-list-wrap">
          <view class="wrap-banner">
            <image
              src="http://yanxuan.nosdn.127.net/d723607e0cbb0c4a11c9396612c71d9b.jpg?imageView&thumbnail=0x196&quality=75"
            />
          </view>
          <template v-for="(item, key) in rightContent">
            <view class="wrap-type" :key="`${key}-type`">
              <text>{{item.name}}</text>
            </view>
            <view class="wrap-list" :key="`${key}-list`">
              <view
                class="list-item"
                :key="key1"
                @tap="nav(`/pages/category-goods/index?id=${item1.id}&pid=${item.id}`)"
                v-for="(item1, key1) in (item.sub || [])"
              >
                <view class="item-pic">
                  <image
                    src="http://yanxuan.nosdn.127.net/cee41a60a2d4d06426f863aea2395f19.png?imageView&quality=85&thumbnail=144x144"
                  />
                </view>
                <view class="item-name">
                  <text>{{item1.name}}</text>
                </view>
              </view>
            </view>
          </template>
        </view>
      </scroll-view>
    </view>
  </view>
</template>

<script>
import mixinPageInfo from '@/mixins/type/pageInfo';

export default {
  mixins: [mixinPageInfo],
  data () {
    return {
      PAGE_CONFIG: {
        key: 'category'
      },
      activeIndex: 0
    }
  },
  computed: {
    rightContent () {
      return this.$_.get(this.PAGE_INFO, `goodsCategoryList[${this.activeIndex}].sub`, [])
    }
  },
  methods: {
  }
}
</script>

<style lang="less">
@import "./index";
</style>
