<template>
  <view class="axq-empty">
    <image class="axq-empty-img" :src="src"></image>
    <text class="axq-empty-text" v-for="(t,i) in tips" :key="i">{{ t }}</text>
  </view>
</template>

<script>
import { appConfig } from '@/config/app'
export default {
  name: 'AxqEmpty',
  components: {},
  mixins: [],
  props: {
    src: { type: String,default: appConfig.staticUrl + 'empty/order.png' },
    tips: { type: Array,default: () => [ '数据空空~' ] }
  },
  data () {
    return {}
  },
  methods: {
  }
}
</script>

<style lang="less">
.axq-empty{
  .flexLayout(@a:center,@j:center,@dir:column);
  &-img{
    margin-top: 223rpx;
    width: 240rpx;
    height: 230rpx;
  }
  &-text{
    font-size: 30rpx;
    color: #666;
    line-height: 1.5em;
    &:first-of-type{
      margin-top: 40rpx;
    }
  }
}
</style>