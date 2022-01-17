<template>
  <view class="page cart">
    <template v-if="cartSortedByShop.length">
      <view class="c-header">
        <view class="h-left"></view>
        <view class="h-right" @click="isEdit = !isEdit">
          <text v-if="isEdit">完成</text>
          <text v-else>编辑</text>
        </view>
      </view>
      <view class="c-main">
        <view class="m-group" :key="key" v-for="(item, key) in cartSortedByShop">
          <view class="g-header">
            <view class="h-left" @tap="selected.fn.toggleShop(item)">
              <text class="iconfont" v-if="_isShopSelected(item)">&#xe640;</text>
              <text class="iconfont" v-else>&#xe651;</text>
              <text class="l-text">{{item.shop.name}}</text>
            </view>
            <view class="h-right">
              <text class="iconfont">&#xe619;</text>
              <text>满150.00免运费</text>
            </view>
          </view>
          <view class="g-content">
            <view class="c-item border-1px-top" :key="key1" v-for="(item1, key1) in item.cartList">
              <view class="i-select" @tap="selected.fn.toggleCart(item1)">
                <text
                  class="iconfont"
                  v-if="selected.attr.activeCartIds.includes(item1.id)"
                >&#xe640;</text>
                <text class="iconfont" v-else>&#xe651;</text>
              </view>
              <view class="i-pic">
                <image
                  src="http://yanxuan.nosdn.127.net/e216153c4c420a6bfaa754e6220981cd.png?imageView&quality=65&thumbnail=330x330"
                />
              </view>
              <view class="i-info">
                <view class="info-name">
                  <text>{{$_.get(item1, 'goodsItems.goods.name')}}</text>
                </view>
                <view class="i-price-num">
                  <view class="price">
                    <text>￥{{$_.get(item1, 'goodsItems.amount') * item1.quantity | amountFormat}}</text>
                  </view>
                  <view class="num">
                    <counter></counter>
                  </view>
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
      <view class="c-footer">
        <view class="f-left" @tap="selected.fn.toggleAll">
          <text
            class="iconfont"
            v-if="isSameArr(selected.attr.activeCartIds, this.$_.get(PAGE_INFO, 'cartList', []).map(i => i.id))"
          >&#xe640;</text>
          <text class="iconfont" v-else>&#xe651;</text>
          <text>全选</text>
        </view>
        <view class="f-right" v-if="isEdit">
          <view class="r-info">
            <text>已选：</text>
            <text>{{1}}件</text>
          </view>
          <view class="r-action" @tap="doDelete">
            <text>删除</text>
          </view>
        </view>
        <view class="f-right" v-else>
          <view class="r-info">
            <text class="i-text">合计：</text>
            <text class="i-price">￥{{selectStat.amount | amountFormat}}</text>
          </view>
          <view class="r-action" @tap="addOrderForm">
            <text>去结算({{selectStat.total}})</text>
          </view>
        </view>
      </view>
    </template>
    <view class="c-main" v-else>
      <view class="m-blank">
        <view class="no-data">
          <image src="@/static/image/blank-cart.png" />
        </view>
        <view class="to-buy" v-if="isLogin">
          <text>购物车空空如也，去</text>
          <text class="btn" @tap="swi('/pages/index/index')">购买</text>
          <text>几件吧</text>
        </view>
        <view class="to-login" v-else>
          <text>您尚未登录</text>
          <text class="btn" @tap="nav('/pages/login/index')">去登陆</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import counter from '@/components/counter';

import mixinPageInfo from '@/mixins/type/pageInfo';
import { mapState } from 'vuex';
import { model } from 'model';

const { OrderForm } = model.collection;
export default {
  components: {
    counter
  },
  mixins: [mixinPageInfo],
  onPullDownRefresh () {
    this._boot().then(res => {
      uni.stopPullDownRefresh();
    });
  },
  data () {
    return {
      PAGE_CONFIG: {
        key: 'cart'
      },
      isEdit: false,
      selected: {
        attr: {
          activeCartIds: []
        },
        fn: {
          toggleAll: () => {
            let activeCartIds = this.selected.attr.activeCartIds;
            if (this.isSameArr(activeCartIds, this.$_.get(this.PAGE_INFO, 'cartList', []).map(i => i.id))) {
              activeCartIds = [];
            } else {
              activeCartIds = this.$_.get(this.PAGE_INFO, 'cartList', []).map(i => i.id);
            }
          },
          toggleShop: (o) => {
            const { cartList } = o;
            let activeCartIds = this.selected.attr.activeCartIds;
            if (this._isShopSelected(o)) {
              activeCartIds = activeCartIds.filter(i => { return !cartList.map(j => j.id).includes(i) });
            } else {
              cartList.forEach(i => {
                if (!activeCartIds.includes(i.id)) {
                  activeCartIds.push(i.id);
                }
              });
            }
          },
          toggleCart: (o) => {
            let activeCartIds = this.selected.attr.activeCartIds;
            if (!activeCartIds.includes(o.id)) {
              activeCartIds.push(o.id);
            } else {
              activeCartIds = activeCartIds.filter(i => { return i !== o.id });
            }
          },
          doRemove: () => {

          }
        }
      }
    }
  },
  computed: {
    ...mapState(['isLogin']),
    cartSortedByShop () {
      let l = [];
      this.$_.get(this.PAGE_INFO, 'cartList', []).forEach(i => {
        const shop = this.$_.cloneDeep(i.shop);
        delete i.shop;
        const tar = this.$_.find(l, x => { return x.shop.id === shop.id });
        if (tar) {
          tar.cartList.push(i);
          return;
        }
        l.push({
          shop: shop,
          cartList: [i]
        });
      });
      return l;
    },
    selectStat () {
      let total = 0;
      let amount = 0;
      this.$_.get(this.PAGE_INFO, 'cartList', []).forEach(i => {
        if (this.selected.attr.activeCartIds.includes(i.id)) {
          total += this.$_.get(i, 'quantity');
          amount += this.$_.get(i, 'goodsItems.goods.amount', 0) * this.$_.get(i, 'quantity');
        }
      });
      return {
        total,
        amount
      }
    }
  },
  methods: {
    _isShopSelected (o) {
      const { cartList } = o;
      return this.isSameArr(this.selected.attr.activeCartIds, cartList.map(i => i.id));
    },
    addOrderForm () {
      const activeCartIds = this.selected.attr.activeCartIds;
      if (!activeCartIds.length) {
        this.toast('请选择购买商品规格');
        return;
      }
      OrderForm.sendApi('add', { params: { type: 'cart' }, data: { cartIds: activeCartIds.join(',') } }, { errorTip: '创建订单失败', parse4Entity: true }).then(res => {
        const { status, data } = res;
        if (status) {
          const { id } = data;
          this.nav(`/subpackage/order-form/pages/cashier/settle?orderFormId=${id}`);
        }
      });
    }
  }
}
</script>

<style lang="less">
@import "./index";
</style>
