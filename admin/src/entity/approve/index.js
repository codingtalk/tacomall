import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  creator: {
    type: "object",
    default: {},
  },
  approveNode: {
    type: "object",
    default: {},
  },
  approveNodeList: {
    type: "object",
    default: [],
  },
  approveProccessList: {
    type: "object",
    default: [],
  },
};

export * from './type'

export class Approve extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "approve",
  };

  static _form = {};

  static _options = {};

  constructor(approve) {
    super(approve, { _tableField });
  }
}
