import { createSSRApp } from "vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import { codingtalkUniToolkitInstall } from "@/config";
import $filter from "./utils/filter";
import uviewPlus from 'uview-plus'

export function createApp() {
  const app = createSSRApp(App);
  app.config.globalProperties.$filter = $filter;
  app.use(createPinia());
  app.use(uviewPlus)
  codingtalkUniToolkitInstall(app);
  return {
    app,
  };
}
