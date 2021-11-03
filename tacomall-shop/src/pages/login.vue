<template>
  <div class="login">
    <div class="l-box">
      <div class="title">TACOMALL</div>
      <div class="b-input">
        <form-validate
          ref="loginForm"
          :form="form"
          :validator="entitys.TmStaff.formUtil().validate"
        >
          <div class="i-item">
            <form-validate-field :filed="form['username']">
              <el-input v-model="form['username']['value']" clearable placeholder="请输入用户名">
                <template v-slot:prefix>
                  <i class="el-input__icon el-icon-user-solid"></i>
                </template>
              </el-input>
            </form-validate-field>
          </div>
          <div class="i-item">
            <form-validate-field :filed="form['passwd']">
              <el-input
                v-model="form['passwd']['value']"
                clearable
                placeholder="6-16位,可包含数字、字母(区分大小写)"
                type="password"
              >
                <template v-slot:prefix>
                  <i class="el-input__icon el-icon-lock"></i>
                </template>
              </el-input>
            </form-validate-field>
          </div>
        </form-validate>
      </div>
      <div class="b-btn">
        <el-button type="primary" v-loading="loading" v-enter="doLogin">登录</el-button>
      </div>
      <div class="b-footer">
        <div class="f-link">
          <div class="l-item">
            <a href="//www.codigntalk.cn">关于塔可商城</a>
          </div>
        </div>
        <div class="f-copyright">
          <span>Copyright © 2020 TACOMALL</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { model } from '@/model';
import formValidate from '@/components/form-validate';
import formValidateField from '@/components/form-validate/field';
import { localCache } from '@/utils/localCache';
import { Request } from '@/model/request';

const { TmStaff } = model.collection;

export default {
  components: {
    formValidate,
    formValidateField
  },
  data () {
    return {
      request: new Request(),
      entitys: {
        TmStaff
      },
      verify: {
        attr: {
          random: Math.random(),
          code: ''
        },
        fn: {
          reGet: () => {
            this.verify.attr.random = Math.random();
          }
        }
      },
      ui: {
        autoLogin: true
      },
      form: TmStaff.formUtil().generator('other', 'login'),
      loading: false
    };
  },
  methods: {
    doLogin () {
      this.loading = true;
      return new Promise((resolve) => {
        this.$refs.loginForm.validate().then((result) => {
          const { ok, form } = result;
          if (ok) {
            TmStaff.sendApi('staffLogin', { params: form, data: {} }).then(
              (res) => {
                const { status, data } = res;
                if (status) {
                  localCache.setToken(data);
                  this.rep('/index');
                  this.loading = false;
                  resolve();
                } else {
                  this.loading = false;
                  this.$message({
                    message: '账号或密码错误',
                    type: 'warning'
                  });
                }
              }
            );
          } else {
            this.loading = false;
            this.$message({
              message: '请确认信息',
              type: 'warning'
            });
          }
        });
      });
    }
  }
};
</script>
<style lang="less" scoped>
.login {
  min-height: 820px;
  height: 100%;
  width: 100%;
  min-width: 1000px;
  overflow: auto;
  background: url(/image/login_bg.svg) center no-repeat;
  background-size: 100% 100%;
  background-color: #f0f2f5;
  .l-box {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    height: 54%;
    width: 420px;
    padding: 0 35px;
    margin: auto;
    background: hsla(0, 0%, 100%, 0);
    border-radius: 5px;
    z-index: 2;
    .title {
      .layout_flex(@a:center, @j:center);
      width: 100%;
      font-size: 48px; /* px-to-viewport-ignore */
      font-weight: 700;
      margin-bottom: 3.5vh;
      margin-top: 6vh;
    }
    .b-input {
      padding: 20px 0;

      .i-item {
        padding-top: 0;
        .form-validate-field {
          border: 1px solid hsla(0, 0%, 100%, 0.88);
          background: hsla(0, 0%, 100%, 0.88);
          border-radius: 4px;
        }
        /deep/ .el-input {
          margin-right: 5%;
          &__inner {
            background: transparent;
            border: none;
          }
        }
        &.captcha-item {
          font-size: 0;
          /deep/ .el-input {
            width: 60%;
            margin-right: 5%;
          }
          .captcha {
            display: flex;
            align-items: center;
            img {
              width: 80px;
              padding-left: 50px;
            }
          }
        }
        &.msg-code {
          /deep/ .el-input {
            width: 60%;
            margin-right: 5%;
          }
          /deep/ .el-button {
            width: 35%;
            height: 100%;
            border-style: none;
          }
        }
      }
    }
    /deep/ .el-input {
      margin-right: 5%;
      &__inner {
        background: transparent;
        border: none;
      }
    }
    .b-btn {
      width: 100%;
      /deep/ .el-button {
        width: 100%;
      }
    }
    .b-footer {
      position: fixed;
      z-index: 1;
      bottom: 0;
      left: 50%;
      transform: translateX(-50%);
      padding: 48px 0 24px 0;

      .f-link {
        display: flex;
        align-items: center;
        justify-content: center;
        .l-item {
          a {
            color: #515a6e;
          }
          &:not(:first-child) {
            padding-left: 30px;
          }
        }
      }
      .f-copyright {
        display: flex;
        justify-content: center;
        padding-top: 10px;
      }
    }
  }
}
</style>
