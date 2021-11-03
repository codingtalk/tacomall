<template>
<uni-shadow-root class="vant-tabbar-index"><view :class="'custom-class '+(border ? 'van-hairline--top-bottom' : '')+' '+(utils.bem('tabbar', { fixed, safe: safeAreaInsetBottom }))" :style="zIndex ? 'z-index: ' + zIndex : ''">
  <slot></slot>
</view></uni-shadow-root>
</template>
<wxs src="../wxs/utils.wxs" module="utils"></wxs>
<script>

global['__wxRoute'] = 'vant/tabbar/index'
import { VantComponent } from '../common/component';
VantComponent({
  relation: {
    name: 'tabbar-item',
    type: 'descendant',
    current: 'tabbar',
    linked(target) {
      target.parent = this;
      target.updateFromParent();
    },
    unlinked() {
      this.updateChildren();
    },
  },
  props: {
    active: {
      type: null,
      observer: 'updateChildren',
    },
    activeColor: {
      type: String,
      observer: 'updateChildren',
    },
    inactiveColor: {
      type: String,
      observer: 'updateChildren',
    },
    fixed: {
      type: Boolean,
      value: true,
    },
    border: {
      type: Boolean,
      value: true,
    },
    zIndex: {
      type: Number,
      value: 1,
    },
    safeAreaInsetBottom: {
      type: Boolean,
      value: true,
    },
  },
  methods: {
    updateChildren() {
      const { children } = this;
      if (!Array.isArray(children) || !children.length) {
        return Promise.resolve();
      }
      return Promise.all(children.map((child) => child.updateFromParent()));
    },
    onChange(child) {
      const index = this.children.indexOf(child);
      const active = child.data.name || index;
      if (active !== this.data.active) {
        this.$emit('change', active);
      }
    },
  },
});
export default global['__wxComponents']['vant/tabbar/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-tabbar{display:-webkit-flex;display:flex;box-sizing:initial;width:100%;height:50px;height:var(--tabbar-height,50px);background-color:#fff;background-color:var(--tabbar-background-color,#fff)}.van-tabbar--fixed{position:fixed;bottom:0;left:0}.van-tabbar--safe{padding-bottom:env(safe-area-inset-bottom)}
</style>