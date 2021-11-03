/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-02 15:58:47
 * @LastEditTime: 2021-11-02 16:03:21
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/model/entity/orderForm/index.js
 */
import Entity from '../../entity.class.js';

const _tableField = {
};

class OrderForm extends Entity {
  static _requestConfig = {
    app: 'ma',
    domain: 'orderForm'
  }

  static _form = {}

  static options = {}

  constructor (orderForm) {
    super(orderForm, { _tableField });
  }
}

export default OrderForm;
