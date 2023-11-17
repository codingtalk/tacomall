<template>
  <div class="page login">
    <div class="login_box">
      <div class="box_title">
        <img src="" alt="" />
        <h1>{{appInfoData?.nameEN}}|{{appInfoData?.name + appInfoData?.subTitle}}</h1>
      </div>
      <div class="box_main">
        <div class="main_left">
          <img src="/image/login_left.png" alt="" />
        </div>
        <div class="main_right">
          <div class="right_welcome">
            <h2>欢迎回来 :)</h2>
          </div>
          <div class="right_describe">
            <p>{{appInfoData?.briefs}}</p>
          </div>
          <div class="right_input">
            <div class="input_item">
              <a-input placeholder="账号" v-model:value="formLogin.username" />
            </div>
            <div class="input_item">
              <a-input-password
                placeholder="密码"
                v-model:value="formLogin.passwd"
              />
            </div>
          </div>
          <div class="right_help">
            <div class="help_left">
              <a-radio>记住账号密码</a-radio>
            </div>
            <div class="help_right">
              <span>忘记密码？</span>
            </div>
          </div>
          <div class="right_submit">
            <a-button type="primary" shape="round" :size="size" @click="doLogin"
              >登录账户</a-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import { storeToRefs } from 'pinia';
import { message } from "ant-design-vue";
import { isObjHasBlank, localCache } from "codingtalk-vue-toolkit";
import { go } from "@/utils/navigator";
import { TOKEN_KEY } from "@/config";
import { OrgStaff } from "@/entity";
import useAppStore from '@/store/app';

const appStore = useAppStore();
let { appInfoData } = storeToRefs(appStore);

const formLogin = reactive({
  username: "",
  passwd: "",
});

const doLogin = () => {
  if (isObjHasBlank(formLogin)) {
    message.warning("请确认输入信息");
    return;
  }
  OrgStaff.sendApi(
    "staffLogin",
    { params: formLogin, body: {} },
    { errorTip: "账号密码错误" }
  ).then((res) => {
    const { status, data } = res;
    if (status) {
      localCache.set(TOKEN_KEY, data);
      go("/index");
    }
  });
};
</script>

<style lang="less">
@import url(../assets/style/mixins.less);
@import url(../assets/style/var.less);
.login {
  position: fixed;
  top: 0;
  bottom: 0;
  width: 100%;
  box-sizing: border-box;
  padding-top: 100px;
  .flex(@a: flex-start; @j:center);
  &_box {
    width: 1000px;
    box-sizing: border-box;
    padding: 20px 40px 60px 40px;
    background: white;
    .box_title {
      .flex();
      height: 70px;
    }
    .box_main {
      .flex(@a:flex-start);
      .main_left {
        .flex(@j:center);
        width: 600px;
        img {
          width: 80%;
        }
      }
      .main_right {
        flex: 1;
        .right_describe {
          p {
            color: @text-color;
          }
        }
        .right_help {
          height: 50px;
          .flex(@j:space-between);
        }
      }
    }
  }
}
</style>