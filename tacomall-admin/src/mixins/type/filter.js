/*
 * @Author: 码上talk|RC
 * @Date: 2021-04-26 21:12:46
 * @LastEditTime: 2021-10-14 16:04:57
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/mixins/type/filter.js
 * Just do what I think it is right
 */
import Vue from 'vue';

import dayjs from 'dayjs';

Vue.mixin({
  filters: {
    timeFormat (d, f = 'YYYY-MM-DD HH:mm') {
      if (!d) {
        return '2019-01-01 00:00';
      }
      return dayjs(d).format(f);
    },
    amountFormat (number, decimals = 2, dec_point, thousands_sep = ',') {
      /*
      * 参数说明：
      * number：要格式化的数字
      * decimals：保留几位小数
      * dec_point：小数点符号
      * thousands_sep：千分位符号
      * */
      number = (number + '').replace(/[^0-9+-Ee.]/g, '');
      const n = !isFinite(+number) ? 0 : +number;
      const prec = !isFinite(+decimals) ? 0 : Math.abs(decimals);
      const sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep;
      const dec = (typeof dec_point === 'undefined') ? '.' : dec_point;
      let s = '';
      const toFixedFix = function (n, prec) {
        const k = Math.pow(10, prec);
        return '' + Math.ceil(n * k) / k;
      };

      s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
      const re = /(-?\d+)(\d{3})/;
      while (re.test(s[0])) {
        s[0] = s[0].replace(re, '$1' + sep + '$2');
      }

      if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
      }
      return s.join(dec);
    },
    addUp (a, key) {
      let n = 0;
      a.forEach(i => {
        n = n + Number(i[key]);
      });
      return n;
    },
    boolean2str (b) {
      return b ? '是' : '否';
    },
    customizeOptionFilter (key, options, noneStr = '/') {
      for (let i = 0; i < options.length; i++) {
        if (options[i].value === key) {
          return options[i].label || noneStr;
        }
      }
      return noneStr;
    },
    divisorFilter (arr) {
      if (!Array.isArray(arr) || arr.length !== 2 || !arr[0] || !arr[1]) {
        return '/';
      }
      return arr[1] === 1 ? arr[0] : `${arr[0]}/${arr[1]}`;
    }
  }
});
