<template>
  <div class="marker-text">
    <div
      class="c-name"
      :style="{width: `${finalConfig.shape.width}px`, background: finalConfig.shape.color}">
      <p :style="{color: finalConfig.word.color}">{{ name }}</p>
    </div>
    <div
      class="c-footer"
      :style="{borderTop: `${finalConfig.shape.height}px solid ${finalConfig.shape.color}`}"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      defaultConfig: {
        word: {
          width: 100,
          color: '#000'
        },
        shape: {
          height: 100,
          color: '#fff'
        }
      }
    };
  },
  props: {
    name: {
      type: String,
      default () {
        return '未填写';
      }
    },
    config: {
      type: Object,
      default () {
        return {
          word: {
            color: '#000'
          },
          shape: {
            color: '#fff'
          }
        };
      }
    }
  },
  computed: {
    finalConfig () {
      return Object.assign(this.defaultConfig, this.config);
    }
  }
};
</script>

<style lang="less">
.marker-text {
    .c-name {
        display: flex;
        align-items: center;
        justify-content: center;
        min-width: 100px;
        max-width: 300px;
        padding: 5px;
        box-shadow: 0 1px 2.5px 0 rgba(0, 0, 0, 0.18);
        background: white;
    }
    .c-footer {
        width: 0;
        height: 0;
        border-top: 100px solid white;
        border-right: 20px solid transparent;
    }
}
</style>
