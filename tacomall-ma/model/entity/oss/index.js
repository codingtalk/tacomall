/*
 * @Author: 码上talk|RC
 * @Date: 2021-09-03 08:16:18
 * @LastEditTime: 2021-10-11 17:29:51
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/model/entity/oss/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import { appConfig } from '@/config/app';

import Entity from '../../entity.class.js';

let ossUrl = appConfig.ossUrl;

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
