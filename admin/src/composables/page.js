import { http, getObjVal, getGlobalVariable } from "codingtalk-vue-toolkit";
import { ref, onBeforeMount } from "vue";

const { api: apiJson } = getGlobalVariable('app');

export default (key, dataFilter) => {
  onBeforeMount(() => {
    load();
  });
  const _pageInfo = ref(null);
  const getVal = (k, d) => {
    return k ? getObjVal(_pageInfo.value, k, d) : _pageInfo.value;
  };
  function load() {
    http
      .request({
        baseURL: apiJson['web'].hostname,
        url: apiJson['web'].modules['page']['info'].path,
        params: {
          key
        },
        data: {}
      })
      .then((res) => {
        const { status, data } = res;
        if (status) {
          _pageInfo.value = data;
        }
      });
  }
  return {
    getVal,
    load,
  };
};
