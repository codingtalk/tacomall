/*
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2021-10-12 11:37:04
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/mixins/common.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import { mapState } from 'vuex';


Vue.mixin({
  computed: {
    ...mapState(['isLogin']),
    ...mapState('user', ['info'])
  }
});
