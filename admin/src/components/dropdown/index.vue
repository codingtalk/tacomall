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
  props: ["type", "styles"],
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
    border-radius: 5px;
    overflow: hidden;
    box-shadow: @shadow-down;
  }
  &:hover {
    .db-expand {
      display: block;
    }
  }
}
</style>
