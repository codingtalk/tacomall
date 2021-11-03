<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-22 11:49:23
 * @LastEditTime: 2021-10-26 14:40:29
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/goods/category/list.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page goods-category-list">
    <div class="l-overview"></div>
    <div class="l-list">
      <list-page
        ref="listPage"
        :config="page.config"
        :form="page.form"
        @add="$_.get(tmStaffInfo, 'accessExtraJson.allowedModifyGoodsModule') !== 1 ? null : $refs.popupConfirmGoodsCategoryAdd.open({triggerClose: popup.goodsCategoryAdd.fn.closeHandler})"
      >
        <template slot="query">
          <div class="q-block">
            <div class="b-item">
              <options-selector
                :mode="1"
                placeholder="状态"
                :config="{entity: 'GoodsCategory', type: 'status'}"
                @change="options.fn.statusChangeHandler"
              ></options-selector>
            </div>
          </div>
          <div class="q-block">
            <div class="b-item">
              <el-input size="small" placeholder="分类/编号" v-model="page.form.query.keyword"></el-input>
            </div>
          </div>
        </template>
      </list-page>
    </div>
    <popup-confirm ref="popupConfirmGoodsCategoryAdd" title="商品分类添加">
      <div class="popup-confirm_goods-category-add">
        <form-validate ref="formValidateGoodsCategoryAdd" :form="popup.goodsCategoryAdd.attr.form">
          <form-validate-field label="父类"></form-validate-field>
          <form-validate-field label="商品分类名称" :field="popup.goodsCategoryAdd.attr.form.name">
            <el-input v-model="popup.goodsCategoryAdd.attr.form.name.value"></el-input>
          </form-validate-field>
          <form-validate-field label="缩略图" :field="popup.goodsCategoryAdd.attr.form.thumbnail">
            <img-video-uploader :max="1"></img-video-uploader>
          </form-validate-field>
          <form-validate-field label="状态">
            <el-radio-group v-model="popup.goodsCategoryAdd.attr.form.status.value">
              <el-radio :label="0">禁用</el-radio>
              <el-radio :label="1">启用</el-radio>
            </el-radio-group>
          </form-validate-field>
        </form-validate>
      </div>
    </popup-confirm>
    <popup-confirm ref="popupConfirmGoodsCategoryUpdate" title="商品分类更新">
      <div class="popup-confirm_goods-category-update">
        <form-validate
          ref="formValidateGoodsCategoryUpdate"
          :form="popup.goodsCategoryUpdate.attr.form"
          :fill="popup.goodsCategoryUpdate.fn.fill"
        >
          <form-validate-field label="父类"></form-validate-field>
          <form-validate-field label="商品分类名称" :field="popup.goodsCategoryUpdate.attr.form.name">
            <el-input v-model="popup.goodsCategoryUpdate.attr.form.name.value"></el-input>
          </form-validate-field>
          <form-validate-field label="缩略图" :field="popup.goodsCategoryAdd.attr.form.thumbnail">
            <img-video-uploader :max="1"></img-video-uploader>
          </form-validate-field>
          <form-validate-field label="状态">
            <el-radio-group v-model="popup.goodsCategoryUpdate.attr.form.status.value">
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
import listPage from '@/components/list-page';
import optionsSelector from '@/components/options-selector';
import popupConfirm from '@/components/popup/type/confirm';
import imgVideoUploader from '@/components/img-video-uploader';
import imgVideoPreviewer from '@/components/img-video-previewer';
import { formToolkit } from '@/model/form-toolkit';

import { model } from '@/model';
const { GoodsCategory } = model.collection;

export default {
  components: {
    formValidate,
    formValidateField,
    listPage,
    optionsSelector,
    popupConfirm,
    imgVideoUploader,
    imgVideoPreviewer
  },
  data () {
    return {
      options: {
        fn: {
          statusChangeHandler: (e) => {
            this.page.form.query.status = e;
          }
        }
      },
      page: {
        config: {
          entity: 'GoodsCategory',
          action: 'categoryPage',
          column: [
            {
              prop: 'id',
              label: 'ID'
            },
            {
              prop: 'name',
              label: '名称'
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
                  return h('el-button', {
                    props: { type: 'primary', size: 'mini', disabled: this.$_.get(this.tmStaffInfo, 'accessExtraJson.allowedModifyGoodsModule') !== 1 },
                    nativeOn: {
                      click: () => {
                        this.$refs.popupConfirmGoodsCategoryUpdate.open({ triggerOpen: this.popup.goodsCategoryUpdate.fn.openHandler, triggerClose: this.popup.goodsCategoryUpdate.fn.closeHandler, params: props });
                      }
                    }
                  }, '编辑');
                },
                ({ h, props }) => {
                  return h('el-button', {
                    props: { type: 'primary', size: 'mini' },
                    nativeOn: {
                      click: () => {
                      }
                    }
                  }, '查看下一级');
                }
              ]
            }
          ],
          selectable: true
        },
        form: {
          query: {
            pId: 0,
            status: '',
            keyword: ''
          },
          order: {}
        }
      },
      popup: {
        goodsCategoryAdd: {
          attr: {
            form: GoodsCategory.formUtil().generator('add', 'default')
          },
          fn: {
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = this.$refs.formValidateGoodsCategoryAdd.validate();
              if (ok) {
                GoodsCategory.sendApi('categoryAdd', { params: {}, data: form }, { errorTip: '商品分类添加失败' }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '商品分类添加成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
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
        goodsCategoryUpdate: {
          attr: {
            form: GoodsCategory.formUtil().generator('update', 'default'),
            info: {}
          },
          fn: {
            fill: (callback) => {
              callback(GoodsCategory, this.popup.goodsCategoryUpdate.attr.info);
            },
            openHandler: (ok, params) => {
              GoodsCategory.sendApi('categoryInfo', { params: { id: params.row._raw.id }, data: {} }, { errorTip: '获取商品分类信息失败' }).then(res => {
                const { status, data } = res;
                if (status) {
                  this.popup.goodsCategoryUpdate.attr.info = data;
                  ok();
                }
              });
            },
            closeHandler: (okCallback, errorCallback) => {
              const { ok, form } = this.$refs.formValidateGoodsCategoryUpdate.validate();
              if (ok) {
                GoodsCategory.sendApi('categoryUpdate', { params: { id: this.popup.goodsCategoryUpdate.attr.info.id }, data: form }, { errorTip: '商品分类更新失败' }).then(
                  (res) => {
                    const { status, data } = res;
                    if (status) {
                      okCallback();
                      this.$refs.listPage.query();
                      this.$message({
                        message: '商品分类更新成功',
                        type: 'success'
                      });
                    } else {
                      errorCallback();
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
  },
  methods: {
    add () { }
  }
};
</script>

<style lang="less">
.goods-category-list {
  background: white;
  .img-video-previewer {
    widows: 100px;
    height: 100px;
  }
  .popup-confirm {
    &_goods-category-add {
      padding: 20px;
    }
    &_goods-category-update {
      padding: 20px;
    }
  }
}
</style>
