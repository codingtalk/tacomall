import { ref, onMounted } from "vue";
import { onPullDownRefresh, onLoad } from "@dcloudio/uni-app";
import { storeToRefs } from "pinia";
import useAppStore from "@/store/app";
import _ from "lodash";
import { TOKEN_KEY } from "@/config";
import { localCache, rep, toParse, http, getGlobalVariable } from "codingtalk-uni-toolkit";

export const checkToken = () => {
  onMounted(() => {
    if (!localCache.get(TOKEN_KEY)) {
      uni.showToast({
        title: '当前页面需要登录首选，请登录后浏览',
        duration: 2000
      });
      setTimeout(() => {
        rep('/pages/login/index')
      }, 1000)
    }
  })
}

export const pageLoad = (key, body, callback, opt = { autoLoad: true }) => {
  let info = ref({});
  let urlQueries = ref({})
  const queryInfo = (obj) => {
    const { api: apiJson } = getGlobalVariable('app');
    return new Promise((resolve) => {
      http.request({
        baseURL: apiJson['ma'].hostname,
        url: apiJson['ma'].modules['page']['info'].path,
        params: {
          key
        },
        data: {
          ...body,
          ...obj,
          ...opt?.getUrlQueries ? opt?.getUrlQueries(urlQueries.value) : {}
        }
      })
        .then((res) => {
          const { status, data } = res;
          if (status) {
            info.value = data;
            resolve();
          }
        });
    });
  };
  onLoad((e) => {
    urlQueries.value = e
    if (opt?.getUrlQueries && !opt?.getUrlQueries(e)) {
      return
    }
    if (!opt.autoLoad) {
      return
    }
    queryInfo().then(() => {
      callback && callback();
    });
  })
  return {
    queryInfo,
    resetInfo: (val = {}) => {
      info.value = val
    },
    getVal: (k, d, opt) => {
      let v = _.get(info.value, k, d || "--")
      if (opt?.toParse) {
        v = toParse(v)
      }
      return v;
    }
  };
};

export function pullDownRefresh(callback) {
  const { isLogin } = storeToRefs(useAppStore());
  onPullDownRefresh(() => {
    if (!isLogin.value) {
      uni.stopPullDownRefresh();
    }
    callback(() => {
      uni.stopPullDownRefresh();
    });
  });
}
