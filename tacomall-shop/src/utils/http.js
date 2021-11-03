/*
 * @Author: 码上talk|RC
 * @Date: 2021-04-26 11:40:02
 * @LastEditTime: 2021-05-15 16:47:07
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin_v2/src/utils/http.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import axios from 'axios';
import { appConfig } from '@/config/app';
import { localCache } from './localCache';
import { router } from '@/router';

class HttpLib {
  constructor () {
    this.queue = {};
  }

  getInsideConfig (options) {
    const { data } = options;
    const config = {
      headers: {
        'Content-Type': 'application/json'
      },
      method: 'post'
    };
    return config;
  }

  destroy (url) {
    delete this.queue[url];
  }

  interceptors (instance, url) {
    instance.interceptors.request.use(config => {
      if (localCache.getToken()) {
        config.headers[appConfig.tokenKey] = localCache.getToken();
      }
      this.queue[url] = true;
      return config;
    }, error => {
      return Promise.reject(error);
    });
    instance.interceptors.response.use(res => {
      if (res.headers[appConfig.token]) {
        localCache.setToken(res.headers[appConfig.token]);
      }
      if (res.data.code === 2101) {
        localCache.clearToken();
        router.replace({ path: '/login' });
      }
      this.destroy(url);
      const { data } = res;
      return data;
    }, error => {
      this.destroy(url);
      router.replace({ path: '/500' });
      return Promise.reject(error);
    });
  }

  request (options) {
    const instance = axios.create();
    options = Object.assign(this.getInsideConfig(options), options);
    this.interceptors(instance, options.url);
    return instance(options);
  }
}

export const httpLib = new HttpLib();
