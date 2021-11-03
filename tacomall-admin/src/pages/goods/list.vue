<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-22 11:49:23
 * @LastEditTime: 2021-10-23 09:59:49
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/goods/list.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page goods-list">
    <div class="l-overview"></div>
    <div class="l-list">
      <list-page
        ref="listPage"
        :config="page.config"
        :form="page.form"
        @add="$_.get(tmStaffInfo, 'accessExtraJson.allowedModifyGoodsModule') !== 1 ? null : go('/goods/add')"
      >
        <template slot="query">
          <div class="q-block">
            <div class="b-item">
              <options-selector
                :mode="1"
                placeholder="在售情况"
                :config="{entity: 'Goods', type: 'status'}"
                @change="page.form.query.status = $event"
              ></options-selector>
            </div>
          </div>
          <div class="q-block">
            <div class="b-item">
              <el-input size="small" placeholder="商品/编号" v-model="page.form.query.keyword"></el-input>
            </div>
          </div>
        </template>
        <template slot="action">
          <el-button type="danger" size="small">下架</el-button>
        </template>
      </list-page>
    </div>
  </div>
</template>

<script>

import listPage from '@/components/list-page';
import optionsSelector from '@/components/options-selector';
import switchSelector from '@/components/switch-selector';
import imgVideoPreviewer from '@/components/img-video-previewer';
import { model } from '@/model';

const { Goods } = model.collection;

export default {
  components: {
    listPage,
    optionsSelector,
    switchSelector,
    imgVideoPreviewer
  },
  data () {
    return {
      page: {
        config: {
          entity: 'Goods',
          action: 'page',
          column: [
            {
              prop: 'id',
              label: 'ID'
            },
            {
              prop: 'name',
              label: '名称',
              width: 110
            },
            {
              prop: 'name',
              label: '封面',
              width: 110,
              VNodes: [
                ({ h, props }) => {
                  return h(imgVideoPreviewer, {
                    props: {
                      src: props.row.thumbnail
                    }
                  });
                }
              ]
            },
            {
              prop: 'amount',
              label: '售价'
            },
            {
              prop: '',
              label: '库存',
              VNodes: [
                ({ h, props }) => {
                  return h('p', props.row._raw.stock);
                }
              ]
            },
            {
              prop: '',
              label: '是否可售',
              width: 110,
              VNodes: [
                ({ h, props }) => {
                  return h(switchSelector, {
                    props: {
                      mode: 2,
                      tip: '更改商品售卖状态，是否继续？',
                      config: { value: !!props.row._raw.status }
                    },
                    on: {
                      change: (e) => {
                        this.switchSelector.fn.updateStatus(props.row, e);
                      }
                    }
                  });
                }
              ]
            },
            {
              prop: 'createTime',
              label: '创建时间'
            },
            {
              fixed: 'right',
              prop: 'action',
              label: '操作',
              width: '160',
              VNodes: [
                ({ h, props }) => {
                  return h('el-button',
                    {
                      props: { type: 'primary', size: 'mini' },
                      nativeOn: {
                        click: () => {
                          this.go(`/goods/${props.row.id}`);
                        }
                      }
                    },
                    '查看');
                },
                ({ h, props }) => {
                  return h('el-button', {
                    props: { type: 'primary', size: 'mini', disabled: this.$_.get(this.tmStaffInfo, 'accessExtraJson.allowedModifyGoodsModule') !== 1 },
                    nativeOn: {
                      click: () => { this.go(`/goods/update/${props.row.id}`); }
                    }
                  }, '编辑');
                }
              ]
            }
          ],
          selectable: true
        },
        form: {
          query: {
            type: '',
            status: '',
            keyword: ''
          },
          order: {}
        }
      },
      switchSelector: {
        fn: {
          updateStatus: (row, e) => {
            const { id } = row._raw;
            const { value, ok, error } = e;
            Goods.sendApi('updateField', { params: { id, type: 1 }, data: { value: value ? 1 : 0, remark: '' } }).then(res => {
              const { status, data } = res;
              if (status) {
                ok();
                this.$message({
                  message: '更新商品状态成功',
                  type: 'success'
                });
                this.$refs.listPage.query();
              } else {
                error();
                this.$message({
                  message: '更新商品状态失败',
                  type: 'error'
                });
              }
            });
          }
        }
      }
    };
  }
};
</script>

<style lang="less">
.goods-list {
  background: white;
  .img-video-previewer {
    widows: 100px;
    height: 100px;
  }
}
</style>
