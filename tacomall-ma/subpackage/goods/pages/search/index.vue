<template>
  <view class="page--column search">
    <view class="s-header">
      <view class="h-top">
        <view class="t-search">
          <text class="iconfont">&#xe652;</text>
          <input
            type="search"
            placeholder="小米超级品牌日"
            v-model="page.form.query.keyword"
            @focus="step = 2"
          />
          <text class="iconfont clear" @click="clearSearch()">&#xe61a;</text>
        </view>
        <view class="t-right">
          <text>搜索</text>
        </view>
      </view>
      <view class="h-bottom" v-if="step === 3">
        <view class="b-item b-item-active">
          <view class="i-text">
            <text>综合</text>
          </view>
          <view class="i-icon i-icon-down">
            <text class="iconfont">&#xe734;</text>
            <text class="iconfont">&#xe6e9;</text>
          </view>
        </view>
        <view class="b-item">
          <text>价格</text>
        </view>
        <view class="b-item">
          <text>品牌</text>
        </view>
        <view class="b-item">
          <text>分类</text>
        </view>
      </view>
    </view>
    <view class="s-main">
      <view class="m-history" v-if="step === 1">
        <view class="__m-section">
          <view class="sec-header">
            <view class="h-left">
              <text>热门搜索</text>
            </view>
            <view class="h-right"></view>
          </view>
          <view class="sec-content">
            <view class="h-hot">
              <text
                :key="key"
                @tap="selectKeyword(item)"
                v-for="(item, key) in $_.get(PAGE_INFO, 'keyword', [])"
              >{{item}}</text>
            </view>
          </view>
        </view>
        <view class="__m-section" v-if="history.attr.list.length">
          <view class="sec-header">
            <view class="h-left">
              <text>历史纪录</text>
            </view>
            <view class="h-right">
              <text class="iconfont" @click="history.fn.clear()">&#xe6d1;</text>
            </view>
          </view>
          <view class="sec-content">
            <view class="h-history">
              <text
                :key="key"
                @tap="selectKeyword(item)"
                v-for="(item, key) in history.attr.list"
              >{{item}}</text>
            </view>
          </view>
        </view>
      </view>
      <view class="m-favour" v-else-if="step === 2">
        <view class="__m-section">
          <view class="sec-header">
            <view class="h-left">
              <text>热门</text>
            </view>
          </view>
          <view class="sec-content" style="padding:0.37333rem;">
            <view class="f-recommend">
              <view class="r-item" :key="key" v-for="(item, key) in $_.get(PAGE_INFO, 'hot', [])">
                <view class="i-pic">
                  <img-loader :src="item.cover" />
                </view>
                <view class="i-info">
                  <text class="i-name">{{item.name}}</text>
                  <text class="i-price">￥2499.00</text>
                </view>
                <view class="i-buy">
                  <text>选购</text>
                  <text class="iconfont">&#xe6af;</text>
                </view>
              </view>
            </view>
          </view>
        </view>
        <view class="__m-section">
          <view class="sec-header">
            <view class="h-left">
              <text>大家都在看</text>
            </view>
          </view>
          <view class="sec-content" style="padding:0.37333rem;">
            <view class="f-hot">
              <view
                class="h-item"
                :key="key"
                v-for="(item, key) in $_.get(PAGE_INFO, 'favour', [])"
              >
                <view class="i-left">
                  <text>{{item.name}}</text>
                </view>
                <view class="i-right">
                  <text>￥1999.00</text>
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
      <view class="m-list" v-else>
        <list-page :config="page.config" :form="page.form">
          <template v-slot="{listData}">
            <view class="l-wrap">
              <view class="w-item" :key="key" v-for="(item, key) in listData">
                <goods-card
                  :goods="item"
                  @selected="nav(`/subpackage/goods/pages/detail/index?id=${$event.id}&ac=search`)"
                ></goods-card>
              </view>
            </view>
          </template>
        </list-page>
      </view>
    </view>
  </view>
</template>

<script>
import goodsCard from '@/components/goods-card';
import listPage from '@/components/list-page';
import { historySearch } from '@/utils/history-search';
import imgLoader from '@/components/img-loader';

import mixinPageInfo from '@/mixins/type/pageInfo';

export default {
  components: {
    goodsCard,
    listPage,
    imgLoader
  },
  mixins: [mixinPageInfo],
  data () {
    return {
      step: 1,
      PAGE_CONFIG: {
        key: 'search'
      },
      page: {
        config: {
          entity: 'Goods',
          action: 'page'
        },
        form: {
          query: {
            keyword: ''
          }
        }
      },
      history: {
        attr: {
          list: []
        },
        fn: {
          clear: () => {
            uni.showModal({
              title: "确认删除全部历史记录?",
              success: (res) => {
                if (res.confirm) {
                  historySearch.clear();
                  this.history.attr.list = []
                }
              }
            });
          }
        }
      }
    }
  },
  watch: {
    "page.form.query.keyword": function (e) {
      if (e) {
        this.step = 3;
      }
    }
  },
  onShow () {
    this.init();
  },
  methods: {
    init () {
      this.history.attr.list = historySearch.get();
    },
    selectKeyword (s) {
      this.page.form.query.keyword = s
    },
    clearSearch () {
      this.step = 1;
      this.page.form.query.keyword = '';
    }
  }
}
</script>

<style lang="less">
@import "./index";
</style>
