/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-03 15:34:12
 * @LastEditTime: 2021-09-07 19:30:19
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/store/user/state.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { model } from '@/model';

const { Member } = model.collection;

export default {
  info: new Member()
};
