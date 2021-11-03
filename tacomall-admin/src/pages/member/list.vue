<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-22 11:49:23
 * @LastEditTime: 2021-10-26 15:31:01
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/member/list.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page member-list">
    <div class="l-overview"></div>
    <div class="l-list">
      <list-page
        ref="listPage"
        :config="page.config"
        :form="page.form"
        :load-excel-config="page.excel.fn.loadConfig"
        @form-reset="page.fn.resetHandler"
      >
        <template slot="query">
          <div class="q-block">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item>会员列表</el-breadcrumb-item>
              <el-breadcrumb-item :key="key" v-for="(item, key) in level">{{ item.realName }}</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div class="q-block">
            <div class="b-item">
              <options-selector
                :mode="1"
                placeholder="会员状态"
                :config="{entity: 'Member', type: 'status'}"
                @change="page.form.query.status = $event"
              ></options-selector>
            </div>
            <div class="b-item">
              <options-selector
                :mode="1"
                placeholder="会员类型"
                :config="{entity: 'Member', type: 'type'}"
                @change="page.form.query.type = $event"
              ></options-selector>
            </div>
          </div>
          <div class="q-block">
            <div class="b-item">
              <el-input size="small" placeholder="会员/编号/邀请码" v-model="page.form.query.keyword"></el-input>
            </div>
          </div>
        </template>
        <template slot="excel-config_1">
          <div class="e-item">
            <div class="i-label">
              <span>日期</span>
            </div>
            <div class="i-value">
              <el-date-picker
                v-model="page.excel.attr.time"
                type="month"
                placeholder="选择业绩月"
                @change="page.excel.fn.timeChangeHandler"
              ></el-date-picker>
            </div>
          </div>
        </template>
      </list-page>
    </div>
  </div>
</template>

<script>

import listPage from '@/components/list-page';
import optionsSelector from '@/components/options-selector';
import switchSelector from '@/components/switch-selector';
import { model } from '@/model';

const { Member } = model.collection;
export default {
  components: {
    listPage,
    optionsSelector,
    switchSelector
  },
  data () {
    return {
      level: [],
      page: {
        config: {
          entity: 'Member',
          action: 'page',
          column: [
            {
              fixed: true,
              prop: 'id',
              label: 'ID',
              width: '100'
            },
            {
              prop: 'realName',
              label: '姓名'
            },
            {
              prop: 'mobile',
              label: '电话'
            },
            {
              prop: '',
              label: '性别',
              VNodes: [
                ({ h, props }) => {
                  return h('p',
                    {
                    }, '男');
                }
              ]
            },
            {
              prop: '',
              label: '会员类型',
              VNodes: [
                ({ h, props }) => {
                  return h('p',
                    {
                    }, Member.filter('type', props.row._raw.type));
                }
              ]
            },
            {
              prop: '',
              label: '会员等级',
              VNodes: [
                ({ h, props }) => {
                  return h('p',
                    {
                    }, this.blankGet(Member.filter('level', props.row.levelId)));
                }
              ]
            },
            {
              prop: 'status',
              label: '状态',
              VNodes: [
                ({ h, props }) => {
                  return h(switchSelector, {
                    props: {
                      mode: 2,
                      tip: '更改会员状态，是否继续？',
                      config: { value: props.row._raw.status === 1 }
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
              width: '250',
              VNodes: [
                ({ h, props }) => {
                  return h('el-button',
                    {
                      props: { type: 'primary', size: 'mini' },
                      nativeOn: {
                        click: () => {
                          this.level.push(props.row);
                          this.page.form.query.keyword = '';
                          this.page.form.query.pId = props.row.id;
                        }
                      }
                    },
                    '下级会员');
                },
                ({ h, props }) => {
                  return h('el-button',
                    {
                      props: { type: 'primary', size: 'mini' },
                      nativeOn: {
                        click: () => {
                          this.go(`/member/${props.row.id}`);
                        }
                      }
                    },
                    '查看');
                }
              ]
            }
          ],
          selectable: true
        },
        form: {
          query: {
            pId: '',
            type: '',
            status: '',
            keyword: ''
          },
          order: {}
        },
        excel: {
          attr: {
            time: this.$dayjs(),
            config: {
              params: {
                year: this.$dayjs().year(),
                month: this.$dayjs().month() + 1
              },
              data: {}
            }
          },
          fn: {
            loadConfig: () => {
              return this.page.excel.attr.config;
            },
            timeChangeHandler: (e) => {
              this.page.excel.attr.config.params.year = this.$dayjs(e).year();
              this.page.excel.attr.config.params.month = this.$dayjs(e).month() + 1;
            }
          }
        },
        fn: {
          resetHandler: () => {
            this.level = [];
          }
        }
      },
      switchSelector: {
        fn: {
          updateStatus: (row, e) => {
            const { id } = row._raw;
            const { value, ok, error } = e;
            Member.sendApi('updateField', { params: { id, type: 10 }, data: { value: value ? 1 : 2, remark: '' } }, { errorTip: '更新会员状态失败' }).then(res => {
              const { status, data } = res;
              if (status) {
                this.$message({
                  message: '更新会员状态成功',
                  type: 'success'
                });
                this.$refs.listPage.query();
                ok();
              } else {
                error();
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
.member-list {
  background: white;
}
</style>
