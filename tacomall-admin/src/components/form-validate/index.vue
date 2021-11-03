<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-23 15:22:14
 * @LastEditTime: 2021-07-01 09:53:17
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/form-validate/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="form-validate">
    <div class="v_default" v-if="type === 'default'">
      <slot></slot>
    </div>
    <div class="v_step" v-else-if="type === 'step'">
      <div class="s-indicator" v-if="!stepConfig.noProgress">
        <el-steps :active="step.active + 1">
          <el-step
            :title="item.title"
            :description="item.description"
            :key="key"
            v-for="(item, key) in step.finalStep"
          ></el-step>
        </el-steps>
      </div>
      <div class="s-tip"></div>
      <div class="s-form" v-if="step.active < step.finalStep.length - 1">
        <template v-for="(item, key) in step.finalStep">
          <slot :name="`step-${key}`" v-if="key === step.active"></slot>
        </template>
      </div>
      <div class="s-result" v-else>
        <slot name="result"></slot>
      </div>
      <div class="s-btn" v-if="step.active < step.finalStep.length - 1 && !stepConfig.noProgress">
        <el-button @click.native="step.fn.stepChange(-1)" v-if="step.active > 0">上一步</el-button>
        <el-button
          type="primary"
          @click.native="step.fn.stepChange(1)"
        >{{ stepConfig.list[step.active].btnText ? stepConfig.list[step.active].btnText : '下一步' }}</el-button>
      </div>
    </div>
  </div>
</template>

<script>

import { formToolkit } from '@/model/form-toolkit';

export default {
  props: {
    type: {
      type: String,
      default () {
        return 'default';
      }
    },
    form: {
      type: Object,
      default () {
        return {};
      }
    },
    fill: {
      type: Function,
      default () {
        return null;
      }
    },
    validator: {
      type: Function,
      default () {
        return () => {
          return { ok: false, form: null };
        };
      }
    },
    forceValidField: {
      type: [Array, Function],
      default () {
        return [];
      }
    },
    stepConfig: {
      type: Object,
      default () {
        return {
          list: []
        };
      }
    }
  },
  data () {
    return {
      step: {
        active: 0,
        finalStep: this.stepConfig.list.concat([{ title: '完成' }]),
        fn: {
          _check: () => {
            return formToolkit.validate(this.form, { rqeuire: this.stepConfig.list[this.step.active]._validateField, forceValidField: Array.isArray(this.forceValidField) ? this.forceValidField : this.forceValidField() });
          },
          stepChange: (n) => {
            if (n < 0) {
              this.step.active = this.step.active + n;
              return;
            }
            if (this.stepConfig.noValidate) {
              if (this.step.active < this.step.finalStep.length - 2) {
                this.step.active = this.step.active + n;
                return;
              }
              this.$emit('ok', {
                rawForm: this.form,
                callback: () => {
                  this.step.active = this.step.finalStep.length - 1;
                }
              });
              return;
            }
            const { ok, form } = this.step.fn._check();
            if (!ok) {
              this.$message({
                message: '请校验输入信息',
                type: 'warning'
              });
              return;
            }
            if (this.step.active < this.step.finalStep.length - 2) {
              const _nextCallback = this.stepConfig.list[this.step.active]._nextCallback;
              if (_nextCallback) {
                _nextCallback().then(() => {
                  this.step.active = this.step.active + n;
                });
                return;
              }
              this.step.active = this.step.active + n;
              return;
            }
            this.$emit('ok', {
              form,
              callback: () => {
                this.step.active = this.step.finalStep.length - 1;
              }
            });
          }
        }
      }
    };
  },
  methods: {
    init () {
      (Array.isArray(this.forceValidField) ? this.forceValidField : this.forceValidField()).forEach(i => {
        this.form[i].require = true;
      });
      this.fill && this.fill((entity, json) => {
        entity.formUtil().fill(this.form, json);
      });
    },
    update () {
      this.init();
    },
    next () {
      this.step.fn.stepChange(1);
    },
    validate () {
      return new Promise((resolve) => {
        const { ok, form } = this.validator(this.form);
        resolve({ ok, form });
      });
    }
  },
  created () {
    this.init();
  }
};
</script>

<style lang="less">
.form-validate {
  .v_default {
  }
  .v_step {
    .s-indicator {
      display: flex;
      align-items: center;
      height: 200px;
      .el-steps {
        width: 100%;
      }
    }
    .s-btn {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 200px;
    }
  }
}
</style>
