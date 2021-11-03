<template>
  <div class="list-page">
    <div class="p-toolbar">
      <div class="t-left">
        <div class="l-query">
          <slot name="query"></slot>
        </div>
        <div class="l-btn">
          <el-button-group>
            <el-button size="small" @click.native="reset">重置</el-button>
            <el-button type="primary" size="small" @click.native="query">搜索</el-button>
          </el-button-group>
        </div>
      </div>
      <div class="t-right">
        <div class="r-item">
          <p v-if="config.selectable">
            已选（
            <em style="color:#1890ff;">{{ select.attr.active.length }}</em>）
          </p>
        </div>
        <div class="r-item">
          <el-button-group>
            <slot name="action"></slot>
            <el-button
              size="small"
              @click.native="$refs.popupConfirmExcelExport.open({triggerClose: popup.excelExport.fn.closeHandler})"
              v-if="popup.excelExport.attr.enable"
            >导出</el-button>
            <el-button
              type="primary"
              size="small"
              @click.native="$emit('add')"
              v-if="!(Array.isArray(hiddenAction) ? hiddenAction : hiddenAction()).includes('add')"
            >添加</el-button>
          </el-button-group>
        </div>
      </div>
    </div>
    <div class="p-table">
      <div class="t-loading" v-if="loading">
        <loading></loading>
      </div>
      <div class="t-content" v-else>
        <template v-for="(item) in tableData">
          <slot name="list" :item="item" v-if="$scopedSlots.list"></slot>
        </template>
        <elastic-table
          :table-data="tableData"
          :selectable="config.selectable"
          :column="config.column"
          @select="select.fn.handleSelectionChange"
          v-if="!$scopedSlots.list"
        ></elastic-table>
      </div>
    </div>
    <div class="p-page">
      <div class="p-left">
        <p>
          共查询到结果
          <em>{{ total }}</em>条
        </p>
      </div>
      <div class="p-right">
        <div class="r-index">
          <el-pagination
            background
            layout="prev, pager, next"
            :page-size="pageParams.pageSize"
            :total="total"
            @current-change="
              (e) => {
                this.pageChange('pageIndex', e);
              }
            "
          ></el-pagination>
        </div>
        <div class="r-size">
          <el-select
            v-model="pageParams.pageSize"
            placeholder="请选择"
            size="mini"
            @change="
              () => {
                this.pageChange('pageSize');
              }
            "
          >
            <el-option
              v-for="item in pageConfig.size"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </div>
      </div>
    </div>
    <popup-confirm ref="popupConfirmExcelExport" title="导出EXCEL">
      <div class="popup-confirm_excel-export">
        <div class="e-item">
          <div class="i-label">
            <span>导出类型</span>
          </div>
          <div class="i-value">
            <el-select
              size="small"
              placeholder="导出类型"
              v-model="popup.excelExport.attr.optionsActive"
            >
              <el-option
                v-for="item in popup.excelExport.attr.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </div>
        </div>
        <div class="e-item" v-if="$_.get(excelExportActive, 'field', []).length">
          <div class="i-label">
            <span>导出内容</span>
          </div>
          <div class="i-value">
            <el-checkbox-group v-model=" popup.excelExport.attr.fieldActive">
              <el-checkbox
                :label="item.label"
                :checked="item.select"
                :key="key"
                v-for="(item, key) in $_.get(excelExportActive, 'field', [])"
              ></el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
        <slot :name="`excel-config_${popup.excelExport.attr.optionsActive}`"></slot>
      </div>
    </popup-confirm>
  </div>
</template>

<script>
import FileSaver from 'file-saver';
import XLSX from 'xlsx';
import popupConfirm from '@/components/popup/type/confirm';
import elasticTable from '../elastic-table';
import loading from '../loading/index.vue';
import { Request } from '@/model/request';
import downloader from '@/utils/downloader';
import { model } from '@/model';
const entitys = model.collection;

export default {
  components: {
    elasticTable,
    popupConfirm,
    loading
  },
  props: {
    config: {
      type: Object,
      default () {
        return {
          entity: '',
          action: '',
          column: [],
          selectable: false
        };
      }
    },
    hiddenAction: {
      default () {
        return [];
      }
    },
    form: {
      type: Object,
      default () {
        return {
          query: {},
          order: {}
        };
      }
    },
    loadExcelConfig: {
      type: Function
    }
  },
  data () {
    return {
      loading: false,
      request: new Request(),
      throttleQuery: this.$_.throttle(this.query, 2000),
      pageConfig: {
        size: [{
          value: 10,
          label: 10
        }, {
          value: 20,
          label: 20
        }, {
          value: 50,
          label: 50
        }]
      },
      pageParams: {
        pageIndex: 1,
        pageSize: 10
      },
      initFormMetaData: {
        query: {},
        order: {}
      },
      tableData: [],
      total: 0,
      select: {
        attr: {
          active: []
        },
        fn: {
          handleSelectionChange: (e) => {
            this.select.attr.active = e;
          }
        }
      },
      popup: {
        excelExport: {
          attr: {
            enable: false,
            options: [],
            optionsActive: -1,
            fieldActive: []
          },
          fn: {
            closeHandler: (ok, error) => {
              if (this.popup.excelExport.attr.optionsActive === -1) {
                this.exportExcel();
                ok();
                return;
              }
              const { domain, action } = this.excelExportActive.download;
              this.request.do('py', domain, action, this.loadExcelConfig ? this.loadExcelConfig() : { params: {}, data: {} }).then(res => {
                const { success, data } = res;
                if (success) {
                  ok();
                  const { oss_url } = data;
                  downloader.do(oss_url);
                } else {
                  error();
                  this.$message({
                    message: '文件下载失败',
                    type: 'error'
                  });
                }
              });
            }
          }
        }
      }
    };
  },
  computed: {
    excelExportActive () {
      for (let i = 0; i < this.popup.excelExport.attr.options.length; i++) {
        if (this.popup.excelExport.attr.options[i].value === this.popup.excelExport.attr.optionsActive) {
          return this.popup.excelExport.attr.options[i];
        }
      }
    }
  },
  watch: {
    form: {
      handler: function () {
        this.throttleQuery();
      },
      deep: true
    }
  },
  methods: {
    _buildExcel () {
      if (this.config.entity && entitys[this.config.entity] && entitys[this.config.entity]._excel) {
        this.popup.excelExport.attr.enable = true;
        this.popup.excelExport.attr.options = entitys[this.config.entity]._excel;
      }
    },
    _buildApi () {
      let form = this.form;
      if (this.config.formQueryPreHook) {
        const hookData = this.config.formQueryPreHook();
        for (let i in hookData) {
          form.query[i] = hookData[i] ? hookData[i] : form.query[i];
        }
      }
      if (this.config.req) {
        this.loading = true;
        return this.config.req({ params: this.pageParams, data: form });
      }
      if (this.config.entity && this.config.action) {
        this.loading = true;
        return entitys[this.config.entity].sendApi(this.config.action, {
          params: this.pageParams,
          data: this.form
        });
      }
    },
    getPage () {
      this._buildApi().then(res => {
        this.loading = false;
        const { status, data, page } = res;
        if (status) {
          this.tableData = (this.config.isRaw || this.config.req) ? data : data.map(i => Object.assign(new entitys[this.config.entity](i), { _raw: i }));
          this.total = page.total;
        }
      });
    },
    pageChange (type, n) {
      this.pageParams.pageIndex = 1;
      if (n) {
        this.pageParams[type] = n;
      }
      this.getPage();
    },
    reset () {
      this.$confirm('是否重置搜索条件?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const { query, order } = this.initFormMetaData;
        for (const i in query) {
          this.$set(this.form.query, i, query[i]);
        }
        for (const i in order) {
          this.$set(this.form.order, i, order[i]);
        }
        this.$emit('form-reset');
      }).catch(() => {
      });
    },
    query () {
      this.pageParams.pageIndex = 1;
      this.getPage();
    },
    exportExcel () {
      const eleFix = document.querySelector('.el-table__fixed');
      let wb = XLSX.utils.table_to_book(document.querySelector('.el-table'));
      if (eleFix) {
        wb = XLSX.utils.table_to_book(document.querySelector('.el-table').removeChild(eleFix));
        document.querySelector('.el-table').appendChild(eleFix);
      }
      const wbout = XLSX.write(wb, {
        bookType: 'xlsx',
        bookSST: true,
        type: 'array'
      });
      try {
        FileSaver.saveAs(
          new Blob([wbout], { type: 'application/octet-stream' }),
          (new Date()).valueOf() + '.xlsx'
        );
      } catch (e) {
        if (typeof console !== 'undefined') {
          this.$log4js.log(e);
        }
      }
      return wbout;
    }
  },
  created () {
    this.initFormMetaData = this.$_.cloneDeep(this.form);
    this._buildExcel();
    this.getPage();
  }
};
</script>

<style lang="less">
.list-page {
  position: relative;
  .p-toolbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 80px;
    .t-left {
      display: flex;
      align-items: center;
      .l-query {
        display: flex;
        .q-block {
          display: flex;
          align-items: center;
          .b-item {
            width: 150px;
            &:not(:first-child) {
              margin-left: 10px;
            }
          }
          &:not(:first-child) {
            padding-left: 10px;
            margin-left: 10px;
            border-left: 1px solid #ebeef5;
          }
        }
      }
      .l-btn {
        margin-left: 20px;
      }
    }
    .t-right {
      display: flex;
      align-items: center;
    }
  }
  .p-table {
    position: relative;
    min-height: 200px;
    .t-content {
    }
    .t-loading {
      position: absolute;
      top: 0;
      bottom: 0;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      z-index: 9;
    }
  }
  .p-page {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 80px;
    .p-right {
      display: flex;
      align-items: center;
      .r-size {
        width: 90px;
        margin-left: 10px;
      }
    }
  }
  .popup-confirm {
    &_excel-export {
      padding: 20px;
      .e-item {
        display: flex;
        align-items: center;
        .i-label {
          width: 100px;
        }
        &:not(:first-child) {
          margin-top: 20px;
        }
      }
    }
  }
}
</style>
