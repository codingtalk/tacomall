<template>
  <view class="page gallery">
    <weixin-wrapper>
      <view class="index_main">
      </view>
    </weixin-wrapper>
  </view>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import weixinWrapper from '@/components/weixin-wrapper'
import { checkToken, pullDownRefresh } from "@/composables/page";
import { STATIC_PATH } from '@/config'
import { $toast, go } from 'codingtalk-uni-toolkit';


pullDownRefresh((ok) => {
  ok()
})
onMounted(() => {
});
</script>


<style lang="less">
@import url("./__style__/index.less");
</style>
