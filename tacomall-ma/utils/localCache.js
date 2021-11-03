/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-13 14:06:50
 * @LastEditTime: 2021-10-14 16:33:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-uniapp/utils/localStorage.js
 */
import { appConfig } from '@/config/app';

export const localCache = {
  setToken: (s) => {
    uni.setStorageSync(appConfig.tokenKey, s);
  },
  getToken: () => {
    return uni.getStorageSync(appConfig.tokenKey);
  },
  clearToken: () => {
    uni.removeStorageSync(appConfig.tokenKey);
  },
  setLocalVersion: (s) => {
    uni.setStorageSync(appConfig.localVersionKey, s);
  },
  getLocalVersion: () => {
    return uni.getStorageSync(appConfig.localVersionKey);
  },
  clearLocalVersion: () => {
    uni.removeStorageSync(appConfig.localVersionKey);
  }
};
