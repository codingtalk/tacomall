<template>
    <div class="approve_type_list">
        <div class="list_page-loader">
            <cvt-page-loader :config="opt.config" :query="opt.query" :column="opt.column"></cvt-page-loader>
        </div>
    </div>
</template>

<script lang="jsx" setup>
import { reactive, defineComponent } from "vue";
import { message, Button } from "ant-design-vue";
import { CvtForm, Popup} from 'codingtalk-vue-toolkit';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { ApproveType } from "@/entity";
import OrgStaffSelect from '@/components/org/staff/select.vue';
import OrgDeptSelect from '@/components/org/dept/select.vue';
import { px2vw } from '@/utils/browser';

const opt = reactive({
    config: { entity: "ApproveType", action: "typePage" },
    query: [{ label: "关键字" }],
    column: [
        { label: "编号", key: "sn" },
        { label: "名称", key: "name" },
        { label: "审批节点", key: "" },
        {
            label: "操作",
            key: "operation",
            width: 250,
            fix: "right",
            render: ({ row }) => {
                const { id } = row;
                return <Button type="primary" onClick={() => {
                    update(row);
                }}>编辑</Button>;
            },
        },
    ],
});


function update(row) {
    const { open, ready, confirm } = Popup();
    const { id } = row;
    ready(() => {
        getRef('cvtForm').init('ApproveType', 'update', {
            renderHook: {
                orgDeptIds: (v, valid) => {
                    return <OrgDeptSelect />
                },
                orgStaffIds: (v, valid) => {
                    return <OrgStaffSelect />
                }
            }
        }, () => {
            return new Promise((resolve) => {
                ApproveType.sendApi('typeInfo', { params: { id }, body: {} }, {}).then(res => {
                    const { status, data } = res;
                    if (status) {
                        resolve(data);
                    }
                })
            })
        });
    });
    confirm((ok) => {
        getRef('cvtForm').submit().then(form => {
            ApproveType.sendApi('typeUpdate', { params: { id }, body: form }, { errorTip: '审批类型更新失败' }).then(res => {
                const { status, data } = res;
                if (status) {
                    message.success("更新成功");
                    getRef('cvtPageLoader').init();
                    ok();
                }
            })
        })
    })
    open('dialog', defineComponent({
        setup() {
            return () => <CvtForm ref-key="cvtForm" ref={storeRef} style={{ width: px2vw(1000) }} />
        }
    }), {
        title: '更新审批类型'
    })
}
</script>