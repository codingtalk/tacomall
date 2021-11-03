/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-10-14 16:39:00
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/router/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import VueRouter from 'vue-router';
import { init } from './routes/init';
import { middlewareAfterEach } from './middleware/afterEach';
import { middlewareBeforeEach } from './middleware/beforeEach';

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: init
});

middlewareAfterEach(router);
middlewareBeforeEach(router);
