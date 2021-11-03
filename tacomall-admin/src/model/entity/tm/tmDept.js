/*
 * @Author: 码上talk|RC
 * @Date: 2021-01-20 14:09:33
 * @LastEditTime: 2021-10-14 14:38:31
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/model/entity/tm/tmDept.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Entity from '../../entity.class.js';
import form from '../../json/form/tm/tmDept';

const _tableField = {
  name: {
    type: 'string',
    default: ''
  }
};

class TmDept extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'tm'
  }

  static _form = form

  static _options = {}

  constructor (tmDept) {
    super(tmDept, { _tableField });
  }
}

export default TmDept;
