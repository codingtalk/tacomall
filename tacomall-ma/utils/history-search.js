/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-22 14:30:48
 * @LastEditTime: 2021-11-05 16:23:48
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/utils/history-search.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import { appConfig } from '@/config/app';

const MAX_STORAGE_SIZE = 8;

export const historySearch = {
  add: (s) => {
    const arrHis = uni.getStorageSync(appConfig.historySearchKey) ? JSON.parse(uni.getStorageSync(appConfig.historySearchKey)) : [];
    if (arrHis.includes(s)) {
      arrHis.splice(arrHis.indexOf(s), 1);
    }
    if (arrHis.length >= MAX_STORAGE_SIZE) {
      arrHis.splice(arrHis.length - 1, 1);
    }
    arrHis.push(s);
    uni.setStorageSync(appConfig.historySearchKey, JSON.stringify(arrHis));
  },
  get: () => {
    return uni.getStorageSync(appConfig.historySearchKey) ? JSON.parse(uni.getStorageSync(appConfig.historySearchKey)) : [];
  },
  clear: () => {
    uni.removeStorageSync(appConfig.historySearchKey);
  }
};
