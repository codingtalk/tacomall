<!--
 * @Author: 码上talk|RC
 * @Date: 2020-12-18 09:11:25
 * @LastEditTime: 2021-05-20 22:49:57
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin_v2/src/libs/antv/packages/line.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="antv-line">
    <canvas :id="cId"></canvas>
  </div>
</template>

<script>
import { mixins } from '../mixins/index';
export default {
  name: 'AntvLine',
  mixins: [mixins],
  props: {
    config: {
      type: Object,
      default () {
        return {
          chart: {},
          axis: null
        };
      }
    }
  },
  methods: {
    render (chart) {
      chart.area()
        .position('x*y')
        .color('l(90) 0:#1890FF 1:#f7f7f7')
        .shape('smooth');
      chart.line()
        .position('x*y')
        .color('l(90) 0:#1890FF 1:#f7f7f7')
        .shape('smooth');
      chart.render();
    }
  }
};
</script>

<style lang="less">
@import "../less/index.less";
.antv-line {
  .antv;
  canvas {
    .antv-canvas;
  }
}
</style>
