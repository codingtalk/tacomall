/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-10-14 14:29:09
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/store/sys/actions.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import * as types from './mutation-types';
import { model } from '@/model';

const { TmStaff } = model.collection;

export const actions = {
  async loadStaffAccessRuleList ({ dispatch, commit }) {
    const staffInfo = await dispatch('staff/getStaffInfo', {}, { root: true });
    const { isAdmin, accessExtraJson } = staffInfo;
    return new Promise((resolve) => {
      TmStaff.sendApi('staffAccessRuleList').then(res => {
        const { status, data } = res;
        if (status) {
          const accessRuleIds = data.map(i => i.id);
          commit(types.SET_MENU, { isAdmin, accessRuleIds });
          commit(types.SET_IS_DYNAMIC_ROUTES_READY, { isAdmin, accessRuleIds });
          commit(types.SET_ACCESS_RULE_IDS, accessRuleIds);
          resolve();
        }
      });
    });
  }
};
