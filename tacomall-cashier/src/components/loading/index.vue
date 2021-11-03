<!--
 * @Author: 码上talk|RC
 * @Date: 2021-05-30 16:35:58
 * @LastEditTime: 2021-06-13 13:33:52
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/loading/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<!--
 * @Author: 码上talk|RC
 * @Date: 2021-05-29 23:15:55
 * @LastEditTime: 2021-05-29 23:26:47
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/loading/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="loading" :class="{loading_fixed: mode === 'fixed'}" v-if="visible">
    <div class="l-item l-item_1"></div>
    <div class="l-item l-item_2"></div>
    <div class="l-item l-item_3"></div>
    <div class="l-item l-item_4"></div>
    <div class="l-item l-item_5"></div>
  </div>
</template>

<script>
export default {
  props: {
    initVisible: {
      type: Boolean,
      default () {
        return true;
      }
    }
  },
  data () {
    return {
      mode: '',
      visible: this.initVisible
    };
  }
};
</script>

<style lang="less">
@-webkit-keyframes stretch-delay {
  0%,
  40%,
  100% {
    -webkit-transform: scaleY(0.4);
  }
  20% {
    -webkit-transform: scaleY(1);
  }
}

@keyframes stretch-delay {
  0%,
  40%,
  100% {
    transform: scaleY(0.4);
    -webkit-transform: scaleY(0.4);
  }
  20% {
    transform: scaleY(1);
    -webkit-transform: scaleY(1);
  }
}

.loading {
  display: flex;
  align-items: center;
  width: 80px;
  height: 120px;
  .l-item {
    height: 100%;
    flex: 1;
    -webkit-animation: stretch-delay 1.2s infinite ease-in-out;
    animation: stretch-delay 1.2s infinite ease-in-out;
    background: #007fff;

    &_2 {
      -webkit-animation-delay: -1.1s;
      animation-delay: -1.1s;
    }

    &_3 {
      -webkit-animation-delay: -1s;
      animation-delay: -1s;
    }

    &_4 {
      -webkit-animation-delay: -0.9s;
      animation-delay: -0.9s;
    }

    &_5 {
      -webkit-animation-delay: -0.8s;
      animation-delay: -0.8s;
    }
    &:not(:first-child) {
      margin-left: 10px;
    }
  }
  &_fixed {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
}
</style>
