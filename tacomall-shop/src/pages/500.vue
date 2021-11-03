<!--
 * @Author: 码上talk|RC
 * @Date: 2021-01-15 15:47:52
 * @LastEditTime: 2021-01-15 16:18:32
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /vuejs-pc-web/src/pages/500.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="_500">
    <div class="_5-content">
      <img src="/image/500.png" alt />
      <h1>500 Something Exceptional Had Happened In Server</h1>
      <div class="btn" @click="rep('/')">
        <span>返回首页</span>
      </div>
      <div class="guide">
        <p></p>
      </div>
    </div>
  </div>
</template>

<style lang="less">
._500 {
    display: flex;
    justify-content: center;
    ._5-content {
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
