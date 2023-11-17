<template>
    <div class="org_staff_list">
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
    config: { entity: "OrgStaff", action: 'staffPage' },
    query: [{ label: "关键字" }],
    column: [
        { label: "序号", key: "id" },
        { label: "账号", key: "name" },
        { label: "昵称", key: "name" },
        {
            label: "头像",
            key: "avatar",
            render: (row) => {
                return <img src="/src/assets/logo.png" />;
            },
        },
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