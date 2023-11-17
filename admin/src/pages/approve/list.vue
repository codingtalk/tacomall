<template>
  <div class="approve_list">
    <div class="list_tabs">
      <a-tabs>
        <a-tab-pane :tab="item.name" :key="key" v-for="(item, key) in getVal('approveTypeList')"></a-tab-pane>
      </a-tabs>
    </div>
    <div class="list_page-loader">
      <cvt-page-loader :config="opt.config" :query="opt.query" :column="opt.column"></cvt-page-loader>
    </div>
  </div>
</template>

<script lang="jsx" setup>
import { reactive } from "vue";
import { message, Button, DatePicker } from "ant-design-vue";
import { go } from '@/utils/navigator'
import page from '@/composables/page';

const {
  getVal,
  load,
} = page('approveList');
const opt = reactive({
  config: { entity: "Approve", action: "page", enableRemoteConfig: 'Approve' },
  query: [{ label: "关键字", key: 'keyword' }, {
    label: "创建时间", key: 'createTime', render: ({ key, update }) => {
      return <DatePicker/>
    }
  }],
  column: [
    { label: "编号", key: "sn" },
    { label: "类型", key: "mobile" },
    {
      label: "审核状态",
      key: "status",
      render: ({ row }) => {
        return <span>{row.toEnum('stauts')?.label}</span>
      }
    },
    { label: "订单编号", key: "" },
    { label: "客户", key: "" },
    {
      label: "操作",
      key: "operation",
      width: 250,
      fix: "right",
      render: ({ row }) => {
        const { id } = row;
        return <Button type="primary" onClick={() => {
          go(`/approve/${id}`)
        }}>查看</Button>;
      },
    },
  ],
});
</script>

<style lang="less">
.approve_list {
  .list_tabs {
    padding: 20px;
    background: white;
  }
}
</style>