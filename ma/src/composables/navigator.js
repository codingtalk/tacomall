import { go } from "codingtalk-uni-toolkit";
import { storeToRefs } from "pinia";
import useAppStore from "@/store/app";

export { go }
export function loginGo(url) {
  const { isLogin } = storeToRefs(useAppStore());
  if (!isLogin.value) {
    uni.showModal({
      title: "登录提示",
      content: "当前功能需要登录，是否继续？",
      confirmText: "确定",
      cancelText: "取消",
      success: (res) => {
        const { confirm } = res;
        if (confirm) {
          go("/pages/login/index");
        }
      },
    });
    return;
  }
  go(url);
}
