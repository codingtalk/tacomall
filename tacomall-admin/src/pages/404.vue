<!--
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 14:31:51
 * @LastEditTime: 2021-01-15 16:15:12
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /vuejs-pc-web/src/pages/404.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="_404">
    <div class="_4-content">
      <img src="/image/404.png" alt />
      <h1>404 Page Not Found</h1>
      <div class="btn" @click="rep('/login')">
        <span>返回登录页</span>
      </div>
    </div>
  </div>
</template>

<style lang="less">
._404 {
    display: flex;
    justify-content: center;
    ._4-content {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 300px;
        padding: 100px 0;
        img {
            width: 100%;
        }
        h1 {
            padding-top: 20px;
            white-space: nowrap;
        }
        .btn {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100px;
            height: 30px;
            background: #1890ff;
            margin-top: 50px;
            span {
                color: white;
            }
            &:hover {
                cursor: pointer;
            }
        }
    }
}
</style>
