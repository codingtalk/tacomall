<template>
  <view class="cell-item">
    <view class="item_left">
      <text>{{ title }}</text>
    </view>
    <view class="item_right">
      <slot></slot>
      <text v-if="value">{{ value }}</text>
      <text class="iconfont" v-if="isLink">&#xe600;</text>
    </view>
  </view>
</template>

<script>
export default {
  props: ["title", "value", "isLink"],
};
</script>

<style lang="less" scoped>

.cell-item {
  position: relative;
  .flex($d: row, $j: space-between);
  min-height: 133rpx;

  .item_left {
    .flex();

    text {
      font-size: 34rpx;
      color: #333;
    }
  }

  .item_right {
    .flex($d: row);
    font-size: 34rpx;
    color: #999;
  }

  &::before {
    position: absolute;
    content: "";
    top: 0;
    left: 0;
    width: 100%;
    height: 0;
    border-top: 1px solid #e8eaec;
    transform: scaleY(0.5);
  }
}
</style>