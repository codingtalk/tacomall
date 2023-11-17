import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  name: {
    type: "string",
    default: "",
  }
};

export class ApproveType extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "approve",
  };

  static _form = {};

  static _options = {};

  constructor(approveType) {
    super(approveType, { _tableField });
  }
}
