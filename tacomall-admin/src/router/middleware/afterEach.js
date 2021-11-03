/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-13 14:02:20
 * @LastEditTime: 2021-10-14 16:40:00
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-admin/src/router/middleware/afterEach.js
 */

import { store } from '@/store';
import { localCache } from '@/utils/localCache';

export const middlewareAfterEach = (router) => {
  router.afterEach((from) => {
    localCache.addHistory({
      name: from.meta.title,
      url: from.fullPath
    });
    store.commit('sys/SET_HISTORY_BAR', localCache.getAllHistory(), { root: true });
  });
}
