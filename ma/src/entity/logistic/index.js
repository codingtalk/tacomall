import { Entity} from "codingtalk-uni-toolkit";

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
    app: "ma",
    domain: "logistic",
  };

  static _form = {};

  static _options = {};

  constructor(logistic) {
    super(logistic, { _tableField });
  }
}
