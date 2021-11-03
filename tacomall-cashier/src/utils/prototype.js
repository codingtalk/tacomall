/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-13 15:49:30
 * @LastEditTime: 2021-07-17 10:57:59
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/utils/prototype.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
String.prototype.toParse = function () {
  try {
    return JSON.parse(this);
  } catch (e) {
    return {};
  }
};

String.prototype.getExt = function () {
  try {
    const index = this.lastIndexOf('.');
    const ext = this.substr(index + 1);
    return ext;
  } catch (e) {
    return '';
  }
};

String.prototype.getServerMessage = function () {
  return this.split('BizException: ')[1];
};

String.prototype.isNumber = function () {
  const regPos = /^\d+(\.\d+)?$/;
  const regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
  const regDiv = /^\d+\/\d+$/;
  if (regPos.test(this) || regNeg.test(this) || regDiv.test(this)) {
    return true;
  } else {
    return false;
  }
};

String.prototype.hideStr = function (frontLen, endLen) {
  const len = this.length - frontLen - endLen;
  let xing = '';
  for (let i = 0; i < len; i++) {
    xing += '*';
  }
  return this.substring(0, frontLen) + '******' + this.substring(this.length - endLen);
};
