/*
 * @Author: 码上talk|RC
 * @Date: 2020-10-24 15:02:14
 * @LastEditTime: 2021-08-10 15:10:35
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/model/entity/tm/tmAccessRule.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Entity from '../../entity.class.js';
import form from '../../json/form/tm/tmAccessRule';

const _tableField = {
  name: {
    type: 'string',
    default: ''
  }
};

class TmAccessRule extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'tm'
  }

  static _form = form

  static _options = {}

  constructor (tmAccessRule) {
    super(tmAccessRule, { _tableField });
  }
}

export default TmAccessRule;
