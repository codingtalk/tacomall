import { ref, onBeforeMount} from "vue";
import entities from "@/entity";
import { getObjVal } from "codingtalk-vue-toolkit";
import { useRouter, useRoute } from "vue-router";

export default function (entity, action, opt) {
  onBeforeMount(() => { 
    opt?.isAutoLoad && load();
  })
  const router = useRouter();
  const { id } = router.currentRoute.value.params;
  const _info = ref(null);
  function getCtXVal(k) {
    return k ? getObjVal(_info.value, k) : _info.value;
  }
  function load() {
    return new Promise((resolve) => {
      entities[entity]
        .sendApi(action, { params: { id }, body: {} }, {parse4Entity: true, enableRemoteConfig: entity})
        .then((res) => {
          const { status, data } = res;
          if (status) {
            _info.value = data;
            opt?.callback && opt.callback();
            resolve(data);
          }
        });
    });
  }
  return {
    id,
    getCtXVal,
    load,
  };
}
