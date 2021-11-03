/*
 * @Author: 码上talk|RC
 * @Date: 2021-08-09 16:54:12
 * @LastEditTime: 2021-10-21 14:13:17
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/model/entity/member/index.js
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
  username: {
    type: 'string',
    default: ''
  },
  realName: {
    type: 'string',
    default: ''
  },
  mobile: {
    type: 'string',
    default: ''
  }
};

class Member extends Entity {
  static _requestConfig = {
    app: 'ma',
    domain: 'member'
  }

  static _form = {}

  static options = options

  constructor (member) {
    super(member, { _tableField });
  }
}

export default Member;
