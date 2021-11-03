/*
 * @Author: 码上talk|RC
 * @Date: 2021-08-09 15:38:41
 * @LastEditTime: 2021-09-06 14:13:11
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/mixins/type/access.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import Vue from 'vue';
import { mapState } from 'vuex';
import accessNode from '@/config/accessNode';
Vue.mixin({
  computed: {
    ...mapState(['isAdmin']),
    ...mapState('sys', ['accessRuleIds']),
    ...mapState('staff', {
      tmStaffInfo: 'info'
    })
  },
  methods: {
    getAccessValidList (l, key = 'accessRuleId') {
      return l.filter(i => {
        if (this.isAdmin) {
          return true;
        }
        return this.accessRuleIds.includes(i[key]);
      });
    },
    checkAccessNode (s) {
      if (this.isAdmin) {
        return true;
      }
      const aN = this.$_.get(accessNode, s, []);
      return this.$_.differenceWith(this.accessRuleIds, Array.isArray(aN) ? aN : [aN]).length !== this.accessRuleIds.length;
    }
  }
});
