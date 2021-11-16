/*
 * @Author: 码上talk|RC
 * @Date: 2021-04-26 11:40:02
 * @LastEditTime: 2021-11-08 17:32:02
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/model/entity.class.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { Request } from './request';
import { formToolkit } from './form-toolkit';
import collection from './collection';
import _ from 'lodash'
import { toast } from '@/utils/uni'

const _baseTableField = {
  id: {
    type: 'int',
    default: 0
  },
  isDelete: {
    type: 'int',
    default: 0
  },
  createTime: {
    type: 'string',
    default: ''
  },
  updateTime: {
    type: 'string',
    default: ''
  },
  deleteTime: {
    type: 'string',
    default: ''
  }
};
class Entity {
  _tableField = []
  request = null
  _raw = {}
  constructor (entity, { _tableField }) {
    this._tableField = Object.assign(_.cloneDeep(_baseTableField), _tableField);
    for (const i in this._tableField) {
      if (entity && this._tableField[i].isEntity) {
        const v = _.get(entity, i);
        this[i] = Array.isArray(v) ? v.map(j => new collection[this._tableField[i].type](j)) : new collection[this._tableField[i].type](v);
        continue;
      }
      this[i] = entity ? this._fieldDo(entity, i) : _.get(this._tableField, `${i}.default`);
      this._raw = entity;
    }
  }

  _fieldDo (entity, i) {
    if (!this._tableField[i].do) {
      return _.get(entity, i);
    }
    const { parse } = this._tableField[i].do;
    if (parse) {
      return _.get(entity, i) ? JSON.parse(_.get(entity, i)) : [];
    }
  }

  static filter (s, key, noneStr = '/') {
    for (let i = 0; i < this.options[s].length; i++) {
      if (this.options[s][i].value === key) {
        return this.options[s][i].label || noneStr;
      }
    }
    return noneStr;
  }

  static sendApi (method, form = { params: {}, data: {} }, config = { errorTip: '', parse4Entity: false }) {
    const { app, domain } = this._requestConfig;
    return new Request().do(app, domain, method, form).then(res => {
      const { status } = res;
      if (!status && config.errorTip) {
        toast(config.errorTip);
      }
      if (config.parse4Entity) {
        res.data = Array.isArray(res.data) ? res.data.map(i => new this(i)) : new this(res.data);
      }
      return res;
    });
  }

  static formUtil () {
    return {
      generator: (module, type) => {
        const f = _.get(this._form, `${module}.${type}`);
        for (const i in f) {
          f[i] = Object.assign(f[i], { _validator: { ok: true, msg: '' } });
        }
        return {
          ..._.cloneDeep(f),
          FILED (s) {
            const _self = this;
            let k = '';
            const sr = s.split('.');
            for (let i in sr) {
              const r = sr[i].match(/(\S+)\[(\d+)\]$/);
              if (r && r.length === 3) {
                k = k + `${r[1]}.value[${r[2]}]`
                continue;
              }
              k = k + `${sr[i]}.value`
            }
            return {
              get val () {
                return _.get(_self, k);
              },
              set val (v) {
                _.set(_self, k, v);
              }
            };
          }
        };
      },
      validate: (json) => {
        return formToolkit.validate(json);
      },
      fill: (f, json, config) => {
        for (const i in f) {
          f[i] = Object.assign(f[i], { _validator: { ok: true, msg: '' } });
        }
        return formToolkit.fill(f, json, config);
      }
    };
  }

}

export default Entity;
