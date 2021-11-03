<!--
 * @Author: 码上talk|RC
 * @Date: 2021-06-13 15:39:58
 * @LastEditTime: 2021-09-05 15:01:47
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/file-previewer/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="file-previewer">
    <div class="l-list">
      <div class="l-item" :key="key" v-for="(item, key) in fileList">
        <div class="i-left" v-if="viewEnableExt.includes(item.info.name.getExt())">
          <img :src="item.oss.url" alt @click="showPopup(item)" />
        </div>
        <template v-else>
          <div class="file-icon">
            <i
              class="iconfont"
              :class="item.info.name | fileIconfontFormat"
              @click="showPopup(item)"
            ></i>
          </div>
        </template>
        <p class="i-middle">{{ blankGet(item, 'info.name') }}</p>
        <i class="i-right iconfont icon-xiazai" @click="download(item.oss)"></i>
      </div>
    </div>
  </div>
</template>

<script>
import popup from '../popup';
import viewer from './viewer';
import downloader from '@/utils/downloader';
import { model } from '@/model';

const { Oss } = model.collection;

const viewEnableExt = ['png', 'jpg'];

export default {
  props: {
    value: {
      type: String,
      default () {
        return '';
      }
    }
  },
  data () {
    return {
      viewEnableExt,
      fileList: this.value ? this.value.toParse() : []
    };
  },
  watch: {
    value: function () {
      this.fileList = this.value ? this.value.toParse() : [];
    }
  },
  filters: {
    fileIconfontFormat (e) {
      const suffix2icon = {
        zip: 'icon-zip',
        excel: 'icon-web-icon-1',
        pdf: 'icon-bg-pdf'
      };
      return suffix2icon[e.getExt()] || 'icon-wenjian_weizhi';
    }
  },
  methods: {
    showPopup (item) {
      if (!viewEnableExt.includes(this.$_.get(item, 'info.name').getExt().toLowerCase())) {
        this.$message.warning('文件类型不支持预览');
        return;
      }
      popup((h) => {
        return h(viewer, {
          props: {
            src: new Oss(item.oss).fullUrl()
          }
        });
      }, { model: 'center' });
    },
    download (s) {
      downloader.do(new Oss(s).fullUrl());
    }
  }
};
</script>

<style lang="less">
.file-previewer {
  .l-list {
    .l-item {
      display: flex;
      align-items: center;
      justify-content: flex-start;
      width: 260px;
      height: 58px;
      background: #ffffff;
      border: 1px solid #d9d9d9;
      margin-bottom: 18px;
      padding: 0 10px;
      .i-left {
        width: 38px;
        height: 38px;
        cursor: pointer;
        img {
          width: 100%;
          height: 100%;
          background-size: cover;
        }
      }
      .file-icon {
        width: 38px;
        height: 38px;
        text-align: center;
        line-height: 38px;
        i {
          padding-top: 20px;
          padding-left: 0;
          font-size: 40px;
        }
      }
      .i-middle {
        flex: 3;
        margin-left: 10px;
        color: #1890ff;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .i-right {
        cursor: pointer;
        font-size: 26px;
        color: #515a6e;
      }
    }
  }
}
</style>
