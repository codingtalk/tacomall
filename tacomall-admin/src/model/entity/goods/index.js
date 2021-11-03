/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 09:21:08
 * @LastEditTime: 2021-10-25 17:01:17
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/model/entity/goods/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Entity from '../../entity.class.js';
import form from '../../json/form/goods';
import options from '../../json/options/goods';

const _tableField = {
  name: {
    type: 'string',
    default: ''
  },
  thumbnail: {
    type: 'string',
    default: ''
  },
  amount: {
    type: 'int',
    default: 0
  },
  goodsItemsList: {
    isEntity: true,
    type: 'GoodsItems',
    default: []
  }
};

class Goods extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'goods'
  }

  static _form = form

  static _options = options

  constructor (goods) {
    super(goods, { _tableField });
  }
}

export default Goods;
