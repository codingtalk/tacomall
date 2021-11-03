<template>
  <div ref="amap" id="amap" class="amap">
    <slot></slot>
  </div>
</template>

<script>
import eventBind from '../../base/event-bind';

export default {
  name: 'Amap',
  props: {
    config: {
      type: Object,
      default () {
        return {
          center: [116.33719, 39.942384],
          zoom: 15
        };
      }
    }
  },
  data () {
    return {
      map: null,
      slotObserver: new MutationObserver(this._slotChange)
    };
  },
  methods: {
    _slotChange () {
      this.$children.forEach(vm => {
        vm.$children.length && vm.$children[0].load();
      });
    },
    init () {
      this.insertScript().then(() => {
        this.initMap();
      });
    },
    initMap () {
      const map = new global.AMap.Map('amap', this.config);
      this.map = map;
      eventBind.call(this, map);
    },
    insertScript () {
      const bp = document.createElement('script');
      const curProtocol = window.location.protocol.split(':')[0];
      if (curProtocol === 'https') {
        bp.src = `https://webapi.amap.com/maps?v=${this._AMAP().version}&key=${this._AMAP().ak}`;
      } else {
        bp.src = `http://webapi.amap.com/maps?v=${this._AMAP().version}&key=${this._AMAP().ak}`;
      }
      const s = document.getElementsByTagName('script')[0];
      s.parentNode.insertBefore(bp, s);
      return new Promise((resolve, reject) => {
        bp.onload = () => {
          resolve(AMap);
        };
      });
    },
    setMapStyle (stylesId = '53d0743d67111847361d9e1d544ae69d') {
      this.map.setMapStyle(`amap://styles/${stylesId}`);
    },
    setMapCenter (center = [113.133683, 23.022703], immediately = false) {
      this.map.setCenter(center, immediately);
    },
    setMapZoom (zoom = 11, immediately = false) {
      this.map.setZoom(zoom, immediately);
    },
    getMapZoom () {
      return this.map.getZoom();
    },
    setLayers (layers) {
      this.map.setLayers([
        AMap.createDefaultLayer(),
        ...layers
      ]);
    },
    setFitView () {
      this.map.setFitView(
        ...arguments
      );
    }
  },
  created () {
    this.$nextTick(() => {
      this.slotObserver.observe(this.$refs.amap, { childList: true });
    });
  },
  mounted () {
    this.init();
  }
};
</script>

<style lang="less">
.amap {
  width: 100%;
  height: 100%;
}
</style>
