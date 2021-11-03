<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-22 11:49:23
 * @LastEditTime: 2021-10-22 14:17:03
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/tm/job/list.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page tm-job-list">
    <div class="l-overview"></div>
    <div class="l-list">
      <list-page
        ref="listPage"
        :config="page.config"
        :form="page.form"
        @add="$refs.popupConfirmTmJobAdd.open({triggerClose: popup.tmJobAdd.fn.closeHandler})"
      >
        <template slot="query">
          <div class="q-block">
            <div class="b-item">
              <el-input size="small" placeholder="职位/编号" v-model="page.form.query.keyword"></el-input>
            </div>
          </div>
        </template>
      </list-page>
    </div>
    <popup-confirm ref="popupConfirmTmJobAdd" title="职位添加">
      <div class="popup-confirm_tm-job-add">
        <div class="a-tab">
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmJobAdd.attr.tab === 1}"
            @click="popup.tmJobAdd.attr.tab = 1"
          >
            <span>职位信息</span>
          </div>
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmJobAdd.attr.tab === 2}"
            @click="popup.tmJobAdd.attr.tab = 2"
          >
            <span>职位权限</span>
          </div>
        </div>
        <div class="a-box a-box_base" v-if="popup.tmJobAdd.attr.tab === 1">
          <form-validate ref="tmJobAdd" :form="popup.tmJobAdd.attr.form">
            <form-validate-field label="职位名称" :field="popup.tmJobAdd.attr.form.name">
              <el-input v-model="popup.tmJobAdd.attr.form.FILED('name').val"></el-input>
            </form-validate-field>
            <form-validate-field label="所属部门" :field="popup.tmJobAdd.attr.form.deptId">
              <options-selector
                :mode="4"
                :config="{req: options.fn.tmDeptQuery}"
                @change="(e) => {options.fn.tmDeptChangeHandler(e, 1)}"
              ></options-selector>
            </form-validate-field>
            <form-validate-field label="职位描述" :field="popup.tmJobAdd.attr.form.description">
              <el-input
                type="textarea"
                :rows="5"
                v-model="popup.tmJobAdd.attr.form.FILED('description').val"
              ></el-input>
            </form-validate-field>
          </form-validate>
        </div>
        <div class="a-box a-box_access-rule" v-else>
          <tm-access-rule-tree @ignore-change="(e) => {options.fn.accessRuleChangeHandler(e, 1)}"></tm-access-rule-tree>
        </div>
      </div>
    </popup-confirm>
    <popup-confirm ref="popupConfirmTmJobUpdate" title="职位更新">
      <div class="popup-confirm_tm-job-update">
        <div class="u-tab">
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmJobUpdate.attr.tab === 1}"
            @click="popup.tmJobUpdate.attr.tab = 1"
          >
            <span>职位信息</span>
          </div>
          <div
            class="t-item"
            :class="{'t-item-active': popup.tmJobUpdate.attr.tab === 2}"
            @click="popup.tmJobUpdate.attr.tab = 2"
          >
            <span>职位权限</span>
          </div>
        </div>
        <div class="u-box u-box_base" v-if="popup.tmJobUpdate.attr.tab === 1">
          <form-validate
            ref="tmJobUpdate"
            :form="popup.tmJobUpdate.attr.form"
            :fill="popup.tmJobUpdate.fn.fill"
          >
            <form-validate-field label="职位名称" :field="popup.tmJobUpdate.attr.form.name">
              <el-input v-model="popup.tmJobUpdate.attr.form.FILED('name').val"></el-input>
            </form-validate-field>
            <form-validate-field label="所属部门" :field="popup.tmJobAdd.attr.form.deptId">
              <options-selector
                :mode="4"
                :config="{req: options.fn.tmDeptQuery}"
                @change="(e) => {options.fn.tmDeptChangeHandler(e, 1)}"
              ></options-selector>
            </form-validate-field>
            <form-validate-field label="职位描述" :field="popup.tmJobUpdate.attr.form.description">
              <el-input
                type="textarea"
                :rows="5"
                v-model="popup.tmJobUpdate.attr.form.FILED('description').val"
              ></el-input>
            </form-validate-field>
          </form-validate>
        </div>
        <div class="u-box u-box_access-rule" v-else>
          <tm-access-rule-tree
            :ignoreValue="$_.get(popup.tmJobUpdate.attr.info, 'ignoreAccessRuleIds').split(',').map(i => Number(i))"
            @ignore-change="(e) => {options.fn.accessRuleChangeHandler, 2}"
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
const { TmDept, TmJob } = model.collection;

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
          tmDeptQuery: (e, ok) => {
            TmDept.sendApi('deptPage', { params: {}, data: { query: { keyword: e } } }).then(res => {
              const { data } = res;
              ok(data.map(i => ({ value: i.id, label: i.name })));
            });
          },
          tmDeptChangeHandler: (e, type) => {
            if (type === 1) {
              this.popup.tmJobAdd.attr.form.FILED('deptId').val = e;
            } else {
              this.popup.tmJobUpdate.attr.form.FILED('deptId').val = e;
            }
          },
          accessRuleChangeHandler: (e, type) => {
            const { checkIds } = e;
            if (type === 1) {
              this.popup.tmJobAdd.attr.form.FILED('ignoreAccessRuleIds').val = checkIds.join(',');
            } else {
              this.popup.tmJobUpdate.attr.form.FILED('ignoreAccessRuleIds').val = checkIds.join(',');
            }
          }
        }
      },
      page: {
        config: {
          entity: 'TmJob',
          action: 'jobPage',
          column: [
            {
              prop: 'id',
              label: '序号'
            },
            {
              prop: 'name',
              label: '职位名称'
            },
            {
              label: '职位人数',
              VNodes: [
                ({ h }) => {
                  return h('p', '0');
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
                          this.$refs.popupConfirmTmJobUpdate.open({ triggerOpen: this.popup.tmJobUpdate.fn.openHandler, triggerClose: this.popup.tmJobUpdate.fn.closeHandler, params: props });
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
        tmJobAdd: {
          attr: {
            tab: 1,
            form: TmJob.formUtil().generator('add', 'default')
          },
          fn: {
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = formToolkit.validate(this.popup.tmJobAdd.attr.form);
              if (ok) {
                TmJob.sendApi('jobAdd', { params: {}, data: form }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '职位添加成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
                      this.$message({
                        message: '职位添加失败',
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
        tmJobUpdate: {
          attr: {
            tab: 1,
            form: TmJob.formUtil().generator('update', 'default'),
            info: {}
          },
          fn: {
            fill: (callback) => {
              callback(TmJob, this.popup.tmJobUpdate.attr.info);
            },
            openHandler: (ok, params) => {
              this.popup.tmJobUpdate.attr.tab = 1;
              TmJob.sendApi('jobInfo', { params: { id: params.row._raw.id }, data: {} }).then(res => {
                const { status, data } = res;
                if (status) {
                  this.popup.tmJobUpdate.attr.info = data;
                  ok();
                } else {
                  this.$message({
                    message: '获取职位信息失败',
                    type: 'warning'
                  });
                }
              });
            },
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = formToolkit.validate(this.popup.tmJobUpdate.attr.form);
              if (ok) {
                TmJob.sendApi('jobUpdate', { params: {}, data: form }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '职位添加成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
                      this.$message({
                        message: '职位添加失败',
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
.tm-job-list {
  background: white;
  .popup-confirm {
    &_tm-job-add {
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
    &_tm-job-update {
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
