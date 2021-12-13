/*
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2021-12-03 14:55:06
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/mixins/type/pageInfo.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { model } from '@/model';

const { Request } = model;

export default {
    data () {
        return {
            PAGE_INFO: {}
        }
    },
    methods: {
        _boot () {
            if (!this.PAGE_CONFIG) {
                this.$log4js.error('attr "PAGE_CONFIG" is require');
            }
            return new Request().do('ma', 'page', 'info', { params: { key: this.PAGE_CONFIG.key }, data: this.PAGE_CONFIG.reqData || {} }).then(res => {
                const { status, data } = res;
                if (status) {
                    this.PAGE_INFO = data;
                    this.PAGE_CONFIG.callback && this.PAGE_CONFIG.callback();
                } else {
                    this.toast('获取页面信息失败');
                }
            });
        },
        infoGet (s, noneValue = '--') {
            return this.$_.get(this.PAGE_INFO, s, noneValue);
        }
    },
    onLoad () {
        this._boot();
    }
};
