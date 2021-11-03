<template>
  <div class="g2-interval" :id="cId"></div>
</template>

<script>
import { mixins } from '../mixins/index';
export default {
  name: 'G2Interval',
  mixins: [mixins],
  props: {
    config: {
      type: Object,
      default () {
        return {
          chart: {
            autoFit: true
          },
          axis: null
        };
      }
    }
  },
  data () {
    return {};
  },
  methods: {
    render (chart) {
      chart.axis('x', {
        label: {
          align: 'left',
          // rotate: Math.PI / 4,
          autoHide: false,
          autoRotate: true
        },
        tickLine: null,
        subTickLine: null,
        grid: null
      });
      chart.axis('y', {
        tickLine: null,
        subTickLine: null,
        grid: null
      });
      chart.tooltip({
        shared: true,
        showMarkers: false,
        itemTpl: '<li class="g2-tooltip-list-item">{value}人</li>'
      });
      chart.interval().position('x*y');
      chart.interaction('active-region');
      chart.render();
    }
  }
};
</script>
<style lang="less">
.g2-interval {
  height: 100%;
  .g2-tooltip {
    background: #6294F9 !important;
    color: #fff !important;
    box-shadow: #6294F9 0px 0px 10px !important;
    &-list-item {
      text-align: center;
    }
  }
}
</style>
