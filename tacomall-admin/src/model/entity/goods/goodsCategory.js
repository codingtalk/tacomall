/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-01 11:32:19
 * @LastEditTime: 2021-10-26 14:31:56
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/model/entity/goods/goodsCategory.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Entity from '../../entity.class.js';
import form from '../../json/form/goods/goodsCategory';
import options from '../../json/options/goods/goodsCategory';

const _tableField = {

  pId: {
    type: 'int',
    default: 0
  },
  name: {
    type: 'string',
    default: ''
  },
  thumbnail: {
    type: 'string',
    default: ''
  }
};

class GoodsItems extends Entity {
  static _requestConfig = {
    app: 'admin',
    domain: 'goods'
  }

  static _form = form

  static _options = options

  constructor (goodsItems) {
    super(goodsItems, { _tableField });
  }
}

export default GoodsItems;
