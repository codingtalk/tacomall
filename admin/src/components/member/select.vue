<template>
  <div class="member_select">
    <div class="select_input">
      <Select show-search placeholder="搜索会员" style="width: 200px" :default-active-first-option="false"
        :show-arrow="false" :filter-option="false" :not-found-content="null" :options="options" @search="search"
        @change="change">
        <template v-if="fetching" #notFoundContent>
          <a-spin size="small" />
        </template>
      </Select>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { Member } from '@/entity';
import { Select } from 'ant-design-vue'

const props = defineProps({
})
const emits = defineEmits(['change']);
const options = ref([]);
const fetching = ref(false);

function search() {
  fetching.value = true;
  Member.sendApi('page', { params: {}, body: {} }).then(res => {
    const { status, data } = res;
    if (status) {
      options.value = data.map(i => ({ label: i.nickname, value: i.id }));
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

.member_select {}
</style>