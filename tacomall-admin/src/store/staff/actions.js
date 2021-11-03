/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-10-14 14:27:50
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/store/staff/actions.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import * as types from './mutation-types';
import { localCache } from '@/utils/localCache';
import { model } from '@/model';

const { TmStaff } = model.collection;

export const actions = {
  getStaffInfo ({ commit }) {
    return new Promise((resolve) => {
      TmStaff.sendApi('staffInfo').then(res => {
        const { status, data } = res;
        if (status) {
          const { isAdmin } = data;
          commit(types.SET_INFO, new TmStaff(data));
          commit('setIsLogin', true, { root: true });
          commit('setIsAdmin', !!isAdmin, { root: true });
          resolve(data);
        }
      });
    });
  },
  logout ({ commit }) {
    return new Promise((resolve, reject) => {
      TmStaff.sendApi('staffLogout').then(res => {
        const { status } = res;
        if (status) {
          localCache.clearToken();
          commit(types.SET_INFO, new TmStaff());
          commit('setIsLogin', false, { root: true });
          resolve();
        } else {
          reject(new Error('staffLogout'));
        }
      });
    });
  }
};
