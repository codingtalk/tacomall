/*
 * @Author: 码上talk|RC
 * @Date: 2021-04-23 11:22:47
 * @LastEditTime: 2021-08-11 09:40:07
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/model/form-toolkit.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import _ from 'lodash'
import collection from './collection';

export const formToolkit = {
  validate: (obj, config = { rqeuire: [] }) => {
    const result = {
      ok: true,
      form: null
    };
    const _loop = (obj) => {
      if (Array.isArray(obj)) {
        return obj.map(i => _loop(i));
      }
      const f = {};
      for (const i in obj) {
        if (obj[i].skip) {
          continue;
        }
        if (obj[i].require && !obj[i].value) {
          if (config.rqeuire && config.rqeuire.length && !config.rqeuire.includes(i)) {
            continue;
          }
          obj[i]._validator = {
            ok: false,
            msg: ''
          };
          result.ok = false;
        }
        if (Array.isArray(obj[i].value)) {
          if (!obj[i].value.length) {
            f[i] = [];
            continue;
          }
          obj[i].value.forEach(j => {
            let isSkipRow = false;
            if (!f[i]) {
              f[i] = [];
            }
            for (const k in j) {
              if (j[k].skipRowValue !== undefined && j[k].skipRowValue === j[k].value) {
                isSkipRow = true;
              }
            }
            if (isSkipRow) {
              return;
            }
            f[i].push(_loop(j));
          });
          continue;
        }
        if (typeof obj[i].value === 'object' && !_.isEmpty(obj[i].value)) {
          f[i] = _loop(obj[i].value);
          continue;
        }
        f[i] = obj[i].value;
      }
      return f;
    };
    result.form = _loop(obj);
    return result;
  },
  fill: (form, obj) => {
    for (const i in form) {
      if (form[i].skip) {
        form[i].value = obj[i] || (() => {
          const type = form[i].type.split('|')[0];
          if (type === 'array') {
            return [];
          }
          if (type === 'string') {
            return '';
          }
          if (type === 'boolean') {
            return false;
          }
          return {};
        })();
        continue;
      }
      if (form[i].skipFill) {
        continue;
      }
      const [typeValid, entity] = form[i].type.split('>');
      const [type] = typeValid.split('|');
      if (type === 'array') {
        obj[i] && obj[i].forEach(j => {
          try {
            form[i].value.push(formToolkit.fill(collection[entity].formUtil().generator('update', 'default'), j));
          } catch (e) {
            throw `${entity} is error`
          }
        });
        continue;
      }
      if (type === 'object') {
        if (!obj[i]) {
          form[i].value = null;
          continue;
        }
        form[i].value = formToolkit.fill(collection[entity].formUtil().generator('update', 'default'), obj[i]);
        continue;
      }
      form[i].value = obj[i];
    }
    return form;
  }
};
