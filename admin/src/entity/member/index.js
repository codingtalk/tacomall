import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  name: {
    type: "string",
    default: '',
  },
  mobile: {
    type: "string",
    default: '',
  },
};

export class Member extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "member",
  };

  static _form = {};

  static _options = {};

  constructor(member) {
    super(member, { _tableField });
  }
}
