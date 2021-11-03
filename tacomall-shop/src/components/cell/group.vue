<!--
 * @Author: 码上talk|RC
 * @Date: 2021-06-25 11:16:46
 * @LastEditTime: 2021-07-13 12:41:01
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/cell/group.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="cell-group">
    <div class="g-title"></div>
    <div class="g-content">
      <slot></slot>
    </div>
  </div>
</template>

<style lang="less">
.cell-group {
  .g-content {
    .cell-item {
      &:not(:first-child) {
        margin-top: 20px;
      }
    }
  }
}
</style>
