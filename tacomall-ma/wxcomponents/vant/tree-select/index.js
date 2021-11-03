/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-03 15:34:13
 * @LastEditTime: 2021-08-20 13:56:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /ma-portal/wxcomponents/vant/tree-select/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { VantComponent } from '../common/component';
VantComponent({
  classes: [
    'main-item-class',
    'content-item-class',
    'main-active-class',
    'content-active-class',
    'main-disabled-class',
    'content-disabled-class',
  ],
  props: {
    items: {
      type: Array,
      observer: 'updateSubItems',
    },
    activeId: null,
    mainActiveIndex: {
      type: Number,
      value: 0,
      observer: 'updateSubItems',
    },
    height: {
      type: [Number, String],
      value: 300,
    },
    max: {
      type: Number,
      value: Infinity,
    },
    selectedIcon: {
      type: String,
      value: 'success',
    },
    scrollTop: {
      type: Number,
      value: 0,
      observer: 'updateSubItems',
    },
    showScrollbar: {
      type: Boolean,
      value: false,
    }
  },
  data: {
    subItems: [],
  },
  methods: {
    // 当一个子项被选择时
    onSelectItem (event) {
      const { item } = event.currentTarget.dataset;
      const isArray = Array.isArray(this.data.activeId);
      // 判断有没有超出右侧选择的最大数
      const isOverMax = isArray && this.data.activeId.length >= this.data.max;
      // 判断该项有没有被选中, 如果有被选中，则忽视是否超出的条件
      const isSelected = isArray
        ? this.data.activeId.indexOf(item.id) > -1
        : this.data.activeId === item.id;
      if (!item.disabled && (!isOverMax || isSelected)) {
        this.$emit('click-item', item);
      }
    },
    scroll (e) {
      this.$emit('scroll', e.detail.scrollTop)
    },
    // 当一个导航被点击时
    onClickNav (event) {
      const index = event.detail;
      const item = this.data.items[index];
      if (!item.disabled) {
        this.$emit('click-nav', { index });
      }
    },
    // 更新子项列表
    updateSubItems () {
      const { items, mainActiveIndex } = this.data;
      const { children = [] } = items[mainActiveIndex] || {};
      return this.set({ subItems: children });
    },
    scrolltolower () {
      this.$emit('scrolltolower', '');
    }
  },
});
