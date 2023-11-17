import { localCache, toParse, toStringify } from "codingtalk-vue-toolkit";
import config from "@/config";
import useAppStore from "@/store/app";

const { historyUrlKey, historyUrlSize } = config;

export default (router) => {
  router.afterEach((from) => {
    const appStore = useAppStore();
    const item = {
      name: from.meta?.title,
      url: from.fullPath,
    };
    let arrHis = localCache.get(historyUrlKey)
      ? toParse(localCache.get(historyUrlKey))
      : [];
    if (arrHis.length >= historyUrlSize) {
      arrHis.splice(0, 1);
    }
    if (arrHis.map((i) => i.name).includes(item.name)) {
      arrHis.splice(arrHis.map((i) => i.name).indexOf(item.name), 1);
    }
    arrHis.push(item);
    localCache.set(historyUrlKey, toStringify(arrHis));
    appStore.setHistory(arrHis);
  });
};
