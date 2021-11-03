<!--
 * @Author: 码上talk|RC
 * @Date: 2021-07-20 17:30:10
 * @LastEditTime: 2021-07-20 17:43:51
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/block/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="block">
    <div class="b-title">
      <div class="t-left">
        <span>{{ title }}</span>
      </div>
      <div class="b-right"></div>
    </div>
    <div class="b-box">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: ['title']
};
</script>

<style lang="less">
.block {
  .b-title {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    .t-left {
      position: relative;
      span {
        padding-left: 40px;
      }
      &:after {
        position: absolute;
        top: 50%;
        left: 20px;
        transform: translateY(-50%);
        content: "";
        width: 5px;
        height: 30px;
        background: #1890ff;
      }
    }
  }
  .b-box {
    padding: 20px;
  }
}
</style>
