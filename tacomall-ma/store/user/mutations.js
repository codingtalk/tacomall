/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-03 15:34:12
 * @LastEditTime: 2021-09-07 19:31:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/store/user/mutations.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import * as types from './mutation-types';
import { model } from '@/model';

const { Member } = model.collection;

export const mutations = {
  [types.SET_MEMBER_INFO] (state, o) {
    state.info = new Member(o);
  }
};
