import 'ant-design-vue/dist/reset.css';
import axios from "axios";
import _ from "lodash";
import * as echarts from "echarts";
import injectComponents from "@/components";
import entities from "@/entity";
import {
  Input,
  Button,
  Radio,
  Menu,
  Dropdown,
  Switch,
  Select,
  Steps,
  Tag,
  message,
  Tabs,
  ImagePreviewGroup,
  Image,
  Tree,
  Timeline,
  TimelineItem,
} from "ant-design-vue";
import {
  install,
  localCache
} from "codingtalk-vue-toolkit";
import { rep } from "@/utils/navigator";

const APP_URL = {
  dev: "http://localhost:4000/sys/app",
  test: "",
  prod: "/open/sys/app",
};

const ENTITY_URL = {
  dev: "http://localhost:4000/sys/entity",
  test: "",
  prod: "/open/sys/entity",
};

export const TOKEN_KEY = "jwt-token";

export const antDesignInstall = (app) => {
  app.use(Input);
  app.use(Button);
  app.use(Radio);
  app.use(Menu);
  app.use(Switch);
  app.use(Select);
  app.use(Dropdown);
  app.use(Tag);
  app.use(Tabs);
  app.use(Steps);
  app.use(ImagePreviewGroup);
  app.use(Image);
  app.use(Tree);
  app.use(Timeline);
  app.use(TimelineItem);
  app.config.globalProperties.$message = message;
};

export const codingtalkVueToolkitInstall = async (app) => {
  await install(
    app,
    {
      libs: {
        _,
        axios,
        echarts,
      },
      entities,
      injectComponents,
      cache: {
        tokenKey: TOKEN_KEY,
        jwtKey: "user-access-token",
      },
      hook: {
        httpAfter: (res) => {
          const { data, status } = res;
          if (data?.code === 4200) {
            localCache.remove(TOKEN_KEY);
            rep("/login");
          }
        },
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
      }
    }
  );
};

export default {
  historyUrlKey: "local-url-history",
  historyUrlSize: 10,
};
