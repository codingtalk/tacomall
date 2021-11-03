<template>
<uni-shadow-root class="vant-goods-action-index"><view :class="'custom-class '+(utils.bem('goods-action', { safe: safeAreaInsetBottom }))">
  <slot></slot>
</view></uni-shadow-root>
</template>
<wxs src="../wxs/utils.wxs" module="utils"></wxs>
<script>

global['__wxRoute'] = 'vant/goods-action/index'
import { VantComponent } from '../common/component';
VantComponent({
  relation: {
    type: 'descendant',
    name: 'goods-action-button',
    current: 'goods-action',
    linked() {
      this.updateStyle();
    },
    unlinked() {
      this.updateStyle();
    },
    linkChanged() {
      this.updateStyle();
    },
  },
  props: {
    safeAreaInsetBottom: {
      type: Boolean,
      value: true,
    },
  },
  methods: {
    updateStyle() {
      wx.nextTick(() => {
        this.children.forEach((child) => {
          child.updateStyle();
        });
      });
    },
  },
});
export default global['__wxComponents']['vant/goods-action/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-goods-action{position:fixed;right:0;bottom:0;left:0;display:-webkit-flex;display:flex;-webkit-align-items:center;align-items:center;height:50px;height:var(--goods-action-height,50px);background-color:#fff;background-color:var(--goods-action-background-color,#fff)}.van-goods-action--safe{padding-bottom:env(safe-area-inset-bottom)}
</style>