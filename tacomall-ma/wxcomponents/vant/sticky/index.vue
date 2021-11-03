<template>
<uni-shadow-root class="vant-sticky-index"><view class="custom-class van-sticky" :style="computed.containerStyle({ fixed, height, zIndex })">
  <view :class="utils.bem('sticky-wrap', { fixed })" :style="computed.wrapStyle({ fixed, offsetTop, transform, zIndex })">
    <slot></slot>
  </view>
</view></uni-shadow-root>
</template>
<wxs src="../wxs/utils.wxs" module="utils"></wxs><wxs src="./index.wxs" module="computed"></wxs>
<script>

global['__wxRoute'] = 'vant/sticky/index'
import { VantComponent } from '../common/component';
import { pageScrollMixin } from '../mixins/page-scroll';
const ROOT_ELEMENT = '.van-sticky';
VantComponent({
  props: {
    zIndex: {
      type: Number,
      value: 99,
    },
    offsetTop: {
      type: Number,
      value: 0,
      observer: 'onScroll',
    },
    disabled: {
      type: Boolean,
      observer: 'onScroll',
    },
    container: {
      type: null,
      observer: 'onScroll',
    },
    scrollTop: {
      type: null,
      observer(val) {
        this.onScroll({ scrollTop: val });
      },
    },
  },
  mixins: [
    pageScrollMixin(function (event) {
      if (this.data.scrollTop != null) {
        return;
      }
      this.onScroll(event);
    }),
  ],
  data: {
    height: 0,
    fixed: false,
    transform: 0,
  },
  mounted() {
    this.onScroll();
  },
  methods: {
    onScroll({ scrollTop } = {}) {
      const { container, offsetTop, disabled } = this.data;
      if (disabled) {
        this.setDataAfterDiff({
          fixed: false,
          transform: 0,
        });
        return;
      }
      this.scrollTop = scrollTop || this.scrollTop;
      if (typeof container === 'function') {
        Promise.all([this.getRect(ROOT_ELEMENT), this.getContainerRect()]).then(
          ([root, container]) => {
            if (offsetTop + root.height > container.height + container.top) {
              this.setDataAfterDiff({
                fixed: false,
                transform: container.height - root.height,
              });
            } else if (offsetTop >= root.top) {
              this.setDataAfterDiff({
                fixed: true,
                height: root.height,
                transform: 0,
              });
            } else {
              this.setDataAfterDiff({ fixed: false, transform: 0 });
            }
          }
        );
        return;
      }
      this.getRect(ROOT_ELEMENT).then((root) => {
        if (offsetTop >= root.top) {
          this.setDataAfterDiff({ fixed: true, height: root.height });
          this.transform = 0;
        } else {
          this.setDataAfterDiff({ fixed: false });
        }
      });
    },
    setDataAfterDiff(data) {
      wx.nextTick(() => {
        const diff = Object.keys(data).reduce((prev, key) => {
          if (data[key] !== this.data[key]) {
            prev[key] = data[key];
          }
          return prev;
        }, {});
        this.setData(diff);
        this.$emit('scroll', {
          scrollTop: this.scrollTop,
          isFixed: data.fixed || this.data.fixed,
        });
      });
    },
    getContainerRect() {
      const nodesRef = this.data.container();
      return new Promise((resolve) =>
        nodesRef.boundingClientRect(resolve).exec()
      );
    },
  },
});
export default global['__wxComponents']['vant/sticky/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-sticky{position:relative}.van-sticky-wrap--fixed{position:fixed;right:0;left:0}
</style>