/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-22 14:30:48
 * @LastEditTime: 2021-10-14 17:41:24
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-uniapp/store/user/actions.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import * as types from './mutation-types';
import { localCache } from '@/utils/localCache';
import { model } from '@/model';

const { Member } = model.collection;

export const actions = {
  getUserInfo: ({ dispatch, commit }) => {
    return Member.sendApi('info', { params: {}, data: {} }, { parse4Entity: true }).then(res => {
      const { status, data } = res;
      if (status) {
        commit('SET_IS_LOGIN', true, { root: true });
        commit(types.SET_MEMBER_INFO, data);
      }
    })
  },
  logout: ({ commit }) => {
    return new Promise((resolve) => {
      localCache.clearToken();
      commit('SET_IS_LOGIN', false, { root: true });
      commit(types.SET_MEMBER_INFO, new Member());
      resolve();
    });
  }
};
