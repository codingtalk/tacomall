/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-03 15:34:12
 * @LastEditTime: 2021-10-15 17:02:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-uniapp/utils/uni.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { defaultsDeep } from 'lodash'
export function nav (url, opts = {}) {
  uni.navigateTo({
    url: url,
    ...opts
  });
}
export function toast (title, opts = {}) {
  let mergeOpts = defaultsDeep(opts, {
    icon: 'none',
    duration: 2000
  })
  uni.showToast({
    title: title,
    ...mergeOpts,
    fail: (e) => {
      console.log(e);
    }
  });
}