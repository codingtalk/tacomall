/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-23 20:32:01
 * @LastEditTime: 2021-10-12 09:34:24
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/utils/socket.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
/*
 * @Author: 码上talk|RC
 * @Date: 2021-07-07 16:43:39
 * @LastEditTime: 2021-07-08 08:28:32
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-shop/src/renderer/libs/socket.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import { appConfig } from '@/config/app';
const io = require('socket.io-client');

let wsUrl = appConfig.wsUrl.dev;

if (process.env.NODE_ENV === 'test') {
  wsUrl = appConfig.wsUrl.test;
}

if (process.env.NODE_ENV === 'production') {
  wsUrl = appConfig.wsUrl.prod;
}

export const SocketIo = class {
  socket = null
  constructor (url = wsUrl) {
    if (window._socketIoSingleInstance) {
      this.socket = window._socketIoSingleInstance;
    }
    this.socket = io(url, {
      reconnectionDelayMax: 10000,
      query: {
        appid: 'ertdzq',
        secret: 'oksdf426kmsdxm'
      }
    });

    window._socketIoSingleInstance = this.socket;
  }

  listen (topic, callback, ok) {
    this.socket.on(topic, msg => {
      callback && callback(msg);
    });
    ok && ok();
  }

  emit (topic, msg) {
    this.socket.emit(topic, msg);
  }

  off (topic, callback) {
    this.socket.off(topic);
    callback();
  }


};
