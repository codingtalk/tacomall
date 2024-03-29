<template>
  <view class="page login">
    <image class="login_background" :src="STATIC_PATH('login_background.png')"></image>
    <image class="login_logo" :src="STATIC_PATH('logo.png')"></image>
    <button class='login_submit' open-type="getUserInfo" withCredentials="true" lang="zh_CN" @click="login">授权微信</button>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Member } from '@/entity';
import { TOKEN_KEY, MP_APPID } from '@/config';
import { storeApp } from '@/composables/store';
import { rep, swi, $toast } from 'codingtalk-uni-toolkit';
import { localCache } from "codingtalk-core"
import { STATIC_PATH } from '@/config'
import { onLoad } from '@dcloudio/uni-app'

const { getMemberInfo, } = storeApp()
const invite = ref('')

const login = (e) => {
  uni.login({
    success(loginRes) {
      const { code } = loginRes
      Member.sendApi('wxLogin', {
        params: { code, appid: MP_APPID, invite: invite.value }, body: {}
      }, { errorTip: 'Login failed' }).then(res => {
        const { status, data } = res;
        if (status) {
          const { token, isNew } = data
          $toast('登录成功');
          localCache.set(TOKEN_KEY, token);
          getMemberInfo();
          setTimeout(() => {
            isNew ? rep('/pages/profile/initial/index') : swi('/pages/index/index')
          }, 1000);
        }
      });
    }
  })
}

onLoad(opt => {
  invite.value = opt?.invite
})
</script>

<style lang="less">
@import url("./__style__/index.less");
</style>