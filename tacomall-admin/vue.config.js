/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-10-14 13:45:07
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/vue.config.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

const path = require('path');

function resolve (url) {
  return path.resolve(__dirname, url);
}

function addStyleResource (rule) {
  rule
    .use('style-resource')
    .loader('style-resources-loader')
    .options({
      patterns: [
        resolve('./src/assets/less/mixins.less'),
        resolve('./src/assets/less/var.less')
      ]
    });
}

module.exports = {
  devServer: {
    proxy: {
      '/apiOpen/': {
        target: 'http://localhost:4003/',
        ws: false,
        changeOrigin: true,
        pathRewrite: {
          '^/apiOpen': '/'
        }
      },
      '/apiAdmin/': {
        target: 'http://localhost:4000/',
        ws: false,
        changeOrigin: true,
        pathRewrite: {
          '^/apiAdmin': '/'
        }
      }
    }
  },
  configureWebpack: {
    entry: ['@babel/polyfill', './src/main.js'],
    devtool: 'source-map'
  },
  css: {
    extract: false,
    sourceMap: false,
    loaderOptions: {
      postcss: {
        plugins: [
          require('postcss-px-to-viewport')({
            unitToConvert: 'px',
            viewportWidth: 1920,
            viewportHeight: 1080,
            unitPrecision: 3,
            propList: [
              '*'
            ],
            viewportUnit: 'vw',
            fontViewportUnit: 'vw',
            selectorBlackList: [],
            minPixelValue: 1,
            mediaQuery: false,
            replace: true
          })
        ]
      }
    }
  },
  chainWebpack: config => {
    const types = ['vue-modules', 'vue', 'normal-modules', 'normal'];
    types.forEach((type) =>
      addStyleResource(config.module.rule('less').oneOf(type))
    );
  }
};