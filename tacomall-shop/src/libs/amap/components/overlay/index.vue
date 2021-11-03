<script>
import mixin from '../../base/mixin';

export default {
  name: 'AmapOverlay',
  mixins: [mixin()],
  props: {
    options: Object
  },
  data () {
    return {
      marker: null
    };
  },
  methods: {
    load () {
      let content = '';
      if (this.$slots.default && this.$slots.default.length) {
        content = this.tmpVM.$refs.node;
      }
      this.addMarker(content);
    },
    addMarker (content) {
      const { lng, lat } = this.options;
      const marker = new AMap.Marker({
        position: new AMap.LngLat(lng, lat),
        content: content,
        ...this.options
      });
      this.map && this.map.add(marker);
      this.marker = marker;
    },
    removeMarker () {
      this.map && this.map.remove(this.marker);
    }
  },
  render (h) {
    const slots = this.$slots.default || [];
    if (slots.length) {
      this.tmpVM.node = slots;
    }
    return null;
  },
  created () {
    this.tmpVM = new Vue({
      data () {
        return { node: '' };
      },
      render (h) {
        const { node } = this;
        return h('div', { ref: 'node', class: 'overlay' }, Array.isArray(node) ? node : [node]);
      }
    }).$mount();
  },
  destroyed () {
    this.tmpVM.$destroy();
    this.removeMarker();
  }
};
</script>

<style lang="less">
.overlay {
}
</style>
