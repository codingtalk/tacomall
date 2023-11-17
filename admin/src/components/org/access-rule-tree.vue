<template>
    <div class="org_access-rule_tree">
        <Tree v-model:checkedKeys="checkedKeys" checkable :tree-data="treeData">
        </Tree>
    </div>
</template>

<script setup>
import { ref, onBeforeMount, computed, toRefs, watch } from "vue";
import { Tree } from "ant-design-vue";
import { OrgAccessRule } from '@/entity';

const props = defineProps({
    checkedIds: {
        type: Array,
        default() {
            return [];
        }
    }
});
const emits = defineEmits(['change']);
const plainData = ref([]);
const treeData = ref([]);
const checkedKeys = ref([]);

watch(() => checkedKeys.value, (val) => {
    emits('change', plainData.value.filter(x => val.includes(x.key)).map(i => i._v?.id))

}, {
    deep: true
})

onBeforeMount(() => {
    OrgAccessRule.sendApi('accessRuleList', { params: {}, body: {} }).then(res => {
        const { status, data } = res;
        if (status) {
            const toTree = (t, p) => {
                for (let i in data) {
                    const { id, pid, name, } = data[i];
                    const key = `${p}-${id}`;
                    const v = {
                        title: name,
                        key,
                        children: [],
                        _v: data[i]
                    }
                    if (p === pid) {
                        t.push(v)
                        plainData.value.push(v);
                        if (props.checkedIds.includes(id)) {
                            checkedKeys.value.push(key);
                        }
                        if (data.map(j => j.pid).includes(id)) {
                            toTree(v.children, id);
                        }
                    }
                }
            }
            toTree(treeData.value, 0);
        }
    })
})

</script>