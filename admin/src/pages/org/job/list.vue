<template>
  <div class="org_job_list">
    <div class="list_brief"></div>
    <div class="list_page-loader">
      <cvt-page-loader :config="opt.config" :query="opt.query" :column="opt.column"></cvt-page-loader>
    </div>
  </div>
</template>

<script lang="jsx" setup>
import { reactive } from "vue";
import {
  Button
} from "ant-design-vue";

const opt = reactive({
  config: { entity: "OrgJob", action: "jobPage" },
  query: [{ label: "关键字" }],
  column: [
    { label: "序号", key: "id" },
    { label: "名称", key: "name" },
    { label: "人数", key: "" },
    { label: "状态", key: "" },
    {
      label: "操作",
      key: "operation",
      width: 250,
      fix: "right",
      render: (row) => {
        return <Button type="primary">查看</Button>;
      },
    },
  ],
});
</script>