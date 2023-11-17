<template>
  <div class="approve">
    <div class="approve_header">
      <div class="header_title">
        <div class="title_left">
          <span>审核编号: {{ getCtXVal('sn') }}</span>
        </div>
        <div class="title_btn" v-if="getCtXVal('status') === 'pendding'">
          <a-button type="primary" size="large" danger>取消
          </a-button>
          <a-button type="dashed " size="large" danger style="marginLeft:10px;">不通过
          </a-button>
          <a-button type="primary" size="large" style="marginLeft:10px;" @click="doAgree">通过
          </a-button>
        </div>
      </div>
      <div class="header_content">
        <div class="content_left"></div>
        <div class="content_right">
          <div class="right_item">
            <span>状态</span>
            <em :style="{ color: getCtXVal()?.toEnum('status')?.color }">{{ getCtXVal()?.toEnum('status')?.label }}</em>
          </div>
          <div class="right_item">
            <span>订单金额</span>
            <em>￥235.41</em>
          </div>
        </div>
      </div>
    </div>
    <div class="approve_node">
      <div class="node_title">
        <div class="title_left">
          <span>审核节点</span>
        </div>
      </div>
      <div class="node_content">
        <div class="content_step">
          <a-steps :current="1">
            <a-step>
              <!-- <span slot="title">Finished</span> -->
              <template #title>创建审核</template>
              <template #description>
                <span>This is a description.</span>
              </template>
            </a-step>
            <a-step :title="item.name" sub-title="Left 00:00:08" description="This is a description." :key="key"
              v-for="(item, key) in getCtXVal('approveNodeList')" />
            <a-step>
              <!-- <span slot="title">Finished</span> -->
              <template #title>审核结束</template>
              <template #description>
                <span>This is a description.</span>
              </template>
            </a-step>
          </a-steps>
        </div>
        <div class="content_box">
          <cvt-detail ref-key="cvtDetail" :ref="storeRef" />
        </div>
      </div>
    </div>
    <div class="approve_process">
      <div class="process_title">
        <div class="title_left">
          <span>审批动态</span>
        </div>
      </div>
      <div class="process_content">
        <a-timeline>
          <a-timeline-item>
            <div class="content_timeline_item">
              <div class="item_text">
                <div class="text_item">
                  <div class="item_label">创建时间：</div>
                  <div class="item_value">{{ getCtXVal('createTime') }}</div>
                </div>
                <div class="text_item">
                  <div class="item_label">创建人：</div>
                  <div class="item_value">{{ getCtXVal('creator.nickname') }}</div>
                </div>
              </div>
            </div>
          </a-timeline-item>
          <a-timeline-item :key="key" v-for="(item, key) in getCtXVal('approveProccessList')">
            <div class="content_timeline_item content_timeline_item--success">
              <div class="item_text">
                <div class="text_item">
                  <div class="item_label">审批时间：</div>
                  <div class="item_value">{{ item?.createTime }}</div>
                </div>
                <div class="text_item">
                  <div class="item_label">审批人：</div>
                  <div class="item_value">{{}}</div>
                </div>
                <div class="text_item">
                  <div class="item_label">备注：</div>
                  <div class="item_value"></div>
                </div>
                <div class="text_item">
                  <div class="item_label">附件：</div>
                  <div class="item_value">
                    <a-image-preview-group>
                      <a-image :width="50" src="https://aliyuncdn.antdv.com/vue.png" />
                      <a-image :width="50" src="https://aliyuncdn.antdv.com/logo.png" />
                    </a-image-preview-group>
                  </div>
                </div>
              </div>
              <div class="item_result">
                <i class="iconfont">&#xe604;</i>
              </div>
            </div>
          </a-timeline-item>
        </a-timeline>
      </div>
    </div>
  </div>
</template>

<script lang="jsx" setup>
import { defineComponent } from 'vue';
import getDetail from '@/composables/getDetail';
import { _refMap, storeRef, getRef } from '@/composables/utilsResolver';
import { CvtForm, Popup } from 'codingtalk-vue-toolkit';
import { Approve } from '@/entity';
import { message, Button } from "ant-design-vue";

const { id, getCtXVal, load } = getDetail('Approve', 'info', {
  isAutoLoad: true,
  callback: () => {
    getRef('cvtDetail')?.init(null, null, () => {
      return new Promise((resolve) => {
        resolve(getCtXVal('_raw'));
      });
    }, {
      init: () => {
        return {
          config: {
            type: 'default',
            col: 3
          },
          fields: getCtXVal('approveNode.opt.detail')
        }
      },
      renderHook: {
        'publish.sn': (v, info) => {
          return <>
            <span>{v}</span>
            <span className="link" style={{ marginLeft: '10px' }}>查看详情</span>
          </>
        }
      }
    })
  }
});

function doAgree() {
  const { open, ready, confirm } = Popup();
  ready(() => {
    getRef('cvtForm').init('Approve', 'doAgree', {});
  });
  confirm((ok) => {
    getRef('cvtForm').submit().then(form => {
      Approve.sendApi('doAgree', { params: { id, isAgree: 1 }, body: form }, { errorTip: '审核失败' }).then(res => {
        const { status, data } = res;
        if (status) {
          message.success("审核成功");
          load();
          ok();
        }
      })
    }).catch(e => {
      message.warn(e);
    })
  })
  open('dialog', defineComponent({
    setup() {
      return () => <CvtForm ref-key="cvtForm" ref={storeRef} />
    }
  }), {
    title: '审核通过'
  })
}
</script>

<style lang="less">
@import "../../assets/style/var.less";
@import "../../assets/style/mixins.less";

.approve {
  &_header {
    padding: 20px;
    margin-bottom: 20px;
    background: white;

    .header_title {
      .flex(@a: center, @j: space-between);
      height: 70px;

      .title_left {
        span {
          font-size: 22px;
        }
      }
    }

    .header_content {
      .flex();

      .content_left {
        flex: 1;
      }

      .content_right {
        .flex();

        .right_item {
          .flex(@d: column, @a: flex-end);

          span {
            color: @text-color;
          }

          em {
            font-size: 24px;
            font-weight: 600;
          }

          &:not(:first-child) {
            margin-left: 20px;
          }
        }
      }
    }
  }

  &_node {
    background: white;

    .node_title {
      .flex(@a: center);
      height: 70px;
      padding: 0 20px;
      border-bottom: 1px solid @border-color-split;
    }

    .node_content {
      padding: 20px 150px;

      .content_step {
        padding: 20px 0 40px 20px;
      }

      .content_box {
        padding: 20px;
        background: @background-color-base ;

        .cvt_detail {
          background: none;

          .link {
            color: @primary-color;

            &:hover {
              cursor: pointer;
            }
          }
        }
      }
    }
  }

  &_process {
    background: white;
    margin-top: 20px;

    .process_title {
      .flex(@a: center);
      height: 70px;
      padding: 0 20px;
      border-bottom: 1px solid @border-color-split;
    }

    .process_content {
      padding: 20px 150px;

      .content_timeline_item {
        position: relative;
        padding: 20px;
        margin-top: 20px;
        background: @background-color-base ;
        overflow: hidden;

        &--success {
          border: 1px solid @success-color;
        }

        .item_text {
          .text_item {
            .flex();
            color: @text-color;

            &:not(:first-child) {
              margin-top: 10px;
            }
          }
        }

        .item_result {
          position: absolute;
          top: 50px;
          right: -50px;
          transform: translateY(-50%);

          .iconfont {
            font-size: 200px;
            color: @success-color;
            opacity: 0.3;
          }
        }
      }
    }
  }
}
</style>