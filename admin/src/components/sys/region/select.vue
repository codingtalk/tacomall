<template>
  <div class="sys-region_select">
    <div class="select_input">
      <Cascader :options="options" :load-data="loadData" placeholder="选取地址" style="width: 200px" change-on-select
        @change="change">
      </Cascader>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { SysRegion } from '@/entity';
import { Cascader } from 'ant-design-vue'

const props = defineProps({
})
const emits = defineEmits(['change']);
const options = ref([]);

async function search(pId = 0, isLeaf = undefined) {
  const { status, data } = await SysRegion.sendApi('regionPage', { params: {}, body: { query: { pId } } })
  return status ? data.map(i => ({ label: i.name, value: i.id, isLeaf, type: i.type })) : []
}

async function init() {
  options.value = await search(1, false)
}
async function loadData(selectedOptions) {
  const targetOption = selectedOptions[selectedOptions.length - 1]
  const { value, type } = targetOption
  targetOption.children = await search(value, type === 'province' ? false : undefined)
  targetOption.loading = false
}

function change(e) {
  emits('change', e);
}

onMounted(() => {
  init()
})
</script>

<style lang="less">
@import "../../../assets/style/var.less";
@import "../../../assets/style/mixins.less";

.sys-region_select {}
</style>