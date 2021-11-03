/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:29
 * @LastEditTime: 2021-10-14 16:04:42
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/mixins/type/utils.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import dayjs from 'dayjs';
Vue.mixin({
  methods: {
    getDayjsObj (v) {
      return dayjs(v);
    },
    blankGet (s, k) {
      if (s instanceof Object) {
        return $_.get(s, k) || '--';
      }
      return s || '--';
    },
    isObjHasBlank (obj, skipAttr = []) {
      for (let i in obj) {
        if (!obj[i] && !skipAttr.includes(i)) {
          return true;
        }
      }
    },
    getYear (d) {
      return this.$dayjs(d).year();
    },
    getMonth (d) {
      return this.$dayjs(d).month() + 1;
    },
    getServerExceptionMsg (s) {
      if (!s) {
        return '服务端错误~'
      } else {
        const mk = s.split('BizException: ')
        return mk.length === 2 ? mk[1] : '服务端错误~'
      }
    },
    getUniArr (a) {
      return Array.from(new Set(a));
    },
    reloadPage () {
      window.location.reload();
    }
  }
});