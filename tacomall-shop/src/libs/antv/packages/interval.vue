<template>
  <div class="antv-interval">
    <canvas :id="cId"></canvas>
  </div>
</template>

<script>
import { mixins } from '../mixins/index';
export default {
  name: 'AntvInterval',
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
      chart.interval().position('x*y');
      chart.render();
    }
  }
};
</script>

<style lang="less">
@import '../less/index.less';
.antv-interval {
  .antv;
  canvas {
    .antv-canvas;
  }
}
</style>
