<!--
 * @Author: 码上talk|RC
 * @Date: 2021-09-08 16:08:37
 * @LastEditTime: 2021-10-21 15:58:35
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/App.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<script>
import { mapActions } from "vuex";

export default {
  methods: {
    ...mapActions(["initApp"]),
  },
  onLaunch: function (options) {
    this.initApp();
  },
  onShow: function (options) {},
  onHide: function () {},
};
</script>

<style lang="scss">
@import "@/uni_modules/uview-ui/index.scss";
</style>

<style lang="less">
@import "./assets/less/var.less";
@import "./assets/less/global.less";
@import "./assets/fonts/iconfont";
</style>
