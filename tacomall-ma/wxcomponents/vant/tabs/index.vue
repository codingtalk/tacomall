<template>
<uni-shadow-root class="vant-tabs-index"><view :class="'custom-class '+(utils.bem('tabs', [type]))">
  <van-sticky :disabled="(!sticky)" :z-index="zIndex" :offset-top="offsetTop" :container="container" @scroll="onTouchScroll">
    <view :class="(utils.bem('tabs__wrap', { scrollable }))+' '+(type === 'line' && border ? 'van-hairline--top-bottom' : '')">
      <slot name="nav-left"></slot>

      <scroll-view :scroll-x="scrollable" scroll-with-animation :scroll-left="scrollLeft" :class="utils.bem('tabs__scroll', [type])" :style="color ? 'border-color: ' + color : ''">
        <view :class="(utils.bem('tabs__nav', [type, { complete: !ellipsis }]))+' nav-class'" :style="getters.tabCardTypeBorderStyle(color, type)">
          <view v-if="type === 'line'" class="van-tabs__line" :style="getters.lineStyle({ color, lineOffsetLeft, lineHeight, skipTransition, duration, lineWidth })"></view>
          <view v-for="(item,index) in (tabs)" :key="item.index" :data-index="index" :class="(getters.tabClass(index === currentIndex, ellipsis))+' '+(utils.bem('tab', { active: index === currentIndex, disabled: item.disabled, complete: !ellipsis }))" :style="getters.tabStyle(index === currentIndex, ellipsis, color, type, item.disabled, titleActiveColor, titleInactiveColor, swipeThreshold, scrollable)" @click="onTap">
            <view :class="ellipsis ? 'van-ellipsis' : ''" :style="item.titleStyle">
              {{ item.title }}
              <van-info v-if="item.info !== null || item.dot" :info="item.info" :dot="item.dot" custom-class="van-tab__title__info"></van-info>
            </view>
          </view>
        </view>
      </scroll-view>

      <slot name="nav-right"></slot>
    </view>
  </van-sticky>

  <view class="van-tabs__content" @touchstart="onTouchStart" @touchmove="onTouchMove" @touchend="onTouchEnd" @touchcancel="onTouchEnd">
    <view :class="(utils.bem('tabs__track', [{ animated }]))+' van-tabs__track'" :style="getters.trackStyle({ duration, currentIndex, animated })">
      <slot></slot>
    </view>
  </view>
</view></uni-shadow-root>
</template>
<wxs src="../wxs/utils.wxs" module="utils"></wxs><wxs src="./index.wxs" module="getters"></wxs>
<script>
import VanInfo from '../info/index.vue'
import VanSticky from '../sticky/index.vue'
global['__wxVueOptions'] = {components:{'van-info': VanInfo,'van-sticky': VanSticky}}

global['__wxRoute'] = 'vant/tabs/index'
import { VantComponent } from '../common/component';
import { touch } from '../mixins/touch';
import { getAllRect, getRect, isDef } from '../common/utils';
VantComponent({
  mixins: [touch],
  classes: ['nav-class', 'tab-class', 'tab-active-class', 'line-class'],
  relation: {
    name: 'tab',
    type: 'descendant',
    current: 'tabs',
    linked(target) {
      target.index = this.children.length - 1;
      this.updateTabs();
    },
    unlinked() {
      this.children = this.children.map((child, index) => {
        child.index = index;
        return child;
      });
      this.updateTabs();
    },
  },
  props: {
    sticky: Boolean,
    border: Boolean,
    swipeable: Boolean,
    titleActiveColor: String,
    titleInactiveColor: String,
    color: String,
    animated: {
      type: Boolean,
      observer() {
        this.children.forEach((child, index) =>
          child.updateRender(index === this.data.currentIndex, this)
        );
      },
    },
    lineWidth: {
      type: [String, Number],
      value: 40,
      observer: 'setLine',
    },
    lineHeight: {
      type: [String, Number],
      value: -1,
    },
    active: {
      type: [String, Number],
      value: 0,
      observer(name) {
        if (name !== this.getCurrentName()) {
          this.setCurrentIndexByName(name);
        }
      },
    },
    type: {
      type: String,
      value: 'line',
    },
    ellipsis: {
      type: Boolean,
      value: true,
    },
    duration: {
      type: Number,
      value: 0.3,
    },
    zIndex: {
      type: Number,
      value: 1,
    },
    swipeThreshold: {
      type: Number,
      value: 5,
      observer(value) {
        this.setData({
          scrollable: this.children.length > value || !this.data.ellipsis,
        });
      },
    },
    offsetTop: {
      type: Number,
      value: 0,
    },
    lazyRender: {
      type: Boolean,
      value: true,
    },
  },
  data: {
    tabs: [],
    lineStyle: '',
    scrollLeft: 0,
    scrollable: false,
    trackStyle: '',
    currentIndex: 0,
    container: null,
    skipTransition: true,
    lineOffsetLeft: 0,
  },
  mounted() {
    wx.nextTick(() => {
      this.setLine(true);
      this.scrollIntoView();
    });
  },
  methods: {
    updateContainer() {
      this.setData({
        container: () => this.createSelectorQuery().select('.van-tabs'),
      });
    },
    updateTabs() {
      const { children = [], data } = this;
      this.setData({
        tabs: children.map((child) => child.data),
        scrollable:
          this.children.length > data.swipeThreshold || !data.ellipsis,
      });
      this.setCurrentIndexByName(this.getCurrentName() || data.active);
    },
    trigger(eventName, child) {
      const { currentIndex } = this.data;
      const currentChild = child || this.children[currentIndex];
      if (!isDef(currentChild)) {
        return;
      }
      this.$emit(eventName, {
        index: currentChild.index,
        name: currentChild.getComputedName(),
        title: currentChild.data.title,
      });
    },
    onTap(event) {
      const { index } = event.currentTarget.dataset;
      const child = this.children[index];
      if (child.data.disabled) {
        this.trigger('disabled', child);
      } else {
        this.setCurrentIndex(index);
        wx.nextTick(() => {
          this.trigger('click');
        });
      }
    },
    // correct the index of active tab
    setCurrentIndexByName(name) {
      const { children = [] } = this;
      const matched = children.filter(
        (child) => child.getComputedName() === name
      );
      if (matched.length) {
        this.setCurrentIndex(matched[0].index);
      }
    },
    setCurrentIndex(currentIndex) {
      const { data, children = [] } = this;
      if (
        !isDef(currentIndex) ||
        currentIndex >= children.length ||
        currentIndex < 0
      ) {
        return;
      }
      children.forEach((item, index) => {
        const active = index === currentIndex;
        if (active !== item.data.active || !item.inited) {
          item.updateRender(active, this);
        }
      });
      if (currentIndex === data.currentIndex) {
        return;
      }
      const shouldEmitChange = data.currentIndex !== null;
      this.setData({ currentIndex });
      wx.nextTick(() => {
        this.setLine();
        this.scrollIntoView();
        this.updateContainer();
        this.trigger('input');
        if (shouldEmitChange) {
          this.trigger('change');
        }
      });
    },
    getCurrentName() {
      const activeTab = this.children[this.data.currentIndex];
      if (activeTab) {
        return activeTab.getComputedName();
      }
    },
    setLine(skipTransition = false) {
      if (this.data.type !== 'line') {
        return;
      }
      const { currentIndex } = this.data;
      Promise.all([
        getAllRect.call(this, '.van-tab'),
        getRect.call(this, '.van-tabs__line'),
      ]).then(([rects = [], lineRect]) => {
        const rect = rects[currentIndex];
        if (rect == null) {
          return;
        }
        let lineOffsetLeft = rects
          .slice(0, currentIndex)
          .reduce((prev, curr) => prev + curr.width, 0);
        lineOffsetLeft += (rect.width - lineRect.width) / 2;
        this.setData({
          lineOffsetLeft,
          skipTransition,
        });
      });
    },
    // scroll active tab into view
    scrollIntoView() {
      const { currentIndex, scrollable } = this.data;
      if (!scrollable) {
        return;
      }
      Promise.all([
        getAllRect.call(this, '.van-tab'),
        getRect.call(this, '.van-tabs__nav'),
      ]).then(([tabRects, navRect]) => {
        const tabRect = tabRects[currentIndex];
        const offsetLeft = tabRects
          .slice(0, currentIndex)
          .reduce((prev, curr) => prev + curr.width, 0);
        this.setData({
          scrollLeft: offsetLeft - (navRect.width - tabRect.width) / 2,
        });
      });
    },
    onTouchScroll(event) {
      this.$emit('scroll', event.detail);
    },
    onTouchStart(event) {
      if (!this.data.swipeable) return;
      this.touchStart(event);
    },
    onTouchMove(event) {
      if (!this.data.swipeable) return;
      this.touchMove(event);
    },
    // watch swipe touch end
    onTouchEnd() {
      if (!this.data.swipeable) return;
      const { direction, deltaX, offsetX } = this;
      const minSwipeDistance = 50;
      if (direction === 'horizontal' && offsetX >= minSwipeDistance) {
        const index = this.getAvaiableTab(deltaX);
        if (index !== -1) {
          this.setCurrentIndex(index);
        }
      }
    },
    getAvaiableTab(direction) {
      const { tabs, currentIndex } = this.data;
      const step = direction > 0 ? -1 : 1;
      for (
        let i = step;
        currentIndex + i < tabs.length && currentIndex + i >= 0;
        i += step
      ) {
        const index = currentIndex + i;
        if (
          index >= 0 &&
          index < tabs.length &&
          tabs[index] &&
          !tabs[index].disabled
        ) {
          return index;
        }
      }
      return -1;
    },
  },
});
export default global['__wxComponents']['vant/tabs/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-tabs{position:relative;-webkit-tap-highlight-color:transparent}.van-tabs__wrap{display:-webkit-flex;display:flex;overflow:hidden}.van-tabs__wrap--scrollable .van-tab{-webkit-flex:0 0 22%;flex:0 0 22%}.van-tabs__wrap--scrollable .van-tab--complete{-webkit-flex:1 0 auto!important;flex:1 0 auto!important;padding:0 12px}.van-tabs__wrap--scrollable .van-tabs__nav--complete{padding-right:8px;padding-left:8px}.van-tabs__scroll{background-color:#fff;background-color:var(--tabs-nav-background-color,#fff)}.van-tabs__scroll--line{box-sizing:initial;height:calc(100% + 15px)}.van-tabs__scroll--card{margin:0 16px;margin:0 var(--padding-md,16px)}.van-tabs__scroll::-webkit-scrollbar{display:none}.van-tabs__nav{position:relative;display:-webkit-flex;display:flex;-webkit-user-select:none;user-select:none}.van-tabs__nav--card{box-sizing:border-box;height:30px;height:var(--tabs-card-height,30px);border:1px solid #ee0a24;border:var(--border-width-base,1px) solid var(--tabs-default-color,#ee0a24);border-radius:2px;border-radius:var(--border-radius-sm,2px)}.van-tabs__nav--card .van-tab{color:#ee0a24;color:var(--tabs-default-color,#ee0a24);line-height:28px;line-height:calc(var(--tabs-card-height, 30px) - var(--border-width-base, 1px)*2);border-right:1px solid #ee0a24;border-right:var(--border-width-base,1px) solid var(--tabs-default-color,#ee0a24)}.van-tabs__nav--card .van-tab:last-child{border-right:none}.van-tabs__nav--card .van-tab.van-tab--active{color:#fff;color:var(--white,#fff);background-color:#ee0a24;background-color:var(--tabs-default-color,#ee0a24)}.van-tabs__nav--card .van-tab--disabled{color:#c8c9cc;color:var(--tab-disabled-text-color,#c8c9cc)}.van-tabs__line{position:absolute;bottom:0;left:0;z-index:1;height:3px;height:var(--tabs-bottom-bar-height,3px);border-radius:3px;border-radius:var(--tabs-bottom-bar-height,3px);background-color:#ee0a24;background-color:var(--tabs-bottom-bar-color,#ee0a24)}.van-tabs__track{position:relative;width:100%;height:100%}.van-tabs__track--animated{display:-webkit-flex;display:flex;transition-property:left}.van-tabs__content{overflow:hidden}.van-tabs--line .van-tabs__wrap{height:44px;height:var(--tabs-line-height,44px)}.van-tabs--card .van-tabs__wrap{height:30px;height:var(--tabs-card-height,30px)}.van-tab{position:relative;-webkit-flex:1;flex:1;box-sizing:border-box;min-width:0;padding:0 5px;text-align:center;cursor:pointer;color:#646566;color:var(--tab-text-color,#646566);font-size:14px;font-size:var(--tab-font-size,14px);line-height:44px;line-height:var(--tabs-line-height,44px)}.van-tab--active{font-weight:500;font-weight:var(--font-weight-bold,500);color:#323233;color:var(--tab-active-text-color,#323233)}.van-tab--disabled{color:#c8c9cc;color:var(--tab-disabled-text-color,#c8c9cc)}.van-tab__title__info{position:relative!important;top:-1px!important;display:inline-block;-webkit-transform:translateX(0)!important;transform:translateX(0)!important}
</style>