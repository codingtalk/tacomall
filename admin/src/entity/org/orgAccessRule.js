import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  name: {
    type: "string",
    default: "",
  },
};

export class OrgAccessRule extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "org",
  };

  static _form = {};

  static _options = {};

  constructor(orgAccessRule) {
    super(orgAccessRule, { _tableField });
  }
}
