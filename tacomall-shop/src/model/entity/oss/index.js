/*
 * @Author: 码上talk|RC
 * @Date: 2020-12-29 17:53:17
 * @LastEditTime: 2021-08-14 23:29:18
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/model/entity/oss/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import { appConfig } from '@/config/app';

import Entity from '../../entity.class.js';

let ossUrl = appConfig.ossUrl.dev;

if (process.env.NODE_ENV === 'test') {
  ossUrl = appConfig.ossUrl.test;
}

if (process.env.NODE_ENV === 'production') {
  ossUrl = appConfig.ossUrl.prod;
}

const _tableField = {

  bucket: {
    type: 'string',
    default: ''
  },
  filename: {
    type: 'string',
    default: ''
  },
  url: {
    type: 'string',
    default: ''
  }
};
class Oss extends Entity {
  static _requestConfig = {
    app: 'open',
    domain: 'oss'
  }

  static _form = {}

  static _options = {}

  constructor (oss) {
    super(oss, { _tableField });
  }

  fullUrl () {
    return `${ossUrl}${this.filename}`;
  }
}

export default Oss;
