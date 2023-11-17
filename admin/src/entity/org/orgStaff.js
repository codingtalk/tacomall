import { Entity } from "codingtalk-vue-toolkit";

const _tableField = {
  isAdmin: {
    type: "int",
    default: 0,
  },
  deptId: {
    type: "int",
    default: 0,
  },
  username: {
    type: "string",
    default: "",
  },
  passwd: {
    type: "string",
    default: "",
  },
  nickname: {
    type: "string",
    default: "",
  },
};

export class OrgStaff extends Entity {
  static _requestConfig = {
    app: "web",
    domain: "org",
  };

  static _form = {};

  static _options = {};

  constructor(orgStaff) {
    super(orgStaff, { _tableField });
  }
}

