/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-13 11:35:36
 * @LastEditTime: 2021-06-13 11:49:33
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/loading/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import loadingComponent from './index.vue';

const LoadingConstructor = Vue.extend(loadingComponent);
const loadingPool = [];

const getAnInstance = () => {
  if (loadingPool.length > 0) {
    const instance = loadingPool[0];
    loadingPool.splice(0, 1);
    return instance;
  }
  return new LoadingConstructor({
    el: document.createElement('div')
  });
};

const returnAnInstance = instance => {
  if (instance) {
    loadingPool.push(instance);
  }
};

const removeDom = event => {
  if (event.target.parentNode) {
    event.target.parentNode.removeChild(event.target);
  }
};

LoadingConstructor.prototype.close = function () {
  this.visible = false;
  this.$el.addEventListener('transitionend', removeDom);
  returnAnInstance(this);
};

const loading = () => {
  const instance = getAnInstance();
  document.body.appendChild(instance.$el);
  Vue.nextTick(function () {
    instance.mode = 'fixed';
    instance.visible = true;
  });
  return instance;
};

export default loading;
