/*
 * @Author: 码上talk|RC
 * @Date: 2021-08-04 17:34:56
 * @LastEditTime: 2021-11-05 15:13:50
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/main.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue'
import App from './App'

import uView from '@/uni_modules/uview-ui';
import store from './store'
import { get, find, cloneDeep, difference, throttle } from 'lodash'
import './mixins'
import dayjs from 'dayjs';
import { Log4js } from '@/utils/log4js';

Vue.config.productionTip = false

Vue.prototype.$_ = {
  get,
  find,
  cloneDeep,
  difference,
  throttle
};
Vue.prototype.$dayjs = dayjs;
Vue.prototype.$log4js = new Log4js();
Vue.use(uView);

App.mpType = 'app';

const app = new Vue({
  store,
  ...App
})
app.$mount()
