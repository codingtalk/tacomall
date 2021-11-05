<!--
 * @Author: 码上talk|RC
 * @Date: 2021-09-17 15:14:08
 * @LastEditTime: 2021-11-05 15:41:14
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/components/list-page/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <view class="list-page">
    <scroll-view
      scroll-y="true"
      :refresher-enabled="config.enablePull"
      :refresher-triggered="isPulling"
      @refresherrefresh="onPull"
      @scrolltolower="loadMore"
    >
      <slot :list-data="listData" v-if="listData.length"></slot>
      <tempate v-else>
        <view class="p-empty">
          <empty :tips="['暂无相关信息']"></empty>
        </view>
      </tempate>
      <a-logo type="text"></a-logo>
    </scroll-view>
  </view>
</template>

<script>
import empty from '../empty';
import {
  model
} from '@/model';

const {
  collection
} = model;

export default {
  components: {
    empty
  },
  props: {
    autoLoad: {
      type: Boolean,
      default () {
        return true;
      }
    },
    config: {
      type: Object,
      default () {
        return {
          entity: '',
          action: ''
        };
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
    }
  },
  data () {
    return {
      loading: false,
      isPulling: false,
      isEnd: false,
      throttleQuery: this.$_.throttle(this.fresh, 2000),
      params: {
        pageIndex: 1,
        pageSize: 10
      },
      listData: [],
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
        return this.config.req({
          params: this.params,
          data: form
        });
      }
      if (this.config.entity && this.config.action) {
        this.loading = true;
        return collection[this.config.entity].sendApi(this.config.action, {
          params: this.params,
          data: this.form
        });
      }
    },
    fresh () {
      this.isEnd = false;
      this.params.pageIndex = 1;
      this.listData = [];
      this.loadMore();
    },
    onPull () {
      this.isPulling = true;
      this.fresh();
    },
    loadMore () {
      if (this.loading) {
        return;
      }
      if (this.isEnd) {
        this.toast('暂无更多数据~');
        return;
      }
      this._buildApi().then(res => {
        this.loading = false;
        this.isPulling = false;
        const {
          status,
          data,
          page
        } = res;
        if (status) {
          const l = (this.config.isRaw || this.config.req) ? data : data.map(i => Object.assign(new collection[
            this.config.entity](i), {
            _raw: i
          }));
          l.forEach(i => {
            this.listData.push(i);
          });
          this.total = page.total;
          if (this.listData.length === this.total) {
            this.isEnd = true;
            return;
          }
          this.params.pageIndex++;
        }
      });
    }
  },
  mounted () {
    this.autoLoad && this.loadMore();
  }
}
</script>

<style lang="less">
.list-page {
  height: 100%;

  scroll-view {
    height: 100%;

    .p-empty {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 500rpx;

      text {
        font-size: 30rpx;
        color: #646566;
      }
    }
  }

  ::-webkit-scrollbar {
    display: none;
    width: 0;
    height: 0;
    color: transparent;
  }
}
</style>