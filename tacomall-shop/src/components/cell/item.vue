<!--
 * @Author: 码上talk|RC
 * @Date: 2021-06-25 11:16:51
 * @LastEditTime: 2021-07-13 12:41:15
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/cell/item.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="cell-item">
    <div class="i-label">
      <span>{{ label }}</span>
    </div>
    <div class="i-value">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: ['label']
};
</script>

<style lang="less">
.cell-item {
  display: flex;
  align-items: center;
  .i-label {
    width: 150px;
    flex-shrink: 0;
    text-align: right;
  }
}
</style>
