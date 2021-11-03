<!--
 * @Author: 码上talk|RC
 * @Date: 2021-05-03 21:30:17
 * @LastEditTime: 2021-10-13 10:52:39
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/options-selector/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="options-selector">
    <el-select
      v-model="value"
      :placeholder="placeholder"
      size="small"
      filterable
      clearable
      :remote="mode === 4"
      @change="changeHander"
      :remote-method="remoteMethod"
      :loading="loading"
      v-if="[1, 2, 4].includes(mode)"
    >
      <el-option v-for="(item, key) in options" :key="key" :label="item.label" :value="item.value">
        <span style="float: left">{{ item.label }}</span>
        <i
          class="iconfont icon-shibai1"
          style="float: right; color: #8492a6; font-size: 20px"
          @click.prevent="deleteOption(item)"
          v-if="config.enableDelete"
        ></i>
      </el-option>
    </el-select>
    <el-cascader
      :props="props"
      size="small"
      filterable
      clearable
      @change="changeHander"
      :placeholder="value"
      style="width:100%;"
      v-if="mode === 3"
    ></el-cascader>
    <el-input size="small" :placeholder="placeholder" v-model="value" v-if="mode === 5"></el-input>
  </div>
</template>

<script>
import collection from '@/model/collection';
import { Request } from '@/model/request';

export default {
  props: {
    mode: {
      type: Number,
      default () {
        return 1;
      }
    },
    placeholder: {
      type: String,
      defualt () {
        return '请选择';
      }
    },
    config: {
      type: Object,
      default () {
        return {
          autoLoad: false,
          enableDelete: false,
          isRaw: false,
          entity: '',
          type: '',
          remote: false,
          options: null,
          value: ''
        };
      }
    }
  },
  data () {
    return {
      request: new Request(),
      debounceQuery: this.$_.debounce(this.query, 500),
      value: this.config.value,
      options: [],
      loading: false,
      props: {
        lazy: true,
        lazyLoad: (node, resolve) => {
          const { level, data } = node;
          new Request().do('open', 'enum', 'lst', { params: { key: this.config.key }, data: { query: { pId: level === 0 ? 0 : data.value.id } } }).then(res => {
            const { status, data } = res;
            if (status) {
              const nodes = data.map(i => {
                const { id, name, level, isHasChildren } = i;
                let leaf = false;
                if (this.config.key === 'goodsSpecCategory' || this.config.key === 'goodsSeries') {
                  leaf = true;
                }
                if (this.config.key === 'sysRegion') {
                  leaf = this.config.endValue ? level === this.config.endValue : (level === 'district' || level === 'street');
                }
                if (this.config.key === 'goodsCategory') {
                  leaf = !isHasChildren;
                }
                return {
                  value: { id, _raw: i },
                  label: name,
                  leaf
                };
              });
              resolve(nodes);
            }
          });
        }
      }
    };
  },
  watch: {
    mode: function () {
      this.value = '';
    },
    value: {
      handler: function (e) {
        if (this.mode === 5) {
          this.debounceQuery();
        }
      },
      deep: true
    },
    'config.value': function (e) {
      this.value = e;
    }
  },
  methods: {
    init () {
      if (this.mode === 1) {
        if (this.config.req) {
          this.config.req && this.config.req((options) => {
            this.options = options;
          });
          return;
        }
        this.options = (this.config.options ? this.config.options : collection[this.config.entity]._options[this.config.type]).filter(i => {
          if (this.config.hidden && this.config.hidden.includes(i.value)) {
            return false;
          }
          return true;
        });
      }
      if (this.mode === 2) {
        this.request.do('open', 'enum', 'lst', { params: { key: this.config.key }, data: {} }).then(res => {
          const { status, data } = res;
          if (status) {
            this.options = data.map(i => {
              return {
                value: i.id,
                label: i.name,
                _raw: i
              };
            });
          }
        });
      }
    },
    reload () {
      this.init();
    },
    remoteMethod (e) {
      this.loading = true;
      this.config.req && this.config.req(e, (options) => {
        this.loading = false;
        this.options = options;
      });
    },
    query () {
      this.config.req && this.config.req(this.value);
    },
    changeHander (e) {
      let _raw = null;
      for (let i = 0; i < this.options.length; i++) {
        if (e === this.options[i].value) {
          _raw = this.options[i]._raw;
        }
      }
      this.$emit('change', e, _raw || {});
    },
    deleteOption (item) {
      this.$confirm(`${this.config.deleteTip}, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$emit('delete', {
          option: item._raw,
          ok: () => {
            this.reload();
          }
        });
      }).catch(() => {
      });
    },
    reset () {
      this.value = '';
    }
  },
  created () {
    this.init();
  }
};
</script>
