<!--
 * @Author: 码上talk|RC
 * @Date: 2021-06-15 10:01:56
 * @LastEditTime: 2021-09-02 15:57:24
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/elastic-table/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="elastic-table">
    <el-table
      :data="tableData"
      :max-height="tableHeight"
      :stripe="true"
      border
      style="width: 100%"
      @selection-change="$emit('selection-change', $event)"
    >
      <template v-if="$slots.default">
        <slot></slot>
      </template>
      <template v-else>
        <el-table-column
          type="selection"
          width="55"
          :selectable="(row, index) => {
              return typeof selectable === 'function' ? selectable(row) : true;
              }"
          v-if="selectable"
        ></el-table-column>
        <template :conf="item" v-for="(item, key) in column">
          <template v-if="item.columns">
            <column
              :key="`customize-${key}-${key1}`"
              :conf="item1"
              v-for="(item1, key1) in item.columns()"
            ></column>
          </template>
          <column :key="key" :conf="item" v-else></column>
        </template>
        <template slot="empty" v-if="$slots.empty">
          <slot name="empty"></slot>
        </template>
      </template>
    </el-table>
  </div>
</template>

<script>
import column from './column';
export default {
  components: {
    column
  },
  props: {
    tableData: {
      type: [Object, Array],
      default () {
        return [];
      }
    },
    column: {
      type: [Object, Array],
      default () {
        return [];
      }
    },
    selectable: [Function, Boolean],
    tableHeight: [Number]
  },
  data () {
    return {
    };
  },

  methods: {

  }
};
</script>

<style lang="less">
.elastic-table {
  width: 100%;
}
</style>
