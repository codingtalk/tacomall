/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-01 11:32:19
 * @LastEditTime: 2021-10-27 11:11:16
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/model/entity/goods/goodsItems.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Entity from '../../entity.class.js';

const _tableField = {

  goodsId: {
    type: 'int',
    default: 0
  },
  specJson: {
    type: 'string',
    default: ''
  },
  stock: {
    type: 'int',
    default: 0
  },
  goods: {
    isEntity: true,
    type: 'Goods',
    default: {}
  },
};

class GoodsItems extends Entity {
  static _requestConfig = {
    app: 'ma',
    domain: 'goods'
  }

  static _form = {}

  static _options = {}

  get specJson2str () {
    return this.specJson.map(i => i.value).join('x');
  }

  constructor (goodsItems) {
    super(goodsItems, { _tableField });
  }
}

export default GoodsItems;
