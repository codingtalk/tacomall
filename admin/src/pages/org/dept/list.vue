<template>
  <div class="org_dept_list">
    <div class="list_brief"></div>
    <div class="list_page-loader">
      <cvt-page-loader ref-key="cvtPageLoader" :ref="storeRef" :config="opt.config" :query="opt.query"
        :column="opt.column" @add="add"></cvt-page-loader>
    </div>
  </div>
</template>

<script lang="jsx" setup>
import { reactive, defineComponent, onBeforeMount } from "vue";
import { CvtForm, Popup } from 'codingtalk-vue-toolkit';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { OrgDept } from "@/entity";
import { message, Button } from "ant-design-vue";
import OrgAccessRuleTree from '@/components/org/access-rule-tree.vue';

const opt = reactive({
  config: { entity: "OrgDept", action: "deptPage" },
  query: [{ label: "关键字" }],
  column: [
    { label: "序号", key: "id" },
    { label: "名称", key: "name" },
    { label: "人数", key: "" },
    { label: "状态", key: "" },
    {
      label: "操作",
      key: "operation",
      width: 250,
      fix: "right",
      render: ({ row }) => {
        return <>
          <Button type="primary" onClick={() => { update(row) }}>更新</Button>
          <Button type="primary" style={{ marginLeft: '10px' }} onClick={() => { updateAccessRule(row) }}>权限</Button>
        </>;
      },
    },
  ],
});

function add() {
  const { open, ready, confirm } = Popup();
  ready(() => {
    getRef('cvtForm').init('OrgDept', 'add', {});
  });
  confirm((ok) => {
    getRef('cvtForm').submit().then(form => {
      OrgDept.sendApi('deptAdd', { params: {}, body: form }, { errorTip: '部门添加失败' }).then(res => {
        const { status, data } = res;
        if (status) {
          message.success("添加成功");
          getRef('cvtPageLoader').init();
          ok();
        }
      })
    })
  })
  open('dialog', defineComponent({
    setup() {
      return () => <CvtForm ref-key="cvtForm" ref={storeRef} />
    }
  }), {
    title: '添加部门'
  })
}

function update(row) {
  const { open, ready, confirm } = Popup();
  const { id } = row;
  ready(() => {
    getRef('cvtForm').init('OrgDept', 'update', {}, () => {
      return new Promise((resolve) => {
        OrgDept.sendApi('deptInfo', { params: { id }, body: {} }, {}).then(res => {
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
      OrgDept.sendApi('deptUpdate', { params: { id }, body: form }, { errorTip: '部门更新失败' }).then(res => {
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
      return () => <CvtForm ref-key="cvtForm" ref={storeRef} />
    }
  }), {
    title: '更新部门'
  })
}

function updateAccessRule(row) {
  const { open, ready, confirm } = Popup();
  const { id, name, accessRuleIds } = row;
  const form = {
    accessRuleIds: ''
  }
  confirm((ok) => {
    OrgDept.sendApi('deptUpdate', { params: { id }, body: form }, { errorTip: '部门更新失败' }).then(res => {
      const { status, data } = res;
      if (status) {
        message.success("更新成功");
        getRef('cvtPageLoader').init();
        ok();
      }
    });
  })
  open('drawer', defineComponent({
    setup() {
      return () => <OrgAccessRuleTree
        checkedIds={accessRuleIds ? accessRuleIds.split(',').map(i => Number(i)) : []}
        onChange={e => {
          form.accessRuleIds = e.join(',');
        }} />
    }
  }), {
    title: `更新部门权限（${name}）`
  })
}
</script>