/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 15:26:53
 * @LastEditTime: 2021-10-26 13:42:41
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/mixins/type/info.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { model } from '@/model';

const typeInfo = {
  data () {
    return {
      params: this.$route.params
    };
  },
  watch: {
    $route: function (e) {
      this.params = this.$route.params;
      this._boot();
    }
  },
  methods: {
    _boot () {
      if (!this.info) {
        this.$log4js.error('attr "info" is require');
      }
      const { entity, action, req, params = {}, noConvert2Entity = false } = this.info.config;
      if (req) {
        return req().then(res => {
          this.info.result = res;
          this.info.callback && this.info.callback();
        });
      }
      return model.collection[entity].sendApi(action, { params: Object.assign({ id: Number(this.params.id) }, params), data: {} }).then(res => {
        const { status, data } = res;
        if (status) {
          this.info.result = noConvert2Entity ? data : new model.collection[entity](data);
          this.info.callback && this.info.callback();
        } else {
          this.$message({
            message: '获取信息失败',
            type: 'error'
          });
        }
      });
    },
    infoGet (s, noneValue = '--') {
      return this.$_.get(this.info.result, s, noneValue);
    }
  },
  created () {
    this._boot();
    this.init && this.init();
  }
};

export default typeInfo;
