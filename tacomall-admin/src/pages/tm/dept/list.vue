<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-22 11:49:23
 * @LastEditTime: 2021-10-22 14:18:13
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/tm/dept/list.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page tm-dept-list">
    <div class="l-overview"></div>
    <div class="l-list">
      <list-page
        ref="listPage"
        :config="page.config"
        :form="page.form"
        @add="$refs.popupConfirmTmDeptAdd.open({triggerClose: popup.tmDeptAdd.fn.closeHandler})"
      >
        <template slot="query">
          <div class="q-block">
            <div class="b-item">
              <el-input size="small" placeholder="部门/编号" v-model="page.form.query.keyword"></el-input>
            </div>
          </div>
        </template>
      </list-page>
    </div>
    <popup-confirm ref="popupConfirmTmDeptAdd" title="部门添加">
      <div class="popup-confirm_tm-dept-add">
        <div class="a-tab">
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmDeptAdd.attr.tab === 1}"
            @click="popup.tmDeptAdd.attr.tab = 1"
          >
            <span>部门信息</span>
          </div>
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmDeptAdd.attr.tab === 2}"
            @click="popup.tmDeptAdd.attr.tab = 2"
          >
            <span>部门权限</span>
          </div>
        </div>
        <div class="a-box a-box_base" v-if="popup.tmDeptAdd.attr.tab === 1">
          <form-validate ref="tmDeptAdd" :form="popup.tmDeptAdd.attr.form">
            <form-validate-field label="部门名称" :field="popup.tmDeptAdd.attr.form.name">
              <el-input v-model="popup.tmDeptAdd.attr.form.FILED('name').val"></el-input>
            </form-validate-field>
            <form-validate-field label="部门负责人" :field="popup.tmDeptAdd.attr.form.leaderId">
              <options-selector
                :mode="4"
                :config="{req: options.fn.tmStaffQuery}"
                @change="(e) => {options.fn.tmStaffChangeHandler(e, 1)}"
              ></options-selector>
            </form-validate-field>
            <form-validate-field label="部门描述" :field="popup.tmDeptAdd.attr.form.description">
              <el-input
                type="textarea"
                :rows="5"
                v-model="popup.tmDeptAdd.attr.form.FILED('description').val"
              ></el-input>
            </form-validate-field>
          </form-validate>
        </div>
        <div class="a-box a-box_access-rule" v-else>
          <tm-access-rule-tree @change="(e) => {options.fn.accessRuleChangeHandler(e, 1)}"></tm-access-rule-tree>
        </div>
      </div>
    </popup-confirm>
    <popup-confirm ref="popupConfirmTmDeptUpdate" title="部门更新">
      <div class="popup-confirm_tm-dept-update">
        <div class="u-tab">
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmDeptUpdate.attr.tab === 1}"
            @click="popup.tmDeptUpdate.attr.tab = 1"
          >
            <span>部门信息</span>
          </div>
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmDeptUpdate.attr.tab === 2}"
            @click="popup.tmDeptUpdate.attr.tab = 2"
          >
            <span>部门权限</span>
          </div>
        </div>
        <div class="u-box u-box_base" v-if="popup.tmDeptUpdate.attr.tab === 1">
          <form-validate
            ref="tmDeptUpdate"
            :form="popup.tmDeptUpdate.attr.form"
            :fill="popup.tmDeptUpdate.fn.fill"
          >
            <form-validate-field label="部门名称" :field="popup.tmDeptUpdate.attr.form.name">
              <el-input v-model="popup.tmDeptUpdate.attr.form.FILED('name').val"></el-input>
            </form-validate-field>
            <form-validate-field label="部门负责人" :field="popup.tmDeptUpdate.attr.form.leaderId">
              <options-selector
                :mode="4"
                :config="{req: options.fn.tmStaffQuery}"
                @change="(e) => {options.fn.tmStaffChangeHandler(e, 2)}"
              ></options-selector>
            </form-validate-field>
            <form-validate-field label="部门描述" :field="popup.tmDeptUpdate.attr.form.description">
              <el-input
                type="textarea"
                :rows="5"
                v-model="popup.tmDeptUpdate.attr.form.FILED('description').val"
              ></el-input>
            </form-validate-field>
          </form-validate>
        </div>
        <div class="u-box u-box_access-rule" v-else>
          <tm-access-rule-tree
            :checkValue="($_.get(popup.tmDeptUpdate.attr.info, 'accessRuleIds') || '').split(',').map(i => Number(i))"
            @change="(e) => {options.fn.accessRuleChangeHandler(e, 2)}"
          ></tm-access-rule-tree>
        </div>
      </div>
    </popup-confirm>
  </div>
</template>

<script>
import formValidate from '@/components/form-validate';
import formValidateField from '@/components/form-validate/field';
import optionsSelector from '@/components/options-selector';
import listPage from '@/components/list-page';
import popupConfirm from '@/components/popup/type/confirm';
import tmAccessRuleTree from '@/components/tm/access-rule-tree';
import { formToolkit } from '@/model/form-toolkit';

import { model } from '@/model';
const { TmDept, TmStaff } = model.collection;

export default {
  components: {
    formValidate,
    formValidateField,
    optionsSelector,
    listPage,
    popupConfirm,
    tmAccessRuleTree
  },
  data () {
    return {
      options: {
        fn: {
          tmStaffQuery: (e, ok) => {
            TmStaff.sendApi('staffPage', { params: {}, data: { query: { keyword: e } } }).then(res => {
              const { data } = res;
              ok(data.map(i => ({ value: i.id, label: i.nickname })));
            });
          },
          tmStaffChangeHandler: (e, type) => {
            if (type === 1) {
              this.popup.tmDeptAdd.attr.form.FILED('leaderId').val = e;
            } else {
              this.popup.tmDeptUpdate.attr.form.FILED('leaderId').val = e;
            }
          },
          accessRuleChangeHandler: (e, type) => {
            const { checkIds } = e;
            if (type === 1) {
              this.popup.tmDeptAdd.attr.form.FILED('accessRuleIds').val = checkIds.join(',');
            } else {
              this.popup.tmDeptUpdate.attr.form.FILED('accessRuleIds').val = checkIds.join(',');
            }
          }
        }
      },
      page: {
        config: {
          entity: 'TmDept',
          action: 'deptPage',
          column: [
            {
              prop: 'id',
              label: '序号'
            },
            {
              prop: 'name',
              label: '部门名称'
            },
            {
              label: '部门人数',
              VNodes: [
                ({ h }) => {
                  return h('p', '/');
                }
              ]
            },
            {
              label: '部门负责人',
              VNodes: [
                ({ h }) => {
                  return h('p', '/');
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
              VNodes: [
                ({ h, props }) => {
                  return h('el-button',
                    {
                      props: { type: 'primary', size: 'mini' },
                      nativeOn: {
                        click: () => {
                          this.$refs.popupConfirmTmDeptUpdate.open({ triggerOpen: this.popup.tmDeptUpdate.fn.openHandler, triggerClose: this.popup.tmDeptUpdate.fn.closeHandler, params: props });
                        }
                      }
                    },
                    '编辑');
                }
              ]
            }
          ]
        },
        form: {
          query: {
            keyword: ''
          },
          order: {}
        }
      },
      popup: {
        tmDeptAdd: {
          attr: {
            tab: 1,
            form: TmDept.formUtil().generator('add', 'default')
          },
          fn: {
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = formToolkit.validate(this.popup.tmDeptAdd.attr.form);
              if (ok) {
                TmDept.sendApi('deptAdd', { params: {}, data: form }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '部门添加成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
                      this.$message({
                        message: '部门添加失败',
                        type: 'warning'
                      });
                    }
                  }
                );
              } else {
                errorCallback();
                this.$message({
                  message: '请确认信息',
                  type: 'warning'
                });
              }
            }
          }
        },
        tmDeptUpdate: {
          attr: {
            tab: 1,
            form: TmDept.formUtil().generator('update', 'default'),
            info: {}
          },
          fn: {
            fill: (callback) => {
              callback(TmDept, this.popup.tmDeptUpdate.attr.info);
            },
            openHandler: (ok, params) => {
              this.popup.tmDeptUpdate.attr.tab = 1;
              TmDept.sendApi('deptInfo', { params: { id: params.row._raw.id }, data: {} }).then(res => {
                const { status, data } = res;
                if (status) {
                  this.popup.tmDeptUpdate.attr.info = data;
                  ok();
                } else {
                  this.$message({
                    message: '获取部门信息失败',
                    type: 'warning'
                  });
                }
              });
            },
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = formToolkit.validate(this.popup.tmDeptUpdate.attr.form);
              if (ok) {
                TmDept.sendApi('deptUpdate', { params: {}, data: form }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '部门添加成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
                      this.$message({
                        message: '部门添加失败',
                        type: 'warning'
                      });
                    }
                  }
                );
              } else {
                errorCallback();
                this.$message({
                  message: '请确认信息',
                  type: 'warning'
                });
              }
            }
          }
        }
      }
    };
  }
};
</script>

<style lang="less">
.tm-dept-list {
  background: white;
  .popup-confirm {
    &_tm-dept-add {
      .a-tab {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        padding: 0 20px;
        display: flex;
        .t-item {
          display: flex;
          align-items: center;
          height: 50px;
          &-active {
            position: relative;
            &:after {
              position: absolute;
              bottom: 0;
              left: 0;
              content: "";
              width: 100%;
              height: 3px;
              background: #1890ff;
            }
          }
          &:not(:first-child) {
            margin-left: 20px;
          }
        }
      }
      .a-box {
        padding: 70px 20px 20px 20px;
      }
    }
    &_tm-dept-update {
      .u-tab {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        padding: 0 20px;
        display: flex;
        .t-item {
          display: flex;
          align-items: center;
          height: 50px;
          &-active {
            position: relative;
            &:after {
              position: absolute;
              bottom: 0;
              left: 0;
              content: "";
              width: 100%;
              height: 3px;
              background: #1890ff;
            }
          }
          &:not(:first-child) {
            margin-left: 20px;
          }
        }
      }
      .u-box {
        padding: 70px 20px 20px 20px;
      }
    }
  }
}
</style>
