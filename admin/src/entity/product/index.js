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

export * from './SKU'

export class Product extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "product",
  };

  static _form = {};

  static _options = {};

  constructor(product) {
    super(product, { _tableField });
  }
}
