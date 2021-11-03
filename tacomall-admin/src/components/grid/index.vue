<!--
 * @Author: 码上talk|RC
 * @Date: 2021-06-22 19:01:02
 * @LastEditTime: 2021-08-25 18:03:40
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/grid/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="grid" :class="{'grid--border': border}">
    <div class="g-title" v-if="title">
      <span>{{ title }}</span>
    </div>
    <div
      class="g-content"
      :style="{gridTemplateColumns: `repeat(${this.col},1fr)`, gridTemplateRows: `repeat(${this.row},1fr)`}"
      v-if="$slots.default"
    >
      <slot></slot>
    </div>
    <div class="g-content--no-grid">
      <slot name="noGrid"></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: [String],
    border: [Boolean],
    col: {
      type: Number,
      default () {
        return 2;
      }
    },
    row: {
      type: Number,
      default () {
        return 2;
      }
    },
    config: {
      type: Object,
      default () {
      }
    }
  }
};
</script>

<style lang="less">
.grid {
  padding: 0px 0 20px;
  .g-title {
    padding: 0px 0 10px;
    font-size: 18px;
    color: #666;
  }
  .g-content {
    display: grid;
    width: 100%;
    & > .grid-item {
      line-height: 22px;
      padding-left: 18px;
    }
  }
  &--border {
    .g-content {
      box-sizing: border-box;
      border-left: 1px solid #ccc;
      border-bottom: 1px solid #ccc;
      & > div {
        box-sizing: border-box;
        border-top: 1px solid #ccc;
        border-right: 1px solid #ccc;
      }
    }
  }
  .grid-item {
    display: flex;
    padding: 2px 18px 2px;
    color: #666;
    & > span {
      color: #666;
      flex-shrink: 0;
    }
  }
}
</style>
