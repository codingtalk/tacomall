<template>
  <div class="publish_list">
    <div class="list_brief"></div>
    <div class="list_page-loader">
      <cvt-page-loader title="订单列表" ref-key="cvtPageLoader" :ref="storeRef" :config="opt.config" :query="opt.query"
        :column="opt.column" :form-pre-hook="opt.formPreHook" @add="go('/form-order/add')"></cvt-page-loader>
    </div>
  </div>
</template>
  
<script lang="jsx" setup>
import { reactive, defineComponent } from "vue";
import { CvtForm, Popup } from 'codingtalk-vue-toolkit';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { go } from "@/utils/navigator";
import { message, Button, Modal } from "ant-design-vue";
import { Logistic, FormOrder } from "@/entity";

const opt = reactive({
  config: { entity: "FormOrder", action: "formPage", enableRemoteConfig: 'FormOrder' },
  query: [{ label: "关键字" }],
  formPreHook: () => {
    return {
      query: {
        status: 'placed,refund,delivery,received'
      }
    }
  },
  column: [
    { label: "编号", key: "sn" },
    {
      label: "类型", key: "", render: ({ row }) => {
        return <>
          <span>{row?._raw?.type === 'KD' ? '快递' : '充话费'}</span>
        </>
      }
    },
    {
      label: "客户", key: "", render: ({ row }) => {
        return <>
          <span>{row?._raw?.nickname}</span>
        </>
      }
    },
    {
      label: "下单信息", key: "", render: ({ row }) => {
        return <>
          {row.type === 'KD' && <>
            <div>------- 寄件人信息 -------</div>
            <div style={{ color: '#333', fontSize: '12px'}}>姓名: {row?.infoData?.senderRealName}</div>
            <div style={{ color: '#333', fontSize: '12px'}}>手机号: {row?.infoData?.senderMobile}</div>
            <div style={{ color: '#333', fontSize: '12px' }}>地址: {`${row?.infoData?.senderProvince}${row?.infoData?.senderCity}${row?.infoData?.senderDistrict}${row?.infoData?.senderDetail}`}</div>
            <div>------- 收件人信息 -------</div>
            <div style={{ color: '#333', fontSize: '12px'}}>姓名: {row?.infoData?.receiverRealName}</div>
            <div style={{ color: '#333', fontSize: '12px'}}>手机号: {row?.infoData?.receiverMobile}</div>
            <div style={{ color: '#333', fontSize: '12px' }}>地址: {`${row?.infoData?.receiverProvince}${row?.infoData?.receiverCity}${row?.infoData?.receiverDistrict}${row?.infoData?.receiverDetail}`}</div>
            <div>------- 收件人信息 -------</div>
            <div style={{ color: '#333', fontSize: '12px'}}>物流单: {row.logistic?.infoData?.orderNo}</div>
          </>}
          {row.type === 'CHF' && <>
            <div style={{ color: '#333', fontSize: '12px'}}>手机号: {row?.infoData?.account}</div>
            <div style={{ color: '#333', fontSize: '12px'}}>金额: ￥{row?.infoData?.amount}</div>
          </>}
        </>
      }
    },
    {
      label: "金额/￥", key: "", render: ({ row }) => {
        return <>
          <span >{row.amountActual}</span>
        </>
      }
    },
    {
      label: "状态", key: "", render: ({ row }) => {
        return <>
          <span style={{ color: row.toEnum('status')?.color }}>{row.toEnum('status')?.label}</span>
        </>
      }
    },
    {
      label: "操作",
      key: "operation",
      width: 250,
      fix: "right",
      render: ({ row }) => {
        const { id } = row;
        return <>
          <Button type="primary" onClick={() => { go(`/form-order/${id}`) }}>查看详情</Button>
          {row.status === 'initial' && <Button style={{ marginLeft: '10px' }} onClick={() => {
            addLogistic(row)
          }}>填写物流</Button>}
          {row.status === 'placed' && <Button style={{ marginLeft: '10px' }} type="primary" danger onClick={() => {
            Modal.confirm({
              title: 'Confirm',
              content: '是否确认取消改订单？',
              okText: '确认',
              cancelText: '取消',
              onOk: () => {
                FormOrder.sendApi('formUpdate', { params: { id }, body: { status: 'refund' } }).then(({ status }) => {
                  if (status) {
                    message.success("退单成功");
                    getRef('cvtPageLoader').init();
                  }
                })
              }
            });
          }}>退单</Button>}
        </>;
      },
    },
  ],
});

function addLogistic(formOrder) {
  const { open, ready, confirm } = Popup();
  ready(() => {
    getRef('cvtForm').init('Logistic', 'add', {});
  });
  confirm((ok) => {
    getRef('cvtForm').submit().then(form => {
      form.formOrderId = formOrder?.id
      form.type = formOrder?.infoData?.logistic
      Logistic.sendApi('add', { params: {}, body: form }, { errorTip: '物流单号添加失败' }).then(res => {
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
    title: '添加物流单号'
  })
}
</script>