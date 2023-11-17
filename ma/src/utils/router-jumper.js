import { rep } from "codingtalk-uni-toolkit";

function getQueryValue(str, key) {
  const reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
  const r = str.match(reg);
  return r ? r[2] : null
}

export default function (opt) {
  const { query } = opt;
  setTimeout(() => {
    const sceneStr = decodeURIComponent(query.scene)
    const a = getQueryValue(sceneStr, 'a')
    const b = getQueryValue(sceneStr, 'b')
    if ((a && b) && a === "invite") {
      rep(`/pages/login/index?invite=${b}`)
    }
  }, 500);
}
