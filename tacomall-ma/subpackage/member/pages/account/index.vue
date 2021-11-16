<!--
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2021-11-05 14:29:59
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/subpackage/member/pages/account/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <view class="page account">
    <cell-group title="基础资料">
      <cell title="头像" :is-link="true">
        <div class="a-avatar">
          <image
            src="https://s1.mi-img.com/mfsv2/avatar/fdsc3/p013qAccs3i9/WT2sfu9guuY496.jpg"
          />
        </div>
      </cell>
      <cell title="账号" value="16607574271"></cell>
      <cell title="昵称" value="tacomall" :is-link="true"></cell>
    </cell-group>
    <cell-group title="保密资料">
      <cell title="手机号" value="16607574271" :is-link="true"></cell>
      <cell title="密码" :is-link="true"></cell>
    </cell-group>
    <view class="a-logout">
      <view class="l-btn" @tap="modalLogoutShow = true">
        <text>退出登录</text>
      </view>
    </view>
    <u-modal
      content="注销当前登录账号，是否继续？"
      :show="modalLogoutShow"
      showCancelButton
      closeOnClickOverlay
      @confrim="doLogout"
      @close="() => (modalLogoutShow = false)"
    ></u-modal>
  </view>
</template>

<script>
import cell from "@/components/cell";
import cellGroup from "@/components/cell-group";
import { mapActions } from "vuex";

export default {
  components: {
    cell,
    cellGroup,
  },
  data() {
    return {
      modalLogoutShow: false,
    };
  },
  methods: {
    ...mapActions("user", ["logout"]),
    doLogout() {
      this.logout().then(() => {
        this.swi("/pages/index/index");
      });
      this.swi("/pages/index/index");
    },
  },
};
</script>

<style lang="less" scoped>
.account {
  .a-avatar {
    display: inline-block;
    height: 104rpx;
    width: 104rpx;
    margin-right: 54rpx;
    image {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }
  .a-logout {
    display: flex;
    justify-content: center;
    margin-top: 20rpx;
    .l-btn {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 710rpx;
      height: 80rpx;
      border-radius: 5rpx;
      background: white;
      font-size: 30rpx;
    }
  }
}
</style>
