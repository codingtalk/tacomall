<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-29 19:32:56
 * @LastEditTime: 2021-08-25 17:45:54
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/popup/type/confirm.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <popup model="center" @created="_createdHandler" v-model="visble">
    <div class="popup_confirm" :style="{width: `${(config.width / vw) * 100 }vw`}">
      <div class="c-header">
        <div class="h-left">
          <span>{{ title }}</span>
        </div>
      </div>
      <div class="c-main" :style="{height: `${(config.height / vw) * 100}vw`}">
        <slot></slot>
      </div>
      <div class="c-footer">
        <div class="f-left">
          <slot name="footer-left"></slot>
        </div>
        <div class="f-right">
          <slot name="footer-right" v-if="$slots['footer-right']"></slot>
          <template v-else>
            <el-button size="mini" @click.native="action.fn.cancel">取消</el-button>
            <el-button
              type="primary"
              size="mini"
              @click.native="action.fn.confirm"
            >{{ confirmText }}</el-button>
          </template>
        </div>
      </div>
    </div>
    <div class="popup-loading" v-if="loading">
      <div class="l-icon">
        <loading></loading>
      </div>
      <div class="l-text">{{ config.loadingText }}</div>
    </div>
  </popup>
</template>

<script>
import popup from '../index.vue';
import loading from '../../loading/index.vue';

const vw = 1920;

export default {
  components: {
    popup,
    loading
  },
  props: {
    title: {
      type: String,
      default () {
        return '';
      }
    },
    confirmText: {
      type: String,
      default () {
        return '确定';
      }
    },
    config: {
      type: Object,
      default () {
        return {
          width: 900,
          height: 500,
          loadingText: ''
        };
      }
    }
  },
  data () {
    return {
      vw,
      visble: false,
      loading: false,
      openOptions: null,
      action: {
        fn: {
          cancel: () => {
            this.visble = false;
          },
          confirm: () => {
            if (this.openOptions && this.openOptions.triggerClose) {
              this.loading = true;
              this.openOptions.triggerClose(() => {
                this.loading = false;
                this.visble = false;
              }, () => {
                this.loading = false;
              });
              return;
            }
            this.visble = false;
          }
        }
      }
    };
  },
  methods: {
    _createdHandler () {
      this.$emit('created');
    },
    open (options) {
      this.openOptions = options;
      if (this.openOptions && this.openOptions.triggerOpen) {
        this.openOptions.triggerOpen(() => {
          this.visble = true;
        }, this.openOptions.params);
        return;
      }
      this.visble = true;
    },
    close () {
      this.visble = false;
    }
  }
};
</script>

<style lang="less">
.popup_confirm {
  background: white;
  .c-header {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    padding: 0 20px;
    border-bottom: 1px solid #ebeef5;

    .h-left {
      span {
        font-weight: bold;
        color: #2d8cf0;
      }
    }

    &:before {
      position: absolute;
      content: "";
      top: 10px;
      left: 5px;
      width: 5px;
      height: 30px;
      border-radius: 2px;
      background: #2d8cf0;
    }
  }

  .c-main {
    position: relative;
    overflow-y: auto;
  }

  .c-footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    padding: 0 20px;
    border-top: 1px solid #ebeef5;
  }
}
.popup-loading {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  .l-text {
    margin-top: 20px;
  }
}
</style>
