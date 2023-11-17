<template>
  <div class="publish_list">
      <div class="list_brief"></div>
      <div class="list_page-loader">
          <cvt-page-loader title="产品列表" :config="opt.config" :query="opt.query" :column="opt.column" @add="go('/product/add')"></cvt-page-loader>
      </div>
  </div>
</template>

<script lang="jsx" setup>
import { reactive } from "vue";
import { go } from "@/utils/navigator";
import { message, Button } from "ant-design-vue";

const opt = reactive({
  config: { entity: "Product", action: "page" },
  query: [{ label: "关键字" }],
  column: [
      { label: "编号", key: "sn" },
      { label: "名称", key: "name" },
      { label: "SKU", key: "" },
      { label: "状态", key: "" },
      {
          label: "操作",
          key: "operation",
          width: 250,
          fix: "right",
          render: ({ row }) => {
              const { id } = row;
              return <Button type="primary" onClick={() => { go(`/product/${id}`) }}>查看详情</Button>;
          },
      },
  ],
});
</script>