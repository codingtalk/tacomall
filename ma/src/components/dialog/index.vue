<template>
  <view class="dialog"></view>
</template>

<script setup>
import { refUtil } from 'codingtalk-uni-toolkit';
import { reactive, ref } from 'vue';

const { _refMap, storeRef, getRef } = refUtil();

const open = () => {
}

defineExpose({
  open
})
</script>

<style lang="less">

.dialog {
  width: 600rpx;
  background: white;

  .dialog_box {
    padding: 20rpx;
  }

  .dialog_btn {
    .flex();

    .btn_item {
      .flex(@j: center);
      flex: 1;
      height: 100rpx;

      &--cancel {
        color: @text-color;
      }

      &--confirm {
        color: @primary-color;
      }
    }
  }
}
</style>