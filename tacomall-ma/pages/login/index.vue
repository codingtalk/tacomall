<!--
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:50:41
 * @LastEditTime: 2021-10-21 16:03:56
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/pages/login/index.vue
-->
<template>
  <view class="page login">
    <view class="l-wrap">
      <view class="w-title">
        <text>手机号登录</text>
      </view>
      <view class="w-form">
        <view class="f-input">
          <view class="i-item">
            <input type="text" placeholder="请输入手机号" v-model="form.mobile" />
          </view>
          <view class="i-item">
            <input type="password" placeholder="请输入密码" v-model="form.passwd" />
          </view>
        </view>
        <view class="f-btn" @tap="doMobileLogin">
          <text>登录</text>
        </view>
      </view>
      <view class="w-service">
        <view class="s-left">
          <text>点击登录即同意</text>
          <text @tap="nav('/pages/h5/index')">《用户手册》</text>
        </view>
        <view class="s-right" @tap="red('/pages/register/index')">
          <text>去注册</text>
        </view>
      </view>
      <view class="w-third">
        <view class="t-title">
          <text>第三方账号登录：</text>
        </view>
        <view class="t-entry">
          <view class="e-item">
            <text class="iconfont" style="color: #ff8200">&#xe61b;</text>
          </view>
          <view class="e-item">
            <text class="iconfont" style="color: #07c160">&#xe7e5;</text>
            <button open-type="getUserInfo" lang="zh_CN"></button>
          </view>
          <view class="e-item">
            <text class="iconfont" style="color: #007fff">&#xe64b;</text>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { model } from '@/model';
const { collection } = model;
const { Member } = collection;
import { localCache } from '@/utils/localCache';

import {
  mapActions
} from 'vuex'
export default {
  data () {
    return {
      time: 0,
      form: {
        mobile: '',
        passwd: ''
      }
    };
  },
  methods: {
    ...mapActions('user', ['getUserInfo']),
    doMobileLogin () {
      if (this.isObjHasBlank(this.form)) {
        this.toast('请校验输入信息');
        return;
      }
      Member.sendApi('loginByMobile', { params: this.form, data: {} }, { errorTip: '登录失败' }).then(res => {
        const { status, data } = res;
        if (status) {
          this.toast('登录成功');
          localCache.setToken(data);
          this.getUserInfo();
          setTimeout(() => {
            this.swi('/pages/index/index');
          }, 1000);
        }
      });
    }
  }
}
</script>

<style lang="less">
@import "./index";
</style>
