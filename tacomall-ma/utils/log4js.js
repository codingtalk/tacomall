/*
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 17:01:39
 * @LastEditTime: 2021-10-26 17:01:40
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/utils/log4js.js
 */
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
