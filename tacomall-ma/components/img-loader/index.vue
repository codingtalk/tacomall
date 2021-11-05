<!--
 * @Author: 码上talk|RC
 * @Date: 2020-12-29 19:55:56
 * @LastEditTime: 2021-10-26 16:26:36
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/components/img-loader/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <view class="img-loader">
    <view class="l-error" v-if="isLoadError">
      <image src="@/static/image/img-default.png" />
    </view>
    <view class="l-img" v-else>
      <image :src="`${ossUrl}${src}?x-oss-process=image/resize,m_mfit,h_160`" @error="imgError" alt />
    </view>
  </view>
</template>

<script>
import { appConfig } from '@/config/app'
export default {
  props: ['src'],
  data () {
    return {
      ossUrl: appConfig.ossUrl,
      isLoadError: false
    }
  },
  methods: {
    imgError () {
      this.isLoadError = true
    }
  }
}
</script>


<style lang="less">
.img-loader {
  width: 100%;
  height: 100%;
  .l-img {
    position: relative;
    width: 100%;
    height: 100%;
    overflow: hidden;
    image {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 100%;
      height: 100%;
    }
  }
  .l-error {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
    background: #f8f8f8;
    image {
      width: 40%;
      height: 40%;
    }
  }
}
</style>