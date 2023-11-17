import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  name: {
    type: "string",
    default: "",
  },
  accessRuleIds: {
    type: "string",
    default: "",
  },
};

export class OrgDept extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "org",
  };

  static _form = {};

  static _options = {};

  constructor(orgDept) {
    super(orgDept, { _tableField });
  }
}
