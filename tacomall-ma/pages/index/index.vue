<template>
  <view class="page home">
    <view class="i-header">
      <view class="h-top">
        <view class="t-logo">
          <image src="@/static/image/logo-text.png" />
        </view>
        <view class="t-search" @tap="nav('/subpackage/goods/pages/search/index')">
          <view class="s-left">
            <text class="iconfont">&#xe652;</text>
            <text class="l-text">搜索好品，共有16523件商品</text>
          </view>
        </view>
      </view>
      <view class="h-bottom">
        <scroll-view>
          <view class="b-tabs">
            <view class="t-item" :key="key" v-for="(item, key) in sectionLinks">
              <text>{{item.n}}</text>
            </view>
          </view>
        </scroll-view>
      </view>
    </view>
    <view class="i-banner">
      <swiper
        class="b-swiper"
        :indicator-dots="true"
        :autoplay="true"
        interval="2000"
        duration="3000"
      >
        <swiper-item :key="key" v-for="(item, key) in $_.get(this.PAGE_INFO, 'maCarouselList', [])">
          <view class="s-item">
            <image :src="item.cover" />
          </view>
        </swiper-item>
      </swiper>
    </view>
    <view class="i-service">
      <view class="s-item">
        <text class="iconfont">&#xe639;</text>
        <text class="i-text">网易自营品牌</text>
      </view>
      <view class="s-item">
        <text class="iconfont">&#xe7c4;</text>
        <text class="i-text">30天无忧退换</text>
      </view>
      <view class="s-item">
        <text class="iconfont">&#xe67c;</text>
        <text class="i-text">48小时快速退款</text>
      </view>
    </view>
    <view class="i-category">
      <view
        class="c-item"
        :key="key"
        v-for="(item, key) in $_.get(this.PAGE_INFO, 'goodsCategoryList', [])"
      >
        <image src="http://yanxuan.nosdn.127.net/fede8b110c502ec5799702d5ec824792.png" />
        <text>{{item.name}}</text>
      </view>
    </view>
    <view class="i-flash">
      <view class="f-header">
        <div class="h-left">
          <view class="l-text">
            <text>限时购</text>
          </view>
          <view class="l-time">
            <view class="t-item">
              <text>{{seckill.h | addZero}}</text>
            </view>
            <view class="t-item">
              <text>{{seckill.m | addZero}}</text>
            </view>
            <view class="t-item">
              <text>{{seckill.s | addZero}}</text>
            </view>
          </view>
        </div>
        <div class="h-right" @tap="nav('/pages/seckill/index')">
          <text>更多</text>
          <text class="iconfont">&#xe93d;</text>
        </div>
      </view>
      <view class="f-content">
        <view
          class="c-item"
          :key="key"
          @tap="nav(`/subpackage/goods/pages/detail/index?id=${item.id}&ac=seckill`)"
          v-for="(item, key) in seckill.l"
        >
          <view class="i-image">
            <image
              src="http://yanxuan.nosdn.127.net/598a7792fdef09260c6c6fb0ca4fa5cc.png?imageView&thumbnail=216x216&quality=75"
            />
          </view>
          <view class="i-price">
            <text>￥{{item.amount}}</text>
            <text>￥5.0</text>
          </view>
        </view>
      </view>
    </view>
    <template v-for="(item, key) in $_.get(this.PAGE_INFO, 'sections', [])">
      <view class="i-direct-supply" :key="key" v-if="item.k == 'HOT'">
        <view class="s-header">
          <div class="h-left">
            <text>{{item.name}}</text>
          </div>
          <div class="h-right" @tap="nav('/pages/category-goods/index')">
            <text>更多</text>
            <text class="iconfont">&#xe93d;</text>
          </div>
        </view>
        <view class="s-content">
          <view class="c-item" :key="key" v-for="(item, key) in item.g">
            <view class="i-desc">
              <text>海外制造商</text>
              <text>24元起</text>
            </view>
            <image
              src="http://yanxuan.nosdn.127.net/74e2ea8f81004d0a60f90fc8e4649058.png?imageView&thumbnail=343y260&enlarge=1"
            />
          </view>
        </view>
      </view>
      <view class="i-newest" :key="key" v-if="item.k == 'HUAN_JI'">
        <view class="n-header">
          <div class="h-left">
            <text>{{item.name}}</text>
          </div>
          <div class="h-right" @tap="nav('/pages/category-goods/index')">
            <text>更多</text>
            <text class="iconfont">&#xe93d;</text>
          </div>
        </view>
        <view class="n-content">
          <view class="c-item" :key="key" v-for="(item, key) in item.g">
            <view class="i-image">
              <image
                src="http://yanxuan.nosdn.127.net/598a7792fdef09260c6c6fb0ca4fa5cc.png?imageView&thumbnail=216x216&quality=75"
              />
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
</template>

<script>
import imgLoader from '@/components/img-loader';

import { model } from '@/model';
import mixinPageInfo from '@/mixins/type/pageInfo';
import { mapState } from 'vuex';
const { Goods } = model.collection;

export default {
  components: {
    imgLoader
  },
  mixins: [mixinPageInfo],
  data () {
    return {
      PAGE_CONFIG: {
        key: 'index',
        callback: () => {
          Goods.sendApi('page', { params: { pageIndex: 1, pageSize: 6 }, data: { query: { isInSeckill: 1 } } }, { errorTip: '获取秒杀商品失败' }).then(res => {
            const { status, data } = res;
            if (status) {
              this.seckill.l = [...data];
            }
          });
        }
      },
      seckill: {
        l: [],
        h: 0,
        m: 0,
        s: 0
      }
    }
  },
  computed: {
    ...mapState(['now']),
    sectionLinks () {
      return this.$_.get(this.PAGE_INFO, 'sections', []).map(i => {
        return {
          n: i.n,
          k: i.k
        }
      })
    }
  },
  watch: {
    'now': {
      handler: function (e) {
        const l = this.getLeftTime(e);
        this.seckill = Object.assign(this.seckill, l);
      },
      deep: true
    }
  },
  methods: {
  }
}
</script>

<style lang="less">
@import "./index";
</style>
