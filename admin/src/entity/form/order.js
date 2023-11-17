import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  sn: {
    type: "string",
    default: "",
  },
  type: {
    type: "string",
    default: "",
  },
  status: {
    type: "string",
    default: "",
  },
  amountActual: {
    type: "string",
    default: "",
  },
  infoData: {
    type: "string",
    do: {
      parse: true
    },
    default: {}
  },
  logistic: {
    isEntity: true,
    type: 'Logistic',
    default: null,
  },
};

export class FormOrder extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "order",
  };

  static _form = {};

  static _options = {};

  constructor(formOrder) {
    super(formOrder, { _tableField });
  }
}
