/*
 * @Author: 码上talk|RC
 * @Date: 2021-01-20 14:09:33
 * @LastEditTime: 2021-08-10 15:10:56
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/model/entity/tm/tmJob.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Entity from '../../entity.class.js';
import form from '../../json/form/tm/tmJob';

const _tableField = {
  name: {
    type: 'string',
    default: ''
  }
};

class TmJob extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'tm'
  }

  static _form = form

  static _options = {}

  constructor (tmJob) {
    super(tmJob, { _tableField });
  }
}

export default TmJob;
