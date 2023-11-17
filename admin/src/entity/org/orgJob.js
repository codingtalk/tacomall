import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  name: {
    type: "string",
    default: "",
  },
};

export class OrgJob extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "org",
  };

  static _form = {};

  static _options = {};

  constructor(orgJob) {
    super(orgJob, { _tableField });
  }
}
