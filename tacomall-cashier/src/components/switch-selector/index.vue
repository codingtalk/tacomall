<!--
 * @Author: 码上talk|RC
 * @Date: 2021-06-11 08:41:44
 * @LastEditTime: 2021-09-20 00:12:35
 * @LastEditTime: 2021-08-06 20:54:00
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/switch-selector/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <el-radio-group v-model="value" v-if="mode === 1">
    <el-radio
      :label="item.value"
      :key="key"
      v-for="(item, key) in $_.get(config, 'selectorList', [])"
    >{{ item.label }}</el-radio>
  </el-radio-group>
  <el-switch
    v-model="value"
    :disabled="disabled"
    :active-text="$_.get(config, 'selectorList[1].label', '是')"
    :inactive-text="$_.get(config, 'selectorList[0].label', '否')"
    v-else
  ></el-switch>
</template>

<script>
export default {
  props: {
    disabled: {
      type: Boolean,
      default () {
        return false;
      }
    },
    mode: {
      type: Number,
      default () {
        return 1;
      }
    },
    tip: {
      type: String
    },
    config: {
      type: Object
    }
  },
  data () {
    return {
      lock: false,
      value: this.config.value
    };
  },
  watch: {
    'config.value': function (e) {
      this.lock = true;
      this.value = e;
      this.$nextTick(() => {
        this.lock = false;
      });
    },
    value: function (val, valOld) {
      if (this.config.noConfirm) {
        this.$emit('change', { value: val });
        return;
      }
      if (this.lock) {
        return;
      }
      this.$confirm(this.tip, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            this.$emit('change', {
              value: val,
              ok: done,
              error: () => {
                this.lock = true;
                this.value = valOld;
                this.$nextTick(() => {
                  this.lock = false;
                  done();
                });
              }
            });
          } else {
            this.lock = true;
            this.value = valOld;
            this.$nextTick(() => {
              this.lock = false;
              done();
            });
          }
        }
      }).then(() => {
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    }
  }
};
</script>
