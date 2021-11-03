<!--
 * @Author: 码上talk|RC
 * @Date: 2021-07-19 11:48:47
 * @LastEditTime: 2021-08-11 17:20:49
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/list-sort/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->

<template>
  <div class="list-sort">
    <div class="s-item" :key="key" @click="toggleSort(item)" v-for="(item, key) in l">
      <div class="i-lable">
        <span>{{ item.label }}</span>
      </div>
      <div class="i-icon">
        <i class="iconfont icon-xiangshangbeifen up" :class="{ active: item.sort === 'asc' }"></i>
        <i class="iconfont icon-xiangxia down" :class="{ active: item.sort === 'desc' }"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    sortList: [Array, Function],
    order: Object
  },
  data () {
    return {
      l: []
    };
  },
  watch: {
    l: {
      handler: function (e) {
        const m = {};
        e.forEach((i) => {
          if (this.order[i.value]) {
            this.order[i.value] = i.sort;
          }
        });
      },
      deep: true
    }
  },
  methods: {
    init () {
      this.l = (
        Array.isArray(this.sortList) ? this.sortList : this.sortList()
      ).map((i) => {
        if (!i.sort) {
          i.sort = 'desc';
        }
        return i;
      });
    },
    toggleSort (item) {
      if (item.sort === 'asc') {
        item.sort = 'desc';
      } else {
        item.sort = 'asc';
      }
    },
    reload () {
      this.init();
    }
  },
  created () {
    this.init();
  }
};
</script>

<style lang="less">
.list-sort {
  display: flex;
  align-items: center;
  .s-item {
    position: relative;
    padding: 5px 20px;
    border: 1px solid #dcdfe6;
    .i-lable {
      padding-right: 20px;
    }
    .i-icon {
      position: absolute;
      top: 50%;
      right: 10px;
      transform: translateY(-50%);
      .iconfont {
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        font-size: 15px;
      }
      .up {
        top: -15px;
      }
      .down {
        bottom: -15px;
      }
      .active {
        color: #1890ff;
      }
    }
    &:hover {
      cursor: pointer;
    }
    &:not(:first-child) {
      margin-left: 10px;
    }
  }
}
</style>
