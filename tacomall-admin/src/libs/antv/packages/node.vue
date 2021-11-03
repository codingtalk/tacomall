<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-19 17:25:50
 * @LastEditTime: 2021-05-23 20:25:14
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin_v2/src/libs/antv/packages/node.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div :id="cId" class="antv-node"></div>
</template>

<script>
/*eslint-disable */
import { mixins } from '../mixins/index'
export default {
  name: 'AntvNode',
  mixins: [mixins],
  props: {
    config: {
      type: Object,
      default () {
        return {
          chart: {},
        }
      }
    }
  },
  watch: {
    'data': {
      handler: function () {
        this.refresh()
      },
      deep: true
    }
  },
  methods: {
    refresh () {
      this.G6.chart.changeData(this.data)
      this.G6.chart.fitView(20)
    }
  }
}
</script>

<style lang="less">
@import "../less/index.less";
.antv-node {
  .antv;
  canvas {
    .antv-canvas;
  }
}
</style>