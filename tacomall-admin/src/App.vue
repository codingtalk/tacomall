<!--
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:28
 * @LastEditTime: 2021-10-14 16:25:27
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/App.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div id="app">
    <div class="a-notity">
      <!-- @TODO Web update notify -->
    </div>
    <div class="a-main">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'App',
  data () {
    return {
      timerUpdate: null,
      isShowDialogUpdate: false,
    }
  },
  computed: {
    ...mapState('sys', ['ws'])
  },
  watch: {
    'ws.update.isWillUpdate': function (e) {
      if (!e) {
        this.timerUpdate && clearInterval(this.timerUpdate);
        this.isShowDialogUpdate = false;
        return;
      } else {
        this.keepDialogUpdate();
        this.getUpdateVersion();
      }
    }
  },
  created () {
    this.appInit();
  },
  methods: {
    ...mapActions(['appInit']),
    ...mapActions('sys', ['getUpdateVersion']),
    keepDialogUpdate () {
      this.timerUpdate = setInterval(() => {
        if (this.$dayjs().diff(this.$dayjs(this.ws.update.info.updateStartTime)) > 0) {
          this.isShowDialogUpdate = true;
        }
      }, 3000);
    }
  }
};
</script>

<style lang="less">
#app {
  position: absolute;
  top: 0px;
  bottom: 0;
  width: 100%;
  min-width: 1200px;
  min-height: 800px;
  display: flex;
  flex-direction: column;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  line-height: 1.5;
  font-size: 14px;
  background: #f7f7f7;
  color: #515a6e;
  .a-notity {
  }
  .a-main {
    flex: 1;
    overflow-y: hidden;
  }
}
::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #535353;

  &:hover {
    background: #409eff;
  }
}

::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  background: #ededed;
}
</style>
