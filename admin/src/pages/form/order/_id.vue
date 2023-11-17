<template>
    <div class="page publish">
        <Detail>
            <template #toolbar>
                <div class="detail_header">
                    <div class="header_title">
                        <div class="title_left">
                            <span>单号: 65320125536</span>
                        </div>
                        <div class="title_btn">
                            <a-button type="primary" size="large" @click="submit" v-if="getCtXVal('status') === 'save'">提交审核</a-button>
                        </div>
                    </div>
                    <div class="header_content">
                        <div class="content_left"></div>
                        <div class="content_right">
                            <div class="right_item">
                                <span>状态</span>
                                <em :style="{color: getCtXVal()?.toEnum('status')?.color}">{{getCtXVal()?.toEnum('status')?.label}}</em>
                            </div>
                            <div class="right_item">
                                <span>订单金额</span>
                                <em>￥235.41</em>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
            <cvt-detail ref-key="cvtDetail" :ref="storeRef" />
        </Detail>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { FormOrder, Approve } from '@/entity';
import Detail from '@/components/detail/index.vue';
import { message, Modal } from "ant-design-vue";
import getDetail from '@/composables/getDetail';

const { id, getCtXVal, load } = getDetail('FormOrder', 'formInfo');

function init() {
    getRef('cvtDetail')?.init('FormOrder', 'info', load, {
    })
}

function submit() {
    Modal.confirm({
        title: '提交审核',
        content: '是否提交当前上刊审核',
        onOk: () => {
            Approve.sendApi('add', { params: { typeId: 1, relateId: id }, body: {} }).then(res => {
                const { status, data } = res;
                if (status) {
                    message.success('提交成功');
                    getRef('cvtDetail')?.refresh(load);
                }
            })
        }
    });
}

onMounted(() => {
    init();
})
</script>