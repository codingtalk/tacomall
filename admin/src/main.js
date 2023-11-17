import { createApp } from "vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import router  from "@/router";
import get from "lodash/get";
import set from "lodash/set";
import cloneDeep from "lodash/cloneDeep";
import { antDesignInstall, codingtalkVueToolkitInstall } from "@/config";
import '@/assets/style/var.less';
import "@/assets/style/global.less";
import "codingtalk-vue-toolkit/dist_publish/index.css";

const app = createApp(App);
antDesignInstall(app);
await codingtalkVueToolkitInstall(app);
app.config.globalProperties.$_ = window.$_ = {
  get,
  set,
  cloneDeep,
};
app.use(createPinia());
app.use(router);
app.mount("#app");
