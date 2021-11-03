<!--
 * @Author: 码上talk|RC
 * @Date: 2020-10-10 17:12:19
 * @LastEditTime: 2021-07-05 09:25:01
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/img-video-uploader/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="img-video-uploader">
    <div class="u-list">
      <div class="l-item" :key="key" v-for="(item, key) in result">
        <div class="i-img">
          <img :src="`${item.fullUrl()}?x-oss-process=image/resize,m_mfit,h_160`" alt />
        </div>
        <div class="i-delete" @click="deleteImg(key)">
          <i class="iconfont icon-shanchu"></i>
        </div>
      </div>
    </div>
    <div class="u-btn" @click="upload" v-if="result.length < max">
      <i class="iconfont icon-shangchuan"></i>
    </div>
  </div>
</template>

<script>

import { OssLib } from '@/utils/oss';
import { model } from '@/model';

const { Oss } = model.collection;

const EVENT_EMIT = {
  U_S: 'upload-success'
};
export default {
  props: ['max', 'value'],
  data () {
    return {
      ossLib: new OssLib(),
      result: this.value ? this.value.map(i => { return i instanceof Oss ? i : new Oss({ filename: i }); }) : []
    };
  },
  methods: {
    deleteImg (k) {
      this.result.splice(k, 1);
    },
    upload () {
      this.ossLib.uploadLocalFile().then(res => {
        const { callbackData } = res;
        const { status, data } = callbackData;
        if (status) {
          this.result.push(new Oss(data));
        }
      }).catch((reject) => {
        this.$message.error('图片上传失败');
      });
    }
  },
  watch: {
    value: function (e) {
      this.result = e ? e.map(i => { return i instanceof Oss ? i : new Oss({ filename: i }); }) : [];
    },
    result: function (e) {
      this.$emit(EVENT_EMIT.U_S, e);
    }
  }
};
</script>

<style lang="less">
.img-video-uploader {
  display: flex;
  .u-list {
    display: flex;
    align-items: center;
    .l-item {
      position: relative;
      width: 100px;
      height: 100px;
      .i-img {
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
      .i-delete {
        position: absolute;
        top: 0;
        left: 0;
        display: none;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.3);
        i {
          font-size: 30px;
          color: white;
        }
      }
      &:hover {
        cursor: pointer;
        .i-delete {
          display: flex;
        }
      }
    }
  }
  .u-btn {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100px;
    height: 100px;
    box-sizing: border-box;
    border: 1px dashed #ebeef5;
    i {
      font-size: 40px;
    }
    &:hover {
      cursor: pointer;
    }
  }
}
</style>
