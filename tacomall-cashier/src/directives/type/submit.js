/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-24 14:48:58
 * @LastEditTime: 2021-06-08 10:41:55
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/directives/type/submit.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';

Vue.directive('submit', {
  inserted: function (el, binding) {
    let timer = null;
    el.addEventListener('click', () => {
      if (timer) {
        Vue.prototype.$Message.warning('请稍后重试');
        return;
      }
      binding.value.action();
      timer = setTimeout(() => {
        clearTimeout(timer);
        timer = null;
      }, binding.value.config && binding.value.config.duration || 2000);
    });
  }
});
