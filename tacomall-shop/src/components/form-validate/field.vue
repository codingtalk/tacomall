<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-23 15:22:25
 * @LastEditTime: 2021-07-01 08:57:10
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/form-validate/field.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="form-validate-field">
    <div class="f-label" v-if="label">
      <em v-if="field && field.require">*</em>
      <span>{{ label }}</span>
    </div>
    <div class="f-slot">
      <slot></slot>
      <div class="s-tip" v-if="!isValid && label">
        <p>内容不能空</p>
      </div>
    </div>
  </div>
</template>

<script>
/*eslint-disable */
export default {
  props: ['label', 'field'],
  data () {
    return {
      isValid: true
    }
  },
  watch: {
    field: {
      handler: function (e) {
        if (!e) {
          return;
        }
        const { value, _validator } = e
        this.isValid = true
        if ((!value && e.require) || !_validator.ok) {
          this.isValid = false
        }
      },
      deep: true
    }
  }
}
</script>

<style lang="less">
.form-validate-field {
  display: flex;
  align-items: center;
  margin-bottom: 30px;
  .f-label {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    width: 140px;
    em {
      padding-right: 5px;
      font-size: 14px;
      color: #ed4014;
    }
    span {
      padding-right: 11px;
    }
  }
  .f-slot {
    position: relative;
    flex: 1;
    width: 100%;
    .s-tip {
      position: absolute;
      bottom: -25px;
      left: 0;
      color: #ed4014;
      p {
        margin: 0;
      }
    }
  }
}
</style>