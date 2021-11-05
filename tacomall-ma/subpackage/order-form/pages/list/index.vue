<template>
  <view class="page--column order-form_list">
    <view class="l-header border-1px-bottom">
      <view
        class="h-item"
        :class="{'h-item-active': page.form.query.type === item.value}"
        :key="key"
        @tap="page.form.query.type = item.value"
        v-for="(item, key) in enumOrderFormStatus"
      >
        <text>{{item.label}}</text>
        <text class="i-tag">{{0}}</text>
      </view>
    </view>
    <view class="l-main">
      <list-page :config="page.config" :form="page.form">
        <template v-slot="{listData}">
          <view class="m-receive">
            <view class="r-item" :key="key" v-for="(item, key) in listData">
              <view class="i-time-status">
                <view class="c-time">
                  <text>2018-08-06 16:57</text>
                </view>
                <view class="c-status">
                  <text>已支付支付</text>
                </view>
              </view>
              <view class="i-goods border-1px-top">
                <view class="g-item" :key="key1" v-for="(item1, key1) in []">
                  <view class="i-pic">
                    <image
                      src="https://static.home.mi.com/app/shop/img?id=shop_f4e9e198a07c9888cd3a4295a0f54b0b.jpeg&w=240&h=240"
                    />
                  </view>
                  <view class="i-info">
                    <view class="i-name">
                      <text>{{item1.goodsItem.name}}</text>
                    </view>
                  </view>
                  <view class="i-price-num">
                    <text class="single-price">￥1</text>
                    <text class="num">x{{item1.quantity}}</text>
                  </view>
                </view>
              </view>
              <view class="i-price">
                <view class="p-total">
                  <text>共1件商品，总金额:</text>
                  <text>￥0</text>
                </view>
                <view class="p-transfer">
                  <text>（含运费￥10）</text>
                </view>
              </view>
              <view class="i-action border-1px-top">
                <view class="expire-time">
                  <text>24小时候订单将关闭</text>
                </view>
                <view class="action-btns">
                  <text>取消</text>
                  <text>去支付</text>
                </view>
              </view>
            </view>
          </view>
        </template>
      </list-page>
    </view>
  </view>
</template>

<script>
import listPage from '@/components/list-page';
import { model } from '@/model';

const { OrderForm } = model.collection;

export default {
  components: {
    listPage
  },
  data () {
    return {
      enumOrderFormStatus: OrderForm.options.status,
      page: {
        config: {
          entity: 'OrderForm',
          action: 'page'
        },
        form: {
          query: {
            type: '',
          }
        }
      }
    }
  },
  onLoad () {
  },
  methods: {
  }
}
</script>

<style lang="less">
@import "./index";
</style>
