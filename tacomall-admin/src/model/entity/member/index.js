/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 09:21:08
 * @LastEditTime: 2021-08-21 11:29:26
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/model/entity/member/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Entity from '../../entity.class.js';
import options from '../../json/options/member';

const _tableField = {

  type: {
    type: 'int',
    default: 0
  },
  levelId: {
    type: 'int',
    default: 0
  },
  realName: {
    type: 'string',
    default: ''
  },
  mobile: {
    type: 'string',
    default: ''
  },
  birthday: {
    type: 'string',
    default: ''
  }
};

class Member extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'member'
  }

  static _form = {}

  static _options = options

  constructor (member) {
    super(member, { _tableField });
  }
}

export default Member;
