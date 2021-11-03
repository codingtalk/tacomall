/*
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2021-10-29 16:56:11
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/mixins/type/methods.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import * as uniApi from '@/utils/uni'

Vue.mixin({
  methods: {
    red: (url) => {
      uni.redirectTo({
        url: url,
        fail: (e) => {
          console.error(e);
        }
      });
    },
    nav: (url, opts) => {
      uniApi.nav(url, opts)
    },
    back: () => {
      uni.navigateBack({
        delta: 1
      });
    },
    swi: (url) => {
      uni.switchTab({
        url: url
      });
    },
    setNavTitle (title) {
      uni.setNavigationBarTitle({
        title: title
      })
    },
    toast (s, d = 2000, i = 'none') {
      uni.showToast({
        title: s,
        duration: d,
        icon: i
      });
      setTimeout(() => {
        uni.hideToast()
      }, d + 500);
    },
    getServerExceptionMsg (s) {
      if (!s) {
        return '服务端错误~'
      } else {
        const mk = s.split('BizException: ')
        return mk.length === 2 ? mk[1] : '服务端错误~'
      }
    },
    isObjHasBlank (obj, skipAttr = []) {
      for (let i in obj) {
        if (!obj[i] && !skipAttr.includes(i)) {
          return true;
        }
      }
    },
    getLeftTime (e) {
      let nt = this.$dayjs();
      let l = 0;
      nt = nt.add(1, 'hour');
      nt = nt.minute(0);
      nt = nt.second(0);
      nt = nt.millisecond(0);
      l = nt.diff(e);
      return {
        h: parseInt(l / (1000 * 60 * 60) % 24),
        m: parseInt(l / (1000 * 60) % 60),
        s: parseInt(l / 1000 % 60)
      }
    },
    isSameArr (a, b) {
      return this.$_.difference(a, b).length === a.length - b.length;
    }
  }
});
