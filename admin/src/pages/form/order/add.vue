<template>
  <div class="page publish_add">
    <Submit ref-key="submit" :ref="storeRef" show-toolbar @submit="() => { add() }">
      <cvt-form ref-key="cvtForm" :ref="storeRef" />
    </Submit>
  </div>
</template>

<script lang="jsx" setup>
import { onMounted } from 'vue';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { FormOrder } from '@/entity';
import { message, Modal } from "ant-design-vue";
import Submit from '@/components/submit/index.jsx';
import FormOrderSelect from '@/components/form/order/select.vue';

function init() {
  getRef('cvtForm')?.init('FormOrder', 'formAdd', {
    renderHook: {
      formOrderId: (v, valid) => {
        return <FormOrderSelect onChange={e => {
          v.value = e;
        }} />
      },
    }
  })
}

function add() {
  Modal.confirm({
    title: '创建提示',
    content: '是否创建该上刊？',
    onOk: () => {
      getRef('cvtForm').submit().then(form => {
        FormOrder.sendApi('add', { params: {}, body: form }).then(res => {
          const { status, data } = res;
          if (status) {
            message.success('创建成功');
            getRef('submit').next();
          }
        })
      }).catch(e => {
        message.error(e);
      })
    }
  });
}

onMounted(() => {
  init();
})
</script>