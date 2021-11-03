/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-03 23:34:29
 * @LastEditTime: 2021-06-03 23:50:05
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/directives/type/enter.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';

Vue.directive('enter', {
  inserted: function (el, binding) {
    const fn = function (event) {
      const e = event || window.event;
      if (e && e.keyCode == 13) {
        binding.value().then(() => {
          document.removeEventListener('keydown', fn);
        });
      }
    };
    document.addEventListener('keydown', fn);
    el.addEventListener('click', binding.value);
  }
});
