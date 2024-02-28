<template>
  <div class="page product_add">
    <Submit ref-key="submit" :ref="storeRef">
      <cvt-form ref-key="cvtForm" :ref="storeRef" @submit="add" />
    </Submit>
  </div>
</template>

<script lang="jsx" setup>
import { onMounted } from 'vue';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { Product } from '@/entity';
import { message, Modal } from "ant-design-vue";
import Submit from '@/components/submit/index.jsx';

function init() {
  getRef('cvtForm')?.init('Product', 'add', {
    renderHook: {
    }
  })
}

function add(e) {
  const { form, callback } = e;
  Modal.confirm({
    title: '创建提示',
    content: '是否创建产品？',
    onOk: () => {
      Product.sendApi('add', { params: {}, body: form }).then(res => {
        const { status, data } = res;
        if (status) {
          callback()
        }
      })
    }
  });
}

onMounted(() => {
  init();
})
</script>

<style lang="less">

.product_add {}
</style>