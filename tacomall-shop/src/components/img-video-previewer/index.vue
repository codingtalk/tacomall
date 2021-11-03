<!--
 * @Author: 码上talk|RC
 * @Date: 2020-12-29 19:17:53
 * @LastEditTime: 2021-08-07 09:12:57
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/img-video-previewer/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="img-video-previewer">
    <div class="p-error" v-if="isLoadError">
      <i class="iconfont icon-img"></i>
    </div>
    <div class="p-img" @click="showPopup" v-else>
      <img :src="`${fullUrl}?x-oss-process=image/resize,m_mfit,h_160`" @error="imgError" alt />
    </div>
  </div>
</template>

<script>
import popup from '../popup';
import viewer from './viewer';
import { model } from '@/model';

const { Oss } = model.collection;

export default {
  props: ['isRawUrl', 'src'],
  data () {
    return {
      isLoadError: false
    };
  },
  computed: {
    fullUrl () {
      return this.isRawUrl ? this.src : new Oss({ filename: this.src }).fullUrl();
    }
  },
  methods: {
    imgError () {
      this.isLoadError = true;
    },
    showPopup () {
      popup((h) => {
        return h(viewer, {
          props: {
            src: this.fullUrl
          }
        });
      }, { model: 'center' });
    }
  }
};
</script>

<style lang="less">
.img-video-previewer {
  width: 100%;
  height: 100%;
  .p-img {
    position: relative;
    width: 100%;
    height: 100%;
    overflow: hidden;
    img {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      height: 100%;
    }
  }
  .p-error {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
    background: #f8f8f8;
    i {
      font-size: 30px;
      color: #555555;
    }
  }
}
</style>
