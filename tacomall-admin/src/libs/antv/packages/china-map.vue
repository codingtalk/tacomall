<template>
  <div class="china-map" :id="cId">
  </div>
</template>

<script>
import { Scene } from '@antv/l7';
import { DrillDownLayer } from '@antv/l7-district';
import { Mapbox } from '@antv/l7-maps';

export default {
  name: 'ChinaMap',
  components: {},
  props: {
    data: Array,
    defs: Object,
    config: {
      type: Object,
      default: () => {
        return {
          chart: {}
        };
      }
    }
  },
  data () {
    return {
      cId: `${this.$options.name}_${this._uid}`,
      colors: ['#B8E1FF', '#7DAAFF', '#3D76DD', '#0047A5', '#001D70'],
      scene: null,
      DrillDownLayer: null
    };
  },
  created () {},
  mounted () {
    this.scene = new Scene(Object.assign({
      id: this.cId,
      // resizeEnable: true,
      // autoFit: true,
      // height: 200,
      // width: 200,
      map: new Mapbox({
        center: [116.2825, 39.9],
        pitch: 0,
        style: 'blank'
        // zoom: 5,
        // minZoom: 3,
        // maxZoom: 10
      })
    }), this.config.chart);
    this.scene.on('loaded', () => {
      this.DrillDownLayer = new DrillDownLayer(this.scene, {
        data: [],
        viewStart: 'Country',
        viewEnd: 'City',
        fill: {
          color: {
            field: 'NAME_CHN',
            values: this.colors
          }
        },
        popup: {
          enable: true,
          Html: props => {
            return `<span>${props.NAME_CHN}</span>`;
          }
        }
      });
    });
  },
  methods: {}
};
</script>
<style lang="less" >
.china-map{
  height: 100%;
}
</style>
