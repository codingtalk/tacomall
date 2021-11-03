/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-03 22:30:33
 * @LastEditTime: 2021-09-05 09:28:02
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/model/request.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import apiJson from '@/config/api';
import { store } from '@/store';
import { httpLib } from '@/utils/http';

export class Request {
  _getRequestBaseUrl (url) {
    let finalBaseUrl = url.dev;

    if (process.env.NODE_ENV === 'local') {
      finalBaseUrl = url.lcoal;
    }

    if (process.env.NODE_ENV === 'test') {
      finalBaseUrl = url.test;
    }

    if (process.env.NODE_ENV === 'production') {
      finalBaseUrl = url.prod;
    }

    return finalBaseUrl;
  }

  _getConfig (app, domain, method) {
    return {
      fullUrl: this._getRequestBaseUrl(apiJson[app].url) + apiJson[app].domain[domain][method].url
    };
  }

  do (app, domain, method, form, config = {}) {
    const { params, data } = form;
    let url = ''
    try {
      url = apiJson[app].domain[domain][method].url;
    } catch (e) {
      $log4js.error(`
      Request.js =====> api json configure is error
      full path ======> ${app}/${domain}/${method}
      `);
    }
    if (config.showLoading) {
      store.commit('sys/SET_LAYOUT_AUTH_LOADING', true, { root: true });
    }
    return httpLib.request({
      baseURL: this._getRequestBaseUrl(apiJson[app].url),
      url,
      params,
      data
    }).finally(() => {
      store.commit('sys/SET_LAYOUT_AUTH_LOADING', false, { root: false });
    });
  }
}
