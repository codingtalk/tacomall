<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-22 11:49:23
 * @LastEditTime: 2021-10-22 14:17:22
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/tm/staff/list.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page tm-staff-list">
    <div class="l-overview"></div>
    <div class="l-list">
      <list-page
        ref="listPage"
        :config="page.config"
        :form="page.form"
        @add="$refs.popupConfirmTmStaffAdd.open({triggerClose: popup.tmStaffAdd.fn.closeHandler})"
      >
        <template slot="query">
          <div class="q-block">
            <div class="b-item">
              <el-input size="small" placeholder="员工/编号" v-model="page.form.query.keyword"></el-input>
            </div>
          </div>
        </template>
      </list-page>
    </div>
    <popup-confirm ref="popupConfirmTmStaffAdd" title="员工添加">
      <div class="popup-confirm_tm-staff-add">
        <form-validate ref="tmStaffAdd" :form="popup.tmStaffAdd.attr.form">
          <form-validate-field label="员工名称" :field="popup.tmStaffAdd.attr.form.nickname">
            <el-input v-model="popup.tmStaffAdd.attr.form.FILED('nickname').val"></el-input>
          </form-validate-field>
          <form-validate-field label="职位">
            <options-selector
              :mode="4"
              :config="{req: options.fn.tmJobQuery}"
              @change="(e) => {options.fn.tmJobChangeHandler(e, 1)}"
            ></options-selector>
          </form-validate-field>
          <form-validate-field label="账号" :field="popup.tmStaffAdd.attr.form.username">
            <el-input v-model="popup.tmStaffAdd.attr.form.FILED('username').val"></el-input>
          </form-validate-field>
          <form-validate-field label="密码" :field="popup.tmStaffAdd.attr.form.passwd">
            <el-input v-model="popup.tmStaffAdd.attr.form.FILED('passwd').val"></el-input>
          </form-validate-field>
          <form-validate-field label="状态">
            <el-radio-group v-model="popup.tmStaffAdd.attr.form.FILED('status').val">
              <el-radio :label="0">禁用</el-radio>
              <el-radio :label="1">启用</el-radio>
            </el-radio-group>
          </form-validate-field>
        </form-validate>
      </div>
    </popup-confirm>
    <popup-confirm ref="popupConfirmTmStaffUpdate" title="员工更新">
      <div class="popup-confirm_tm-staff-update">
        <form-validate
          ref="tmStaffUpdate"
          :form="popup.tmStaffUpdate.attr.form"
          :fill="popup.tmStaffUpdate.fn.fill"
        >
          <form-validate-field label="员工名称" :field="popup.tmStaffUpdate.attr.form.nickname">
            <el-input v-model="popup.tmStaffUpdate.attr.form.FILED('nickname').val"></el-input>
          </form-validate-field>
          <form-validate-field label="职位">
            <options-selector
              :mode="4"
              :config="{req: options.fn.tmJobQuery}"
              @change="(e) => {options.fn.tmJobChangeHandler(e, 2)}"
            ></options-selector>
          </form-validate-field>
          <form-validate-field label="账号" :field="popup.tmStaffAdd.attr.form.username">
            <el-input v-model="popup.tmStaffUpdate.attr.form.FILED('username').val"></el-input>
          </form-validate-field>
          <form-validate-field label="密码" :field="popup.tmStaffAdd.attr.form.passwd">
            <el-input
              placeholder="留空为不更新"
              v-model="popup.tmStaffUpdate.attr.form.FILED('passwd').val"
            ></el-input>
          </form-validate-field>
          <form-validate-field label="状态">
            <el-radio-group v-model="popup.tmStaffUpdate.attr.form.FILED('status').val">
              <el-radio :label="0">禁用</el-radio>
              <el-radio :label="1">启用</el-radio>
            </el-radio-group>
          </form-validate-field>
        </form-validate>
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
import { formToolkit } from '@/model/form-toolkit';

import { model } from '@/model';
const { TmJob, TmStaff } = model.collection;

export default {
  components: {
    formValidate,
    formValidateField,
    optionsSelector,
    listPage,
    popupConfirm
  },
  data () {
    return {
      options: {
        fn: {
          tmJobQuery: (e, ok) => {
            TmJob.sendApi('jobPage', { params: {}, data: { query: { keyword: e } } }).then(res => {
              const { data } = res;
              ok(data.map(i => ({ value: i.id, label: i.name })));
            });
          },
          tmJobChangeHandler: (e, type) => {
            if (type === 1) {
              this.popup.tmStaffAdd.attr.form.FILED('jobId').val = e;
            } else {
              this.popup.tmStaffUpdate.attr.form.FILED('jobId').val = e;
            }
          }
        }
      },
      page: {
        config: {
          entity: 'TmStaff',
          action: 'staffPage',
          column: [
            {
              prop: 'id',
              label: '序号'
            },
            {
              prop: 'nickname',
              label: '员工名称'
            },
            {
              prop: 'name',
              label: '职位'
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
                          this.$refs.popupConfirmTmStaffUpdate.open({ triggerOpen: this.popup.tmStaffUpdate.fn.openHandler, triggerClose: this.popup.tmStaffUpdate.fn.closeHandler, params: props });
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
        tmStaffAdd: {
          attr: {
            form: TmStaff.formUtil().generator('add', 'default')
          },
          fn: {
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = formToolkit.validate(this.popup.tmStaffAdd.attr.form);
              if (ok) {
                TmStaff.sendApi('staffAdd', { params: {}, data: form }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '员工添加成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
                      this.$message({
                        message: '员工添加失败',
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
        tmStaffUpdate: {
          attr: {
            form: TmStaff.formUtil().generator('update', 'default'),
            info: {}
          },
          fn: {
            fill: (callback) => {
              callback(TmStaff, this.popup.tmStaffUpdate.attr.info);
            },
            openHandler: (ok, params) => {
              TmStaff.sendApi('staffInfo', { params: { id: params.row._raw.id }, data: {} }).then(res => {
                const { status, data } = res;
                if (status) {
                  this.popup.tmStaffUpdate.attr.info = data;
                  ok();
                } else {
                  this.$message({
                    message: '获取员工信息失败',
                    type: 'warning'
                  });
                }
              });
            },
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = formToolkit.validate(this.popup.tmStaffUpdate.attr.form);
              if (ok) {
                TmStaff.sendApi('staffUpdate', { params: {}, data: form }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '员工更新成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
                      this.$message({
                        message: '员工更新失败',
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
.tm-staff-list {
  background: white;
  .popup-confirm {
    &_tm-staff-add {
      padding: 20px;
    }
    &_tm-staff-update {
      padding: 20px;
    }
  }
}
</style>
