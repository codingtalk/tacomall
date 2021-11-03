<!--
 * @Author: 码上talk|RC
 * @Date: 2021-01-05 14:57:14
 * @LastEditTime: 2021-06-13 16:16:33
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/file-previewer/viewer.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->

<template>
  <div class="viewer">
    <div class="v-img" :style="{width: width + 'px', height: height + 'px'}">
      <img :src="src" alt />
    </div>
  </div>
</template>

<script>
const FIXED_WIDTH = 500;
export default {
  props: ['src'],
  data () {
    return {
      width: FIXED_WIDTH,
      height: 0
    };
  },
  watch: {
    src: {
      immediate: true,
      handler (val) {
        const img = new Image();
        img.src = val;
        img.onload = () => {
          this.height = FIXED_WIDTH / img.width * img.height;
        };
      }
    }
  }
};
</script>

<style lang="less">
.viewer {
  display: flex;
  flex-direction: column;
  align-items: center;
  .v-img {
    position: relative;
    img {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 100%;
      height: 100%;
    }
  }
}
</style>
