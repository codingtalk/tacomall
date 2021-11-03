/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-06-13 10:48:22
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/utils/log4js.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
/* eslint-disable  */
const defaultConfig = {
  isUp2sever: false,
  url: ''
}
export class Log4js {
  config = {}

  constructor (config) {
    this.config = Object.assign(defaultConfig, config)
  }

  debug (e) {
    const prefix = `%c
//     __  __     ____         _       __           __    __
//    / / / /__  / / /___     | |     / /___  _____/ /___/ /
//   / /_/ / _ \/ / / __ \    | | /| / / __ \/ ___/ / __  / 
//  / __  /  __/ / / /_/ /    | |/ |/ / /_/ / /  / / /_/ /  
// /_/ /_/\___/_/_/\____/     |__/|__/\____/_/  /_/\__,_/   
//                                             
      `
    const suffix = `%c
//
// =======================================================
//
      `
    console.log(prefix, 'color: blue')
    console.log(e)
    console.log(suffix, 'color: red')
  }

  log (e) {
    console.log(`%c ------------- power by log4js, log start -----------`, 'color: blue')
    console.log(e)
    console.log(`%c ------------- power by log4js, log end -----------`, 'color: red')
  }

  error (e) {
    console.log(`%c ------------- power by log4js, log start -----------`, 'color: blue')
    throw e
    console.log(`%c ------------- power by log4js, log end -----------`, 'color: red')
  }
}
