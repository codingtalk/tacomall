<template>
    <div class="org_staff_select">
        <Select size="middle" mode="multiple" style="width: 200px" :default-active-first-option="false" :show-arrow="false"
            :filter-option="false" :not-found-content="null" :options="options" @search="search"
            @change="change"></Select>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import {
    Select
} from "ant-design-vue";
import { OrgStaff } from '@/entity';

const props = defineProps({
    formRes: {
        type: Function
    },
    opt: {
        type: Object
    },
    hook: {
        type: Function
    }
})

const options = ref([]);
const fetching = ref(false);

function search(e) {
    let body = {};
    fetching.value = true;
    if (props.hook) {
        const { query } = props.hook(e, props.formRes());
        body = Object.assign(body, { query });
    }
    OrgStaff.sendApi('staffPage', { params: {}, body }).then(res => {
        const { status, data } = res;
        if (status) {
            options.value = data.map(i => ({ label: i.nickname, value: i.id }));
        }
    }).finally(() => {
        fetching.value = false;
    })
}

function change(e) {
    if (props.opt) {
        props.opt.value = e;
    }
}
</script>