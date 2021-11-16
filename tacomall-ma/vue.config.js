/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-13 14:06:52
 * @LastEditTime: 2021-11-05 13:57:40
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/vue.config.js
 */
const path = require('path');
const lessVar = path.resolve(__dirname, 'assets/less/var.less');
const lessMixins = path.resolve(__dirname, 'assets/less/mixins.less');
module.exports = {
  css: {
    loaderOptions: {
      less: {
        globalVars: {
          'lessVar': `true; @import "${lessVar}"`,
          'lessMixins': `true; @import "${lessMixins}"`
        }
      }
    }
  },
  configureWebpack: {
    resolve: {
      alias: {
        '~': path.join(__dirname, 'static'),
      }
    }
  }
}