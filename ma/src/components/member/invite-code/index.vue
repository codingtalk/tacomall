<template>
  <view class="member_invite-code">
    <view class="invite-code_box">
      <image :src="`https:${url}`" v-if="url"></image>
    </view>
    <view class="invite-code_share">
      <button open-type="share">分享</button>
    </view>
    <view class="invite-code_tip">
      <text>提示：通过分享二维码或者邀请他人微信扫码，便可成功邀请</text>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { http, getGlobalVariable } from 'codingtalk-uni-toolkit';

const props = defineProps(['memberId'])
const { api: apiJson } = getGlobalVariable('app');
const url = ref('')

onMounted(() => {
  http.request({
    baseURL: apiJson['open'].hostname,
    url: apiJson['open'].modules['member']['inviteCode'].path,
    params: {
      memberId: props.memberId
    },
    data: {}
  }).then(res => {
    const { status, data } = res;
    if (status) {
      url.value = data
    }
  })
})
</script>

<style lang="less">
@import url("../../../assets/style/var.less");
@import url("../../../assets/style/mixins.less");

.member_invite-code {
  .flex(@d: column, @a: center);

  .invite-code_ {
    &box {
      width: 500rpx;
      height: 500rpx;

      image {
        width: 100%;
        height: 100%;
      }
    }

    &share {
      width: 600rpx;
      margin: 40rpx 0;

      button {
        background: white;
        border: 1rpx solid @primary-color;
        color: @primary-color;
      }
    }

    &tip {
      color: #777;

      text {
        white-space: pre-wrap;
        text-align: center;
      }
    }
  }
}
</style>