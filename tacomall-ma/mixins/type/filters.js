/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-14 15:46:43
 * @LastEditTime: 2021-10-28 15:36:47
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/mixins/type/filters.js
 */
import Vue from 'vue';

Vue.mixin({
  filters: {
    addZero: (t) => {
      return t < 10 ? '0' + t : t + '';
    },
    timeFormat (d, f = 'YYYY-MM-DD HH:mm') {
      if (!d) {
        return '2019-01-01 00:00';
      }
      return this.$dayjs(d).format(f);
    },
    amountFormat (number, decimals = 2, dec_point, thousands_sep = ',') {
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
    }
  }
});