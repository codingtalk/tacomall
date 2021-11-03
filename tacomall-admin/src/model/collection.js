/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:29
 * @LastEditTime: 2021-10-26 14:49:39
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/model/collection.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

import Oss from './entity/oss';
import TmDept from './entity/tm/tmDept';
import TmJob from './entity/tm/tmJob';
import TmStaff from './entity/tm/tmStaff';
import TmAccessRule from './entity/tm/tmAccessRule';
import Goods from './entity/goods';
import GoodsItems from './entity/goods/goodsItems';
import GoodsCategory from './entity/goods/goodsCategory';
import Member from './entity/member';

export default {
  Oss,
  TmDept,
  TmJob,
  TmStaff,
  TmAccessRule,
  Goods,
  GoodsItems,
  GoodsCategory,
  Member
};
