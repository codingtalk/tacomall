<!--
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:50:40
 * @LastEditTime: 2021-11-05 15:20:31
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/components/empty/index.vue
-->
<template>
  <view class="empty">
    <image class="e-img" src="@/static/image/blank-data.png"></image>
    <text class="e-text" v-for="(t,i) in tips" :key="i">{{ t }}</text>
  </view>
</template>

<script>
export default {
  name: 'AxqEmpty',
  components: {},
  mixins: [],
  props: {
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
.empty{
  .layout_flex(@a:center,@j:center,@f:column);
  .e-img{
    margin-top: 223rpx;
    width: 240rpx;
    height: 230rpx;
  }
  .e-text{
    font-size: 30rpx;
    color: #666;
    line-height: 1.5em;
    &:first-of-type{
      margin-top: 40rpx;
    }
  }
}
</style>