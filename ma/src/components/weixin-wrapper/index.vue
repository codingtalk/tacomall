<template>
  <view class="weixin_wrapper">
    <view class="wrapper_status" :style="{height: `${statusHeight}px`}">
      <slot name="status"></slot>
    </view>
    <view class="wrapper_nav" :style="{top: `${statusHeight}px`, height: `${navHeight}px`}">
      <slot name="nav"></slot>
    </view>
    <view class="wrapper_container">
      <view class="container_blank" :style="{height: `${statusHeight + navHeight}px`}" v-if="!toTop"></view>
      <view class="container_main">
        <slot></slot>
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
const statusHeight = ref(0)
const navHeight = ref(0)
const props = defineProps(['toTop'])
onMounted(() => {
  const menuRect = uni.getMenuButtonBoundingClientRect()
  statusHeight.value = uni.getSystemInfoSync().statusBarHeight
  navHeight.value = (menuRect.top - statusHeight.value) * 2 + menuRect.height
})
</script>

<style lang="less">
@import url("../../assets/style/var.less");
@import url("../../assets/style/mixins.less");
.weixin_wrapper{
  .wrapper_{
    &status{
      position: absolute;
      top:0;
      left:0;
      width:100%;
      z-index: 10;
    }
    &nav{
      position: absolute;
      left:0;
      width:100%;
      z-index: 10;
    }
    &container{
      display: flex;
      flex-direction: column;
      min-height: 100vh;
      .container_{
        &blank{
        }
        &main{
          position: relative;
          flex:1;
        }
      }
    }
  }
}
</style>