/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-09 17:12:47
 * @LastEditTime: 2021-08-28 08:26:20
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/utils/downloader.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

const downloader = {
  do: (url) => {
    require('downloadjs')(url);
  }
};

export default downloader;
