import { defineStore } from "pinia";
import { ready, getGlobalVariable } from "codingtalk-uni-toolkit";
import { localCache } from "codingtalk-core"
import { TOKEN_KEY } from "@/config";
import { Member } from "@/entity";
import dayjs from 'dayjs';


export default defineStore({
  id: "app",
  state: () => ({
    isLogin: false,
    member: null,
    now: dayjs(),
    address: null
  }),
  getters: {},
  actions: {
    init() {
      ready().then(() => {
        localCache.get(TOKEN_KEY) && this.getMemberInfo();
      })
      setInterval(() => {
        this.now = dayjs();
      }, 1000);
    },
    getMemberInfo() {
      return new Promise((resolve) => {
        Member.sendApi("info", { params: {} , body: {}}, { parse4Entity: true }).then((res) => {
          const { status, data } = res;
          if (status) {
            this.isLogin = true;
            this.member = data;
            resolve(data);
          }
        });
      });
    },
    logout() {
      return new Promise((resolve, reject) => {
        this.isLogin = false;
        this.member = new Member();
        localCache.remove(TOKEN_KEY);
        resolve();
      });
    }
  },
});
