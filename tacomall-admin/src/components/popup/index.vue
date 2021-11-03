<!--
 * @Author: 码上talk|RC
 * @Date: 2021-01-21 14:28:25
 * @LastEditTime: 2021-04-29 19:54:15
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /vuejs-pc-web/src/components/popup/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <transition name="popup-fade">
    <div class="popup" @click="visible = false" v-if="visible">
      <div class="p-content" :class="contentClass" @click.stop>
        <slot v-if="slotVisible"></slot>
        <render :tpl="tpl" v-else></render>
      </div>
    </div>
  </transition>
</template>

<script>

import render from './render';
export default {
  components: {
    render
  },
  props: {
    tpl: {
      type: Function,
      default () {
        return '';
      }
    },
    value: {
      type: Boolean
    },
    model: {
      type: String,
      default () {
        return 'right';
      }
    }
  },
  data () {
    return {
      visible: false
    };
  },
  computed: {
    slotVisible () {
      return !!this.$slots.default;
    },
    contentClass () {
      return `p-content-${this.model}`;
    }
  },
  watch: {
    value: function (val) {
      this.visible = val;
    },
    visible: function (val) {
      if (val) {
        this.$emit('created');
      } else {
        this.$emit('destroyed');
      }
      this.$emit('input', val);
    }
  }
};
</script>

<style lang="less">
.popup {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.7);
    z-index: 999;
    .p-content {
        display: none;
        overflow-y: auto;
        &-right {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            display: block;
            min-width: 300px;
            background: white;
        }

        &-center {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            display: block;
        }
    }
}

.popup-fade-enter-active,
.popup-fade-leave-active {
    transition: opacity 0.4s;
}

.popup-fade-enter,
.popup-fade-leave-to {
    opacity: 0;
}
</style>
