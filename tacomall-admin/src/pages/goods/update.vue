<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-27 16:57:48
 * @LastEditTime: 2021-10-25 17:56:25
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/pages/goods/update.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="page goods-update">
    <div class="u-wrap">
      <form-validate
        ref="formValidGoodsUpdate"
        type="step"
        :form="form.value"
        :step-config="form.stepConfig"
        :fill="form.fn.fill"
        @ok="form.fn.okHandler"
      >
        <div class="w-goods" slot="step-0">
          <form-validate-field label="分类" :field="form.value.categoryId">
            <options-selector
              :mode="3"
              placeholder="商品分类"
              :config="{key: 'goodsCategory'}"
              @change="options.fn.goodsCategoryChangeHandler"
            ></options-selector>
          </form-validate-field>
          <form-validate-field label="规格分类" :field="form.value.specCategoryId">
            <options-selector
              :mode="3"
              placeholder="规格分类"
              :config="{key: 'goodsSpecCategory'}"
              @change="options.fn.goodsSpecCategoryChangeHandler"
            ></options-selector>
          </form-validate-field>
          <form-validate-field label="名称" :field="form.value.name">
            <el-input v-model="form.value.name.value"></el-input>
          </form-validate-field>
          <form-validate-field label="零售价" :field="form.value.amount">
            <el-input type="number" v-model="form.value.amount.value"></el-input>
          </form-validate-field>
          <form-validate-field label="是否在售" :field="form.value.status">
            <el-radio-group v-model="form.value.status.value">
              <el-radio :label="0">否</el-radio>
              <el-radio :label="1">是</el-radio>
            </el-radio-group>
          </form-validate-field>
          <form-validate-field label="封面">
            <img-video-uploader
              :max="1"
              @upload-success="form.value.thumbnail.value = $_.get($event, '[0].filename', '')"
            ></img-video-uploader>
          </form-validate-field>
          <form-validate-field label="轮播图">
            <img-video-uploader
              :max="10"
              @upload-success="form.value.gallery.value = $event.map(i => i.fullUrl())"
            ></img-video-uploader>
          </form-validate-field>
          <form-validate-field label="图文详情">
            <text-editor @change="form.value.detailMobileText.value = $event"></text-editor>
          </form-validate-field>
        </div>
        <div class="w-goods-items" slot="step-1">
          <div class="i-table">
            <form-validate-field>
              <el-table :data="tableData" style="width: 100%">
                <el-table-column label="规格">
                  <template slot-scope="scope">
                    <div :key="key" v-for="(item, key) in options.attr.goodsSpec">
                      <span>{{ item.label }}</span>
                      <el-select
                        size="small"
                        style="width:100px"
                        v-model="options.attr.defaultValue.goodsItemsSpec[scope.$index][key].valueId"
                        @change="(val) => {options.fn.goodsItemsSpecChangeHandler(scope.$index, key, item, val)}"
                      >
                        <el-option
                          v-for="(item1, key1) in item.children"
                          :label="item1.name"
                          :value="item1.id"
                          :key="key1"
                        ></el-option>
                      </el-select>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="条形码">
                  <template slot-scope="scope">
                    <el-input v-model="form.value.goodsItemsList.value[scope.$index].barCode.value"></el-input>
                  </template>
                </el-table-column>
                <el-table-column fixed="right" label="操作" width="200">
                  <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click.native="deleteGoodsItems(scope)">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </form-validate-field>
          </div>
          <div class="i-btn" @click="addGoodsItems()">
            <span>增加商品规格</span>
          </div>
        </div>
        <div class="w-result" slot="result">
          <div class="r-icon">
            <i class="iconfont icon-chenggong"></i>
          </div>
          <div class="r-text">
            <span>更新商品成功</span>
          </div>
          <div class="r-description">
            <p>
              提交结果页用于反馈一系列操作任务的处理结果，如果仅是简单操作，使用
              Message
              全局提示反馈即可。灰色区域可以显示一些补充的信息。
            </p>
          </div>
          <div class="r-link">
            <el-button
              type="primary"
              style="margin-left: 8px"
              @click.native="rep(`/goods/${goods.id}`)"
            >查看商品</el-button>
            <el-button style="margin-left: 8px" @click.native="rep(`/goods/list`)">返回列表</el-button>
          </div>
        </div>
      </form-validate>
    </div>
  </div>
</template>

<script>
import formValidate from '@/components/form-validate';
import formValidateField from '@/components/form-validate/field';
import optionsSelector from '@/components/options-selector';
import imgVideoUploader from '@/components/img-video-uploader';
import textEditor from '@/components/text-editor';
import { Request } from '@/model/request';
import { model } from '@/model';

const { Goods, GoodsItems } = model.collection;


export default {
  components: {
    formValidate,
    formValidateField,
    optionsSelector,
    imgVideoUploader,
    textEditor
  },
  data () {
    return {
      params: this.$route.params,
      request: new Request(),
      goods: new Goods(),
      options: {
        attr: {
          defaultValue: {
            category: [],
            specCategory: [],
            goodsSeries: [],
            thumbnail: [],
            goodsItemsSpec: [],
            goodsItemsThumbnail: []
          },
          goodsSpec: []
        },
        fn: {
          goodsCategoryChangeHandler: (val) => {
            this.options.attr.defaultValue.category = val.map(i => i.id);
            this.form.value.categoryId.value = val.map(i => i.id).join('>');
          },
          goodsSpecCategoryChangeHandler: (val) => {
            this.options.attr.defaultValue.specCategory = val.map(i => i.id);
            this.form.value.specCategoryId.value = val.map(i => i.id)[0];
          },
          goodsItemsSpecChangeHandler: (index, key, item, val) => {
            const attr = {
              key: item.name,
              value: item.children.filter(i => i.id === val)[0].name,
              valueId: val
            };
            const g = this.options.attr.defaultValue.goodsItemsSpec[index];
            for (let i = 0; i < g.length; i++) {
              if (g[i].valueId === val) {
                g[i] = attr;
                break;
              }
            }
            this.form.value.FILED(`goodsItemsList[${index}].specJson`).val = this.options.attr.defaultValue.goodsItemsSpec[index];
          }
        }
      },
      form: {
        value: Goods.formUtil().generator('update', 'default'),
        stepConfig: {
          list: [
            {
              _validateField: ['name', 'categoryId', 'amount'],
              _nextCallback: () => {
                return new Promise((resolve) => {
                  this.request.do('open', 'enum', 'lst', { params: { key: 'goodsSpec' }, data: { query: { specCategoryId: this.form.value.specCategoryId.value } } }).then(res => {
                    const { stauts, data } = res;
                    this.options.attr.goodsSpec = data;
                    resolve();
                  });
                });
              },
              title: '步骤一',
              description: '填写商品信息'
            },
            {
              title: '步骤二',
              description: '更新商品规格',
              btnText: '更新商品'
            }
          ]
        },
        fn: {
          fill: (callback) => {
            Goods.sendApi('info', { params: { id: this.params.id }, data: {} }, { parse4Entity: true, errorTip: '获取商品信息错误' }).then(res => {
              const { status, data } = res;
              if (status) {
                this.form.fn.setDefaultValue(data);
                this.goods = data;
                callback(Goods, data._raw);
              }
            });
          },
          setDefaultValue: (data) => {
            const { goodsItemsList } = data;
            this.options.attr.defaultValue.goodsItemsSpec = goodsItemsList.map(i => i.specJson);
          },
          okHandler: (ev) => {
            this.updateGoods(ev);
          }
        }
      },
      tableData: []
    };
  },
  watch: {
    'form.value.goodsItemsList.value': function (e) {
      this.tableData = [];
      e.forEach(i => {
        this.tableData.push({});
      });
    }
  },
  methods: {
    deleteGoodsItems (props) {
      this.options.attr.defaultValue.goodsItemsSpec.splice(props.$index, 1);
      this.form.value.goodsItemsList.value.splice(props.$index, 1);
    },
    addGoodsItems () {
      this.options.attr.defaultValue.goodsItemsSpec.push(this.options.attr.goodsSpec.map(i => ({
        key: '',
        value: '',
        valueId: ''
      })));
      const f = GoodsItems.formUtil().generator('update', 'default');
      this.form.value.goodsItemsList.value.push(f);
    },
    updateGoods (ev) {
      const { form, callback } = ev;
      Goods.sendApi('update', { params: {}, data: form }, { showLoading: true }).then(res => {
        const { status, data } = res;
        if (status) {
          this.goods = new Goods(data);
          callback();
        } else {
          this.$message({
            message: '商品更新失败',
            type: 'error'
          });
        }
      });
    }
  }
};
</script>

<style lang="less">
.goods-update {
  .u-wrap {
    width: 60%;
    margin: 0 auto;
    .w-goods {
      .ql-container {
        height: 500px;
      }
    }
    .w-goods-items {
      .i-btn {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 40px;
        margin-top: 20px;
        border: 1px dashed #e9e9e9;
      }
    }
    .w-result {
      display: flex;
      flex-direction: column;
      align-items: center;
      .r-icon {
        i {
          font-size: 100px;
          color: #19be6b;
        }
      }
      .r-text {
        font-size: 18px;
        color: #000;
      }
      .r-description {
        padding: 20px 0;
      }
    }
  }
}
</style>
