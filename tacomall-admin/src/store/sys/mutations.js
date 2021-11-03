/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-02 10:07:23
 * @LastEditTime: 2021-10-14 14:29:36
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/store/sys/mutations.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import * as types from './mutation-types';
import menuJson from '@/config/menu.json';
import { router } from '@/router';
import dynamicRoutes from '@/router/routes/dynamic';

export const mutations = {
  [types.SET_LAYOUT_AUTH_LOADING] (state, b) {
    state.layoutAuthLoading = b;
  },
  [types.SET_MENU] (state, argvs) {
    const { isAdmin, accessRuleIds } = argvs;
    if (isAdmin) {
      state.menu = $_.cloneDeep(menuJson);
      return;
    }
    state.menu = $_.cloneDeep(menuJson).filter(i => {
      if (i.accessRuleId) {
        if (Array.isArray(i.accessRuleId)) {
          return $_.difference(accessRuleIds, i.accessRuleId).length !== accessRuleIds.length;
        }
        if (!accessRuleIds.includes(i.accessRuleId)) {
          return false;
        }
      }
      if (i.sub && i.sub.length) {
        i.sub = i.sub.filter(j => {
          if (!j.accessRuleId) {
            return true;
          }
          if (j.accessRuleId) {
            if (Array.isArray(j.accessRuleId)) {
              return $_.difference(accessRuleIds, j.accessRuleId).length !== accessRuleIds.length;
            }
            return accessRuleIds.includes(j.accessRuleId);
          }
          return false;
        });
        if (!i.sub.length) {
          return false;
        }
      }
      return true;
    });
  },
  [types.SET_IS_DYNAMIC_ROUTES_READY] (state, argvs) {
    const { isAdmin, accessRuleIds } = argvs;
    const routes = $_.cloneDeep(dynamicRoutes);
    if (!isAdmin) {
      routes.forEach(i => {
        i.children = i.children.filter(j => {
          if (j.meta && j.meta.accessRuleId) {
            if (Array.isArray(j.meta.accessRuleId)) {
              return $_.difference(accessRuleIds, j.meta.accessRuleId).length !== accessRuleIds.length;
            }
            return accessRuleIds.includes(j.meta.accessRuleId);
          }
          return true;
        });
      });
    }
    router.addRoutes(routes);
    state.isDaynamicRoutesReady = true;
  },
  [types.SET_ACCESS_RULE_IDS] (state, a) {
    state.accessRuleIds = a;
  },
  [types.SET_HISTORY_BAR] (state, a) {
    state.historyBar = a;
  }
};
