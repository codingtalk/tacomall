<!--
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-19 13:50:41
 * @LastEditTime: 2021-10-21 15:44:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/pages/register/index.vue
-->
<template>
  <view class="page register">
    <view class="r-wrap">
      <view class="w-title">
        <text>会员注册</text>
      </view>
      <view class="w-form">
        <view class="f-input">
          <view class="i-item">
            <input type="text" placeholder="请输入手机号" v-model="form.mobile" />
          </view>
          <view class="i-item">
            <input type="text" placeholder="请输入密码" v-model="form.passwd" />
          </view>
          <view class="i-item">
            <input type="text" placeholder="邀请码" v-model="form.inviteCode" />
          </view>
          <view class="i-item">
            <input type="password" placeholder="请输入验证码" />
            <text class="btn btn-disable" v-if="time">{{ time }}S后重发</text>
            <text class="btn" v-else @tap="sendCode">获取验证码</text>
          </view>
        </view>
        <view class="f-btn" @tap="doRegister">
          <text>注册</text>
        </view>
      </view>
      <view class="w-service">
        <text>点击注册即同意</text>
        <text @tap="nav('/pages/h5/index')">《用户手册》</text>
      </view>
    </view>
  </view>
</template>

<script>
import { model } from '@/model';
const { validate, collection } = model;
const { Member } = collection;

export default {
  data () {
    return {
      time: 0,
      form: {
        mobile: '',
        passwd: '',
        inviteCode: ''
      }
    };
  },
  methods: {
    sendCode () {
      if (!this.form.mobile) {
        this.toast('手机号必填')
        return
      }
      if (!validate.isMobile(this.form.mobile)) {
        this.toast('手机号格式有误')
        return
      }
      let timer = null
      this.time = 60
      timer = setInterval(() => {
        if (this.time === 0 && this.timer) {
          this.time = 60
          timer = null
          return
        }
        this.time = this.time - 1
      }, 1000)
    },
    doRegister () {
      if (this.isObjHasBlank(this.form)) {
        this.toast('请校验输入信息');
        return;
      }
      Member.sendApi('register', { params: this.form, data: {} }, { errorTip: '注册失败' }).then(res => {
        const { status, data } = res;
        if (status) {
          this.toast('注册成功');
          setTimeout(() => {
            this.red('/pages/login/index');
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
