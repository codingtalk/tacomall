import _ from "lodash";
import {
  install,
  localCache,
  getGlobalVariable
} from "codingtalk-uni-toolkit";
import entities from "@/entity";

const APP_URL = {
  dev: "http://localhost:4000/sys/app",
  test: "",
  prod: "",
};

const ENTITY_URL = {
  dev: "http://localhost:4000/sys/entity",
  test: "",
  prod: "",
};

export const TOKEN_KEY = "jwt-token";
export const STATIC_PATH = filename => `https://jerrytang-static.oss-cn-shenzhen.aliyuncs.com/taocomall/ma/image/${filename}`
export const MP_APPID = "wx0038a1d24bb9eb99";

export const codingtalkUniToolkitInstall = (app) => {
  install(
    app,
    {
      libs: {
        _,
      },
      entities,
      cache: {
        tokenKey: TOKEN_KEY,
        jwtKey: "x-access-token",
      },
      remote: {
        oss: "//api.yun-kuai.com/open/oss/authorize",
        app: (() => {
          let url = APP_URL.dev;
          if (process.env.NODE_ENV === "test") {
            url = APP_URL.test;
          } else if (process.env.NODE_ENV === "production") {
            url = APP_URL.prod;
          }
          return url;
        })(),
        entity: (() => {
          let url = ENTITY_URL.dev;
          if (process.env.NODE_ENV === "test") {
            url = ENTITY_URL.test;
          } else if (process.env.NODE_ENV === "production") {
            url = ENTITY_URL.prod;
          }
          return url;
        })()
      },
      hook: {
        httpAfter: (res) => {
          const { data, status } = res;
          if (data?.code === 4200) {
            localCache.remove(TOKEN_KEY);
          }
        },
      }
    }
  );
};

export default {};
