import { SysApp } from "./sys";
import { Approve, ApproveType } from "./approve";
import { OrgDept, OrgJob, OrgAccessRule, OrgStaff } from "./org";
import { FormOrder } from "./form";
import { Member } from "./member";
import { Logistic } from "./logistic";
import { Product, ProductSKU } from "./product";

export * from "./sys";
export * from "./approve";
export * from "./org";
export * from "./form";
export * from "./member";
export * from "./logistic";
export * from "./product";

export default {
  SysApp,
  Approve,
  ApproveType,
  OrgDept,
  OrgJob,
  OrgStaff,
  OrgAccessRule,
  FormOrder,
  Member,
  Logistic,
  Product,
  Product,
  ProductSKU
};
