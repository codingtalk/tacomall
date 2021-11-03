<!--
 * @Author: 码上talk|RC
 * @Date: 2020-12-16 14:25:06
 * @LastEditTime: 2020-12-16 14:50:33
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /vuejs-pc-web/src/components/dropdown-box/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="dropdown-box">
    <div class="db-slot" :style="styles">
      <slot></slot>
    </div>
    <div class="db-expand db-expand-hover" v-if="type === 'hover'">
      <slot name="expand"></slot>
    </div>
    <div class="db-expand db-expand-click" v-if="type === 'click'"></div>
  </div>
</template>

<script>
export default {
  props: ['type', 'styles']
};
</script>

<style lang="less">
.dropdown-box {
    position: relative;
    width: 100%;
    height: 100%;
    .db-slot {
        width: 100%;
        height: 100%;
    }
    .db-expand {
        position: absolute;
        top: 100%;
        left: 0;
        display: none;
        z-index: 999;
    }
    &:hover {
        .db-expand {
            display: block;
        }
    }
}
</style>
