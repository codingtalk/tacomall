<!--
 * @Author: 码上talk|RC
 * @Date: 2020-09-28 16:59:53
 * @LastEditTime: 2020-09-30 17:03:49
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-shop/src/pages/account/setting.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="setting-notice">
    <div class="sn-tab">
      <div class="t-item t-item-active">
        <span>基本设置</span>
      </div>
      <div class="t-item">
        <span>安全设置</span>
      </div>
      <div class="t-item">
        <span>消息通知</span>
      </div>
    </div>
    <div class="sn-content">
      <div class="sc-title">
        <span>基本设置</span>
      </div>
      <div class="sc-base">
        <div class="sb-form">
          <div class="sf-main">
            <div class="sm-item">
              <div class="si-label">
                <span>姓名</span>
              </div>
              <div class="sel-input">
                <el-input type="email" placeholder="邮箱"></el-input>
              </div>
            </div>
            <div class="sm-item">
              <div class="si-label">
                <span>昵称</span>
              </div>
              <div class="sel-input">
                <el-input type="email" placeholder="昵称"></el-input>
              </div>
            </div>
            <div class="sm-item">
              <div class="si-label">
                <span>个人简介</span>
              </div>
              <div class="sel-input">
                <el-input type="textarea" :rows="5" placeholder="个人简介"></el-input>
              </div>
            </div>
            <div class="sm-item">
              <div class="si-label">
                <span>国家/地图</span>
              </div>
              <div class="sel-input">
                <el-select></el-select>
              </div>
            </div>
            <div class="sm-item">
              <div class="si-label">
                <span>所在省市</span>
              </div>
              <div class="sel-input">
                <el-select></el-select>
                <el-select style="margin-left: 10px"></el-select>
              </div>
            </div>
            <div class="sm-item">
              <div class="si-label">
                <span>街道地址</span>
              </div>
              <div class="sel-input">
                <el-input></el-input>
              </div>
            </div>
            <div class="sm-item">
              <div class="si-label">
                <span>联系电话</span>
              </div>
              <div class="sel-input">
                <el-input></el-input>
                <el-input style="margin-left: 10px"></el-input>
              </div>
            </div>
          </div>
          <div class="sf-avatar">
            <div class="sa-label">
              <span>头像</span>
            </div>
            <div class="sa-input">
              <img
                src="https://img.codingtalk.cn/5DmNTp51598446051256?x-oss-process=image/resize,m_fill,h_100,w_100"
                alt/>
              <el-button style="margin-left: 10px">上传头像</el-button>
            </div>
          </div>
        </div>
        <div class="sb-submit">
          <el-button type="primary">更新基本信息</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less">
.setting-notice {
  display: flex;
  padding: 10px 0;
  background: white;
  .sn-tab {
    width: 240px;
    border-right: 1px solid #f0f0f0;
    .t-item {
      display: flex;
      align-items: center;
      height: 40px;
      span {
        padding-left: 20px;
      }
      &-active {
        position: relative;
        background: #e6f7ff;
        color: #1890ff;
        &:after {
          position: absolute;
          top: 0;
          right: 0;
          content: "";
          width: 2px;
          height: 100%;
          background: #1890ff;
        }
      }
    }
  }
  .sn-content {
    padding-left: 20px;
    .sc-title {
      display: flex;
      align-items: center;
      height: 40px;
      span {
        font-size: 18px;
      }
    }
    .sc-base {
      .sb-form {
        display: flex;
        .sf-main {
          .sm-item {
            .si-label {
              display: flex;
              align-items: center;
              height: 30px;
            }
            .sel-input {
              display: flex;
              align-items: center;
            }
            &:not(:first-child) {
              margin-top: 20px;
            }
          }
        }
        .sf-avatar {
          padding-left: 20px;
          .sa-label {
            display: flex;
            align-items: center;
            height: 30px;
          }
          .sa-input {
            display: flex;
            align-items: center;
            img {
              width: 100px;
              height: 100px;
              border-radius: 50px;
            }
          }
        }
      }
      .sb-submit {
        padding-top: 20px;
      }
    }
  }
}
</style>
