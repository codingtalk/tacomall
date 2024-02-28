<template>
  <div class="form-order_select">
    <div class="select_input">
      <a-select show-search placeholder="搜索订单" style="width: 200px" :default-active-first-option="false"
        :show-arrow="false" :filter-option="false" :not-found-content="null" :options="options" @search="search"
        @change="change">
        <template v-if="fetching" #notFoundContent>
          <a-spin size="small" />
        </template>
      </a-select>
    </div>
    <div class="select_info">
      <div class="info_item">
        <div class="item_label">SN: </div>
        <div class="item_value">230564200153</div>
      </div>
      <div class="info_item">
        <div class="item_label">客户: </div>
        <div class="item_value">张三</div>
      </div>
      <div class="info_item">
        <div class="item_label">金额: </div>
        <div class="item_value">￥36832.00</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { FormOrder } from '@/entity';

const props = defineProps({
})
const emits = defineEmits(['change']);
const options = ref([]);
const fetching = ref(false);

function search() {
  fetching.value = true;
  FormOrder.sendApi('formPage', { params: {}, body: {} }).then(res => {
    const { status, data } = res;
    if (status) {
      options.value = data.map(i => ({ label: i.sn, value: i.id }));
    }
  }).finally(() => {
    fetching.value = false;
  })
}

function change(e) {
  emits('change', e);
}
</script>

<style lang="less">

.form-order_select {
  .select_info {
    display: inline-block;
    padding: 10px 50px 10px 10px;
    margin-top: 5px;
    background: @background-color-base ;

    .info_item {
      .flex();
      font-size: @font-size-small ;
      color: @text-color;

      .item_label {
        width: 50px;
      }
    }
  }
}
</style>