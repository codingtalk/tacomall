/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-03 15:34:12
 * @LastEditTime: 2021-10-27 11:17:47
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/store/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Vue from 'vue';
import Vuex from 'vuex';
import dayjs from 'dayjs';
import user from './user';

import { localCache } from '../utils/localCache';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    user
  },
  state: {
    isLogin: false,
    app: {
      platform: ''
    },
    now: dayjs()
  },
  mutations: {
    SET_IS_LOGIN (state, b) {
      state.isLogin = b;
    },
    SET_NOW (state, o) {
      state.now = o;
    }
  },
  actions: {
    initApp ({ dispatch, commit }) {
      localCache.getToken() && dispatch('user/getUserInfo');
      setInterval(() => {
        commit('SET_NOW', dayjs());
      }, 1000);
    }
  }
});

export default store;
