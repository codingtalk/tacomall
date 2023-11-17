import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  infoData: {
    type: "object",
    default: "",
  },
};

export class SysApp extends Entity {
  static _requestConfig = {
    app: "open",
    domain: "sys",
  };

  static _form = {};

  static _options = {};

  constructor(sysApp) {
    super(sysApp, { _tableField });
  }
}
