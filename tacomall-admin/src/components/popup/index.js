/*
 * @Author: 码上talk|RC
 * @Date: 2021-01-21 14:28:35
 * @LastEditTime: 2021-05-10 17:06:32
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin_v2/src/components/popup/index.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import popupComponent from './index.vue';

const PopupConstructor = Vue.extend(popupComponent);
const popupPool = [];

const getAnInstance = () => {
  if (popupPool.length > 0) {
    const instance = popupPool[0];
    popupPool.splice(0, 1);
    return instance;
  }
  return new PopupConstructor({
    el: document.createElement('div')
  });
};

const returnAnInstance = instance => {
  if (instance) {
    popupPool.push(instance);
  }
};

const removeDom = event => {
  if (event.target.parentNode) {
    event.target.parentNode.removeChild(event.target);
  }
};

PopupConstructor.prototype.close = function () {
  this.visible = false;
  this.$el.addEventListener('transitionend', removeDom);
  returnAnInstance(this);
};

const popup = (tpl, config = {}) => {
  const instance = getAnInstance();
  document.body.appendChild(instance.$el);
  Vue.nextTick(function () {
    instance.visible = true;
    instance.tpl = tpl;
    instance.model = config.model;
  });
  return instance;
};

export default popup;
