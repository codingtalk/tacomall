<!--
 * @Author: 码上talk|RC
 * @Date: 2020-10-10 17:12:19
 * @LastEditTime: 2021-06-13 15:28:13
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/file-uploader/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="file-uploader">
    <div class="u-btn">
      <el-button size="small" type="primary" @click.native="upload">点击上传</el-button>
    </div>
    <div class="u-tip">
      <p>{{ tip || `上传仅限于${extLimit.join(',')}格式，单次上传限制200MB` }}</p>
    </div>
    <div class="u-list">
      <div class="l-item" :key="key" v-for="(item, key) in fileList">
        <img
          class="file-img"
          :src="item.oss.url"
          alt
          v-if="viewEnableExt.includes(item.info.name.getExt())"
          @click="showPopup(item)"
        />
        <template v-else>
          <div class="file-icon">
            <i
              class="iconfont"
              :class="item.info.name | fileIconfontFormat"
              @click="showPopup(item)"
            ></i>
          </div>
        </template>
        <p>{{ item.info.name }}</p>
        <i class="iconfont icon-shanchu" @click="remove(key)"></i>
      </div>
    </div>
  </div>
</template>

<script>
import filePreviewer from '../file-previewer';
import popup from '../popup';
import viewer from '../img-video-previewer/viewer';
import { OssLib } from '@/utils/oss';
import { model } from '@/model';

const { Oss } = model.collection;
const viewEnableExt = ['jpg', 'png'];
export default {
  components: {
    filePreviewer
  },
  props: {
    max: {
      type: Number,
      default () {
        return 3;
      }
    },
    value: {
      type: Array,
      default () {
        return [];
      }
    },
    tip: {
      type: String,
      default () {
        return '';
      }
    },
    extLimit: {
      type: Array,
      default () {
        return ['JPG', 'PNG', 'ZIP', 'EXCEL', 'PDF'];
      }
    }
  },
  data () {
    return {
      ossLib: new OssLib(),
      fileList: [],
      viewEnableExt
    };
  },
  watch: {
    fileList: {
      handler: function () {
        this.$emit('change', JSON.stringify(this.fileList.map(i => ({ info: i.info, oss: i.oss }))));
      }
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
    upload () {
      if (this.fileList.length >= this.max) {
        this.$message.warning(`最大上传数量：${this.max}`);
        return;
      }
      this.ossLib.uploadLocalFile().then(res => {
        const { file, callbackData } = res;
        const { status, data } = callbackData;
        this.fileList.push({
          _file: file,
          info: {
            name: file.name,
            size: file.Size
          },
          oss: data
        });
      }).catch((e) => {
        this.$message.warning('上传失败');
      });
    },
    showPopup (item) {
      if (!viewEnableExt.includes(this.$_.get(item, 'info.name').getExt())) {
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
    remove (key) {
      this.fileList.splice(key, 1);
    }

  }
};
</script>

<style lang="less">
.file-uploader {
  display: inline-block;
  .u-list {
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
      .file-img {
        width: 38px;
        height: 38px;
        background-size: cover;
      }
      .file-icon {
        width: 38px;
        height: 38px;
        text-align: center;
        line-height: 28px;
        i {
          padding-top: 5px;
          padding-left: 0;
          font-size: 38px;
        }
      }
      p {
        flex: 3;
        padding: 0;
        margin-left: 10px;
        font-size: 12px;
        font-weight: 300;
        color: #0b83ff;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      i {
        display: inline-block;
        padding-left: 20px;
      }
    }
  }
}
</style>
