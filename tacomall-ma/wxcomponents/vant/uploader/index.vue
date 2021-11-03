<template>
<uni-shadow-root class="vant-uploader-index"><view class="van-uploader">
  <view class="van-uploader__wrapper">
    
    <view v-for="(item,index) in (lists)" :key="item.index" v-if="previewImage" class="van-uploader__preview" :data-index="index" @click="onClickPreview">
      <image v-if="item.isImage" :mode="imageFit" :src="item.thumb || item.url" :alt="item.name || ('图片' + index)" class="van-uploader__preview-image" :style="'width: '+(utils.addUnit(previewSize))+'; height: '+(utils.addUnit(previewSize))+';'" :data-index="index" @click="onPreviewImage"></image>
      <video v-else-if="item.isVideo" :src="item.url" :title="item.name || ('视频' + index)" :poster="item.thumb" :autoplay="item.autoplay" class="van-uploader__preview-image" :style="'width: '+(utils.addUnit(previewSize))+'; height: '+(utils.addUnit(previewSize))+';'" :data-index="index" @click="onPreviewVideo">
      </video>
      <view v-else class="van-uploader__file" :style="'width: '+(utils.addUnit(previewSize))+'; height: '+(utils.addUnit(previewSize))+';'">
        <van-icon name="description" class="van-uploader__file-icon"></van-icon>
        <view class="van-uploader__file-name van-ellipsis">{{ item.name || item.url }}</view>
      </view>
      <view v-if="item.status === 'uploading' || item.status === 'failed'" class="van-uploader__mask">
       <van-icon v-if="item.status === 'failed'" name="close" class="van-uploader__mask-icon"></van-icon>
       <van-loading v-else custom-class="van-uploader__loading"></van-loading>
       <text v-if="item.message" class="van-uploader__mask-message">{{ item.message }}</text>
      </view>
      <view v-if="deletable && item.deletable" :data-index="index" class="van-uploader__preview-delete" @click.stop.prevent="deleteItem">
        <van-icon name="cross" class="van-uploader__preview-delete-icon"></van-icon>
      </view>
    </view>

    
    <block v-if="isInCount">
      <view class="van-uploader__slot" @click="startUpload">
        <slot></slot>
      </view>

      
      <view v-if="showUpload" :class="'van-uploader__upload '+(disabled ? 'van-uploader__upload--disabled': '')" :style="'width: '+(utils.addUnit(previewSize))+'; height: '+(utils.addUnit(previewSize))+';'" @click="startUpload">
        <van-icon :name="uploadIcon" class="van-uploader__upload-icon"></van-icon>
        <text v-if="uploadText" class="van-uploader__upload-text">{{ uploadText }}</text>
      </view>
    </block>
  </view>
</view></uni-shadow-root>
</template>
<wxs src="../wxs/utils.wxs" module="utils"></wxs>
<script>
import VanIcon from '../icon/index.vue'
import VanLoading from '../loading/index.vue'
global['__wxVueOptions'] = {components:{'van-icon': VanIcon,'van-loading': VanLoading}}

global['__wxRoute'] = 'vant/uploader/index'
import { VantComponent } from '../common/component';
import { isImageFile, chooseFile, isVideoFile } from './utils';
import { chooseImageProps, chooseVideoProps } from './shared';
import { isBoolean, isPromise } from '../common/validator';
VantComponent({
  props: Object.assign(
    Object.assign(
      {
        disabled: Boolean,
        multiple: Boolean,
        uploadText: String,
        useBeforeRead: Boolean,
        afterRead: null,
        beforeRead: null,
        previewSize: {
          type: null,
          value: 80
        },
        name: {
          type: [ Number, String ],
          value: ''
        },
        accept: {
          type: String,
          value: 'image'
        },
        fileList: {
          type: Array,
          value: [],
          observer: 'formatFileList'
        },
        maxSize: {
          type: Number,
          value: Number.MAX_VALUE
        },
        maxCount: {
          type: Number,
          value: 100
        },
        deletable: {
          type: Boolean,
          value: true
        },
        showUpload: {
          type: Boolean,
          value: true
        },
        previewImage: {
          type: Boolean,
          value: true
        },
        previewFullImage: {
          type: Boolean,
          value: true
        },
        imageFit: {
          type: String,
          value: 'scaleToFill'
        },
        uploadIcon: {
          type: String,
          value: 'photograph'
        }
      },
      chooseImageProps
    ),
    chooseVideoProps
  ),
  data: {
    lists: [],
    isInCount: true
  },
  methods: {
    formatFileList () {
      const { fileList = [], maxCount } = this.data;
      const lists = fileList.map((item) =>
        Object.assign(Object.assign({}, item), {
          isImage: isImageFile(item),
          isVideo: isVideoFile(item),
          deletable: isBoolean(item.deletable) ? item.deletable : true
        })
      );
      console.log(lists);
      this.setData({ lists, isInCount: lists.length < maxCount });
    },
    getDetail (index) {
      return {
        name: this.data.name,
        index: index == null ? this.data.fileList.length : index
      };
    },
    startUpload () {
      const { maxCount, multiple, accept, lists, disabled } = this.data;
      if (disabled) return;
      chooseFile(
        Object.assign(Object.assign({}, this.data), {
          maxCount: maxCount - lists.length
        })
      )
        .then((res) => {
          console.log(res);
          this.onBeforeRead(multiple ? res : res[0]);
        })
        .catch((error) => {
          this.$emit('error', error);
        });
    },
    onBeforeRead (file) {
      const { beforeRead, useBeforeRead } = this.data;
      let res = true;
      if (typeof beforeRead === 'function') {
        res = beforeRead(file, this.getDetail());
      }
      if (useBeforeRead) {
        res = new Promise((resolve, reject) => {
          this.$emit(
            'before-read',
            Object.assign(Object.assign({ file }, this.getDetail()), {
              callback: (ok) => {
                ok ? resolve() : reject();
              }
            })
          );
        });
      }
      if (!res) {
        return;
      }
      if (isPromise(res)) {
        res.then((data) => this.onAfterRead(data || file));
      } else {
        this.onAfterRead(file);
      }
    },
    onAfterRead (file) {
      const { maxSize, afterRead } = this.data;
      const oversize = Array.isArray(file)
        ? file.some((item) => item.size > maxSize)
        : file.size > maxSize;
      if (oversize) {
        this.$emit('oversize', Object.assign({ file }, this.getDetail()));
        return;
      }
      if (typeof afterRead === 'function') {
        afterRead(file, this.getDetail());
      }
      this.$emit('after-read', Object.assign({ file }, this.getDetail()));
    },
    deleteItem (event) {
      const { index } = event.currentTarget.dataset;
      this.$emit(
        'delete',
        Object.assign(Object.assign({}, this.getDetail(index)), {
          file: this.data.fileList[index]
        })
      );
    },
    onPreviewImage (event) {
      if (!this.data.previewFullImage) return;
      const { index } = event.currentTarget.dataset;
      const { lists } = this.data;
      const item = lists[index];
      wx.previewImage({
        urls: lists.filter((item) => isImageFile(item)).map((item) => item.url),
        current: item.url,
        fail () {
          wx.showToast({ title: '预览图片失败', icon: 'none' });
        }
      });
    },
    onPreviewVideo (event) {
      if (!this.data.previewFullImage) return;
      const { index } = event.currentTarget.dataset;
      const { lists } = this.data;
      wx.previewMedia({
        sources: lists
          .filter((item) => isVideoFile(item))
          .map((item) =>
            Object.assign(Object.assign({}, item), { type: 'video' })
          ),
        current: index,
        fail () {
          wx.showToast({ title: '预览视频失败', icon: 'none' });
        }
      });
    },
    onClickPreview (event) {
      const { index } = event.currentTarget.dataset;
      const item = this.data.lists[index];
      this.$emit(
        'click-preview',
        Object.assign(Object.assign({}, item), this.getDetail(index))
      );
    }
  }
});
export default global['__wxComponents']['vant/uploader/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-uploader{position:relative;display:inline-block}.van-uploader__wrapper{display:-webkit-flex;display:flex;-webkit-flex-wrap:wrap;flex-wrap:wrap}.van-uploader__slot:empty{display:none}.van-uploader__slot:not(:empty)+.van-uploader__upload{display:none!important}.van-uploader__upload{position:relative;display:-webkit-flex;display:flex;-webkit-flex-direction:column;flex-direction:column;-webkit-align-items:center;align-items:center;-webkit-justify-content:center;justify-content:center;box-sizing:border-box;width:80px;height:80px;margin:0 8px 8px 0;background-color:#f7f8fa}.van-uploader__upload:active{background-color:#f2f3f5}.van-uploader__upload-icon{color:#dcdee0;font-size:24px}.van-uploader__upload-text{margin-top:8px;color:#969799;font-size:12px}.van-uploader__upload--disabled{opacity:.5;opacity:var(--uploader-disabled-opacity,.5)}.van-uploader__preview{position:relative;margin:0 8px 8px 0;cursor:pointer}.van-uploader__preview-image{display:block;width:80px;height:80px;overflow:hidden}.van-uploader__preview-delete{position:absolute;top:0;right:0;width:14px;height:14px;padding:0 0 8px 8px}.van-uploader__preview-delete:after{position:absolute;top:0;right:0;width:14px;height:14px;background-color:rgba(0,0,0,.7);border-radius:0 0 0 12px;content:""}.van-uploader__preview-delete-icon{position:absolute;top:-2px;right:-2px;z-index:1;color:#fff;font-size:16px;-webkit-transform:scale(.5);transform:scale(.5)}.van-uploader__file{display:-webkit-flex;display:flex;-webkit-flex-direction:column;flex-direction:column;-webkit-align-items:center;align-items:center;-webkit-justify-content:center;justify-content:center;width:80px;height:80px;background-color:#f7f8fa}.van-uploader__file-icon{color:#646566;font-size:20px}.van-uploader__file-name{box-sizing:border-box;width:100%;margin-top:8px;padding:0 4px;color:#646566;font-size:12px;text-align:center}.van-uploader__mask{position:absolute;top:0;right:0;bottom:0;left:0;display:-webkit-flex;display:flex;-webkit-flex-direction:column;flex-direction:column;-webkit-align-items:center;align-items:center;-webkit-justify-content:center;justify-content:center;color:#fff;background-color:rgba(50,50,51,.88)}.van-uploader__mask-icon{font-size:22px}.van-uploader__mask-message{margin-top:6px;padding:0 4px;font-size:12px;line-height:14px}.van-uploader__loading{width:22px;height:22px;color:#fff!important}
</style>