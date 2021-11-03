/*
 * @Author: 码上talk|RC
 * @Date: 2021-01-21 14:45:34
 * @LastEditTime: 2021-01-21 17:01:25
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /vuejs-pc-web/src/components/popup/render.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

export default {
  functional: true,
  props: ['tpl'],
  render: (h, ctx) => {
    return ctx.props.tpl(h);
  }
};
