/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 09:21:08
 * @LastEditTime: 2021-10-28 16:21:21
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/model/entity/goods/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Entity from '../../entity.class.js';

import { get, find, difference } from 'lodash'

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
    app: 'ma',
    domain: 'goods'
  }

  static _form = {}

  static _options = {}

  get spec () {
    let sj = [];
    get(this, 'goodsItemsList').forEach(i => {
      const { specJson } = i;
      specJson.forEach(j => {
        const tar = find(sj, x => { return x.k === j.key });
        if (tar) {
          !tar.v.map(i => i.id).includes(j.valueId) && tar.v.push({
            id: j.valueId,
            name: j.value
          });
          return;
        }
        sj.push({
          k: j.key,
          v: [{
            id: j.valueId,
            name: j.value
          }]
        });
      });
    });
    return sj;
  }

  constructor (goods) {
    super(goods, { _tableField });
  }

  getGoodItemsBySpecValueIds (a = []) {
    if (!a.length) {
      return null;
    }
    for (let i in get(this, 'goodsItemsList')) {
      const gi = get(this, 'goodsItemsList')[i];
      if (difference(gi.specJson.map(i => i.valueId), a).length === 0) {
        return gi;
      }
    }
    return null;
  }

}

export default Goods;
