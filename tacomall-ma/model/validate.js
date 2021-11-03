/*
 * @Author: 码上talk|RC
 * @Date: 2020-10-10 14:43:28
 * @LastEditTime: 2020-10-10 14:44:40
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-shop/src/utils/validate.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
export const validate = {
  isNotEmpty (s) {
    return !!s;
  },
  strLenRequire (s, l) {
    return !!s && s.length >= l;
  },
  isMobile (s) {
    return /^1[3456789]\d{9}$/.test(s);
  }
};
