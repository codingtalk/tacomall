<!--
 * @Author: 码上talk|RC
 * @Date: 2021-05-27 21:07:01
 * @LastEditTime: 2021-10-14 16:24:30
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/components/tm/access-rule-tree/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="tm-access-rule-tree">
    <el-tree
      :props="props"
      :data="data"
      node-key="id"
      :default-checked-keys="checkValue"
      show-checkbox
      @check-change="checkChangeHandler"
    ></el-tree>
  </div>
</template>

<script>

import { model } from '@/model';
const { TmAccessRule } = model.collection;

function _toTree (data) {
  const result = [];
  if (!Array.isArray(data)) {
    return result;
  }
  data.forEach(item => {
    delete item.children;
  });
  const map = {};
  data.forEach(item => {
    map[item.id] = item;
  });
  data.forEach(item => {
    const parent = map[item.pid];
    if (parent) {
      (parent.children || (parent.children = [])).push(item);
    } else {
      result.push(item);
    }
  });
  return result;
}

function _loopGetCheckIds (data) {
  const checkIds = [];
  const { id, children } = data;
  if (children) {
    children.forEach(i => {
      _loopGetCheckIds(i).forEach(j => {
        checkIds.push(j);
      });
    });
    return checkIds;
  }
  checkIds.push(id);
  return checkIds;
}
export default {
  props: {
    checkValue: {
      type: Array,
      default () {
        return [];
      }
    },
    ignoreValue: {
      type: Array,
      default () {
        return [];
      }
    }
  },
  data () {
    return {
      props: {
        label: 'name',
        children: 'children'
      },
      data: [],
      totalIds: [],
      checkIds: []
    };
  },
  watch: {
    checkIds: {
      handler: function (e) {
        this.$emit('change', {
          totalIds: this.totalIds,
          checkIds: e,
          ignoreIds: this.totalIds.filter(i => {
            if (e.includes(i)) {
              return false;
            }
            return true;
          })
        });
      },
      deep: true
    },
    data: {
      handler: function (e) {
        e.map(i => _loopGetCheckIds(i)).forEach(i => {
          this.totalIds = this.totalIds.concat(i);
        });
        if (this.checkValue.length) {
          this.checkIds = this.checkValue;
        }
        if (this.ignoreValue.length) {
          this.checkIds = this.totalIds.filter(i => {
            if (this.ignoreValue.includes(i)) {
              return false;
            }
            return false;
          });
        }
      },
      deep: true
    }
  },
  methods: {
    _loadAccessRuleList () {
      TmAccessRule.sendApi('accessRuleList', { params: {}, data: {} }).then(res => {
        const { status, data } = res;
        if (status) {
          this.data = _toTree(data);
        } else {
          this.$message({
            message: '获取权限错误',
            type: 'warning'
          });
        }
      });
    },
    checkChangeHandler (data, checked, indeterminate) {
      if (!checked && indeterminate) {
        return;
      }
      if (checked) {
        _loopGetCheckIds(data).forEach(i => {
          this.checkIds.push(i);
        });
      } else {
        this.checkIds = this.checkIds.filter(i => {
          if (_loopGetCheckIds(data).includes(i)) {
            return false;
          }
          return true;
        });
      }
    }
  },
  created () {
    this._loadAccessRuleList();
  }
};
</script>
