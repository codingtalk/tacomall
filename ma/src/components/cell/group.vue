<template>
  <view class="cell-group">
    <view class="group_header">
      <view class="header_left">
        <text>{{ title }}</text>
      </view>
    </view>
    <view class="group_content">
      <slot></slot>
    </view>
  </view>
</template>

<script>
export default {
  props: ['title']
}
</script>

<style lang="less">

.cell-group {
  padding: 0 34rpx;
  margin-bottom: 20rpx;
  background: white;

  .group_header {
    display: flex;
    justify-content: space-between;
    height: 100rpx;

    .header_left {
      display: flex;
      flex-direction: column;
      justify-content: flex-end;

      text {
        padding-bottom: 20rpx;
        color: #666;
        font-size: 34rpx;
      }
    }
  }
}
</style>