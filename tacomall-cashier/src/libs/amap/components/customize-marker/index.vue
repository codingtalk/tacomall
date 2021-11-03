<template>
  <amap-overlay :options="{ lng: location[0], lat: location[1] }">
    <div
      class="customize-marker"
      :style="{'bottom': `${finalConfig.offset.y}px`, left: `${finalConfig.offset.x}px`}">
      <div class="m-type">
        <marker-text
          ref="text"
          :active="active"
          :name="info.name"
          :config="finalConfig"
          v-if="type === 1"></marker-text>
        <marker-ys7
          ref="ys7"
          :active="active"
          :name="info.name"
          :config="finalConfig"
          v-if="type === 2"
          @iconClick="handleClick('ys7')"></marker-ys7>
        <marker-project
          ref="project"
          :active="active"
          :name="info.name"
          :config="finalConfig"
          v-if="type === 3"
          @iconClick="handleClick('project')"></marker-project>
      </div>
    </div>
  </amap-overlay>
</template>

<script>
import markerText from './types/text';
import markerYs7 from './types/ys7';
import markerProject from './types/project';
export default {
  components: {
    markerText,
    markerYs7,
    markerProject
  },
  data () {
    return {
      defaultConfig: {
        text: {
          offset: {
            x: 0,
            y: 0
          }
        },
        ys7: {
          offset: {
            x: 0,
            y: 0
          }
        },
        project: {
          offset: {
            x: 0,
            y: 0
          }
        }
      }
    };
  },
  props: {
    type: {
      default () {
        return 1;
      }
    },
    active: {
      type: Boolean,
      default () {
        return false;
      }
    },
    location: {
      type: Array
    },
    info: {
      type: Object,
      default () {
        return {
          name: '名称未填写'
        };
      }
    },
    config: {
      type: Object,
      default () {
        return {
        };
      }
    }
  },
  computed: {
    finalConfig () {
      let defaultInstanceConfig = {};
      if (this.type === 1) {
        defaultInstanceConfig = this.defaultConfig.text;
      }
      if (this.type === 2) {
        defaultInstanceConfig = this.defaultConfig.ys7;
      }
      if (this.type === 3) {
        defaultInstanceConfig = this.defaultConfig.project;
      }
      return Object.assign(defaultInstanceConfig, this.config);
    }
  },
  methods: {
    handleClick (e) {
      this.$emit('marker-click', e);
    }
  }

};
</script>

<style lang="less">
.customize-marker {
  position: relative;
  z-index: 100;
}
</style>
