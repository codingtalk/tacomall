<script>
import useAppStore from '@/store/app';
import routerJumper from './utils/router-jumper';

export default {
    onLaunch: function (opt) {
        console.log('App Launch');
        routerJumper(opt);
        useAppStore()?.init();
    },
    onShow: function () {
        console.log('App Show');
    },
    onHide: function () {
        console.log('App Hide');
    },
    setup() {
    }
}
</script>

<style lang="less">
/*每个页面公共css */
@import "./assets/style/global.less";
@import "./assets/fonts/iconfont.less";
</style>
