import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  type: {
    type: "string",
    default: "",
  },
  infoData: {
    type: "string",
    do: {
      parse: true
    },
    default: null,
  },
};

export class Logistic extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "logistic",
  };

  static _form = {};

  static _options = {};

  constructor(logistic) {
    super(logistic, { _tableField });
  }
}
