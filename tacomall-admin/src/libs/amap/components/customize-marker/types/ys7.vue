<template>
  <div class="marker-ys7">
    <div class="y-icon" @click="$emit('iconClick')">
      <img src="/image/_test/camera.png" alt />
      <div class="y-name">{{ name }}</div>
    </div>
    <div class="y-previewer" v-if="active">
      <div class="y-previewer-name">
        <span>{{ name }}</span>
      </div>
      <ys7-previewer
        :load="ys7.fn.loadYs7"
        :config="{
          autoplay: 1,
          width: 350,
          height: 300,
        }"></ys7-previewer>
    </div>
  </div>
</template>

<script>
import ys7Previewer from '@/components/ys7/previewer';
export default {
  components: {
    ys7Previewer
  },
  props: {
    active: {
      type: Boolean
    },
    name: {
      type: String,
      default () {
        return '';
      }
    },
    config: {
      type: Object,
      default () {
        return {};
      }
    }
  },
  data () {
    return {
      ys7: {
        fn: {
          loadYs7: (callback) => {
            const { channelNo } = this.$_.get(this.config, '_raw');
            const { serial } = this.$_.get(this.config, '_raw.device');
            const { accessToken } = this.$_.get(this.config, '_raw.device.ys7Config');
            /* eslint-disable-next-line */
            callback(`${serial}/${channelNo}`, accessToken);
          }
        }
      }
    };
  }
};
</script>

<style lang="less">
.marker-ys7 {
  position: relative;
  .y-icon {
    img {
      width: 30px;
      height: 30px;
      transform: translate(-50%, -100%);
    }
    &:hover {
      .y-name {
        display: block;
      }
    }
  }
  .y-name {
    display: none;
    position: absolute;
    top: -48px;
    padding: 6px 9px;
    white-space: nowrap;
    font-size: 12px;
    text-align: center;
    transform: translateX(-50%);
    border-radius: 5px;
    background: rgba(49, 142, 253, 0.3);
  }
  .y-previewer {
    padding: 10px 10px 17px;
    transform: translate(-50%, -420px);
    background: url("~@/assets/images/map/video-border.png") no-repeat;
    background-size: 100% 100%;
    &-name {
      text-align: center;
      color: #53fdfd;
      font-size: 15px;
      margin-bottom: 6px;
      & > span {
        position: relative;
        &::before,
        &::after {
          position: absolute;
          content: "";
          width: 4px;
          height: 4px;
          top: 0;
          bottom: 0;
          margin: auto;
          border-radius: 50%;
          background: rgba(0, 255, 255, 0.44);
        }
        &::before {
          left: -10px;
        }
        &::after {
          right: -10px;
        }
      }
    }
  }
}
</style>
