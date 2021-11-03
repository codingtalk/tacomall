/*
 * @Author: 码上talk|RC
 * @Date: 2021-01-20 14:09:14
 * @LastEditTime: 2021-09-08 09:48:42
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/model/entity/tm/tmStaff.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Entity from '../../entity.class.js';
import form from '../../json/form/tm/tmStaff';

const _tableField = {
  deptId: {
    type: 'int',
    default: 0
  },
  username: {
    type: 'string',
    default: ''
  },
  passwd: {
    type: 'string',
    default: ''
  },
  nickname: {
    type: 'string',
    default: ''
  },
  accessExtraJson: {
    type: 'string',
    do: {
      parse: true
    },
    default: ''
  }
};

class TmStaff extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'tm'
  }

  static _form = form

  static _options = {}

  constructor (tmStaff) {
    super(tmStaff, { _tableField });
  }
}

export default TmStaff;
