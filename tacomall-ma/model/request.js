/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-03 22:30:33
 * @LastEditTime: 2021-10-21 15:48:20
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/model/request.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import apiJson from '@/config/api';
import { http } from '@/utils/http';
import {
  appConfig
} from '@/config/app'

export class Request {

  _getRequestBaseUrl (url) {
    let finalBaseUrl = url.dev;

    if (appConfig.APP_ENV === 'test') {
      finalBaseUrl = url.test;
    }

    if (appConfig.APP_ENV === 'prod') {
      finalBaseUrl = url.prod;
    }

    return finalBaseUrl;
  }

  _getConfig (app, domain, method) {
    return {
      fullUrl: this._getRequestBaseUrl(apiJson[app].url) + apiJson[app].domain[domain][method].path
    };
  }

  do (app, domain, method, form) {
    const { params, data } = form;
    return http.post(apiJson[app].domain[domain][method].path, params, data, { baseUrl: this._getRequestBaseUrl(apiJson[app].url) });
  }
}
