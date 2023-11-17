import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  name: {
    type: "string",
    default: '',
  },
  infoData: {
    type: "string",
    do: {
      parse: true
    },
    default: {}
  },
};

export class ProductSKU extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "product",
  };

  static _form = {};

  static _options = {};

  constructor(productSKU) {
    super(productSKU, { _tableField });
  }
}
