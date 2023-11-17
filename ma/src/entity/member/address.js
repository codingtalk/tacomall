import { Entity} from "codingtalk-uni-toolkit";

const _tableField = {
  province: {
    type: "string",
    default: "",
  },
  city: {
    type: "string",
    default: "",
  },
  district: {
    type: "string",
    default: "",
  },
  detail: {
    type: "string",
    default: "",
  },
  receiver: {
    type: "string",
    default: "",
  },
  mobile: {
    type: "string",
    default: "",
  },
};

export class MemberAddress extends Entity {
  static _requestConfig = {
    app: "ma",
    domain: "member",
  };

  static _form = {};

  static _options = {};

  constructor(memberAddress) {
    super(memberAddress, { _tableField });
  }
}
