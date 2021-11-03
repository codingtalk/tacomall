<template>
<uni-shadow-root class="vant-sidebar-index"><view class="van-sidebar custom-class">
  <slot></slot>
</view></uni-shadow-root>
</template>

<script>

global['__wxRoute'] = 'vant/sidebar/index'
import { VantComponent } from '../common/component';
VantComponent({
  relation: {
    name: 'sidebar-item',
    type: 'descendant',
    current: 'sidebar',
    linked() {
      this.setActive(this.data.activeKey);
    },
    unlinked() {
      this.setActive(this.data.activeKey);
    },
  },
  props: {
    activeKey: {
      type: Number,
      value: 0,
      observer: 'setActive',
    },
  },
  beforeCreate() {
    this.currentActive = -1;
  },
  methods: {
    setActive(activeKey) {
      const { children, currentActive } = this;
      if (!children.length) {
        return Promise.resolve();
      }
      this.currentActive = activeKey;
      const stack = [];
      if (currentActive !== activeKey && children[currentActive]) {
        stack.push(children[currentActive].setActive(false));
      }
      if (children[activeKey]) {
        stack.push(children[activeKey].setActive(true));
      }
      return Promise.all(stack);
    },
  },
});
export default global['__wxComponents']['vant/sidebar/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-sidebar{width:85px;width:var(--sidebar-width,85px)}
</style>