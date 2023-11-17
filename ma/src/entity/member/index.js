import { Entity} from "codingtalk-uni-toolkit";

const _tableField = {
  nickname: {
    type: "string",
    default: "",
  },
  username: {
    type: "string",
    default: "",
  },
  type: {
    type: "string",
    default: "",
  },
  avatar: {
    type: "string",
    default: "",
  },
};

export * from './address'

export class Member extends Entity {
  static _requestConfig = {
    app: "ma",
    domain: "member",
  };

  static _form = {};

  static _options = {};

  constructor(member) {
    super(member, { _tableField });
  }
}
