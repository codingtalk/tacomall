/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:29
 * @LastEditTime: 2021-10-14 16:37:58
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/router/middleware/beforeEach.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import { localCache } from '@/utils/localCache';
import { store } from '@/store';
import dynamicRoutes from '@/router/routes/dynamic';

export const middlewareBeforeEach = (router) => {
  router.beforeEach(async (to, from, next) => {
    if (!store.state.sys.isDaynamicRoutesReady) {
      if (!localCache.getToken() && to.path === '/login') {
        next();
        return;
      }
      await store.dispatch('sys/loadStaffAccessRuleList', {});
      next({
        ...to,
        replace: true
      });
    } else {
      if (!to.matched.length) {
        const routes = [];
        dynamicRoutes.forEach(i => {
          i.children && i.children.forEach(j => {
            routes.push({
              path: j.path
            });
          });
        });
        if (routes.map(i => i.path).includes(to.path)) {
          next({ path: '/403' });
          return;
        }
        next({ path: '/404', query: { redirect: to.fullPath } });
        return;
      }
      if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!localCache.getToken()) {
          next({
            path: '/login',
            query: { redirect: to.fullPath }
          });
        } else {
          next();
        }
      } else {
        next();
      }
    }
  });
}
