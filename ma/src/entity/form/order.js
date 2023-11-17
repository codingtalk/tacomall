import { Entity} from "codingtalk-uni-toolkit";

const _tableField = {
  name: {
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
    default: null,
  },
  logistic: {
    isEntity: true,
    type: 'Logistic',
    default: null,
  },
};

export class FormOrder extends Entity {
  static _requestConfig = {
    app: "ma",
    domain: "formOrder",
  };

  static _form = {};

  static _options = {};

  constructor(formOrder) {
    super(formOrder, { _tableField });
  }
}
