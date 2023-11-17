import { localCache } from "codingtalk-vue-toolkit";
import { TOKEN_KEY } from "@/config";
import routesDynamic from "@/router/routes/dynamic";
import useAppStore from "@/store/app";

export default (router) => {
  router.beforeEach(async (to) => {
    const appStore = useAppStore();
    if (!appStore.isLoadedRoute) {
      if (!localCache.get(TOKEN_KEY)) {
        if (to.path === "/login") {
          return true;
        } else {
          return { name: "login" };
        }
      }
      await appStore.accessInit();
      router.push(to.fullPath);
      return true;
    } else {
      if (!to.matched.length) {
        const routes = [];
        routesDynamic.forEach((i) => {
          i.children &&
            i.children.forEach((j) => {
              routes.push({
                path: j.path,
              });
            });
        });
        if (routes.map((i) => i.path).includes(to.path)) {
          return { name: "403" };
        }
        return { name: "404", query: { redirect: to.fullPath } };
      }
      if (to.matched.some((record) => record.meta.requiresAuth)) {
        if (!localCache.get(TOKEN_KEY)) {
          return { name: "login", query: { redirect: to.fullPath } };
        }
      }
      return true;
    }
  });
};
