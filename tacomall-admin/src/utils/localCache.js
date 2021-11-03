/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-13 14:02:20
 * @LastEditTime: 2021-10-14 16:43:19
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-admin/src/utils/localCache.js
 */
/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-13 14:02:20
 * @LastEditTime: 2021-10-14 14:26:19
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-admin/src/utils/localStorage.js
 */

import { appConfig } from '@/config/app';

const HISTORY_MAX_LEN = 10;

export const localCache = {
  setToken: s => {
    localStorage.setItem(appConfig.tokenKey, s);
  },
  getToken: () => {
    return localStorage.getItem(appConfig.tokenKey);
  },
  clearToken: () => {
    localStorage.removeItem(appConfig.tokenKey);
  },
  addHistory: (o) => {
    const arrHis = localStorage.getItem(appConfig.localHistoryKey) ? JSON.parse(localStorage.getItem(appConfig.localHistoryKey)) : [];
    if (arrHis.length >= HISTORY_MAX_LEN) {
      arrHis.splice(0, 1);
    }
    if (arrHis.map(i => i.name).includes(o.name)) {
      arrHis.splice(arrHis.map(i => i.name).indexOf(o.name), 1);
    }
    arrHis.push(o);
    localStorage.setItem(appConfig.localHistoryKey, JSON.stringify(arrHis));
  },
  deleteHistory: (index) => {
    const arrHis = localStorage.getItem(appConfig.localHistoryKey) ? JSON.parse(localStorage.getItem(appConfig.localHistoryKey)) : [];
    arrHis.splice(index, 1);
    localStorage.setItem(appConfig.localHistoryKey, JSON.stringify(arrHis));
  },
  getAllHistory: () => {
    return localStorage.getItem(appConfig.localHistoryKey) ? JSON.parse(localStorage.getItem(appConfig.localHistoryKey)) : [];
  }
};

