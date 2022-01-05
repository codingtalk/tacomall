/*
 * @Author: 码上talk|RC
 * @Date: 2021-08-17 09:57:55
 * @LastEditTime: 2022-01-05 13:52:18
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-ma/config/app.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

const APP_ENV = 'dev';


export const appConfig = {
    APP_ENV,
    wx: {
        appid: ''
    },
    tokenKey: 'x-access-token',
    historySearchKey: 'local-history-search',
    staticUrl: '',
    ossUrl: (() => {
        const cfg = {
            dev: '',
            test: '',
            prod: ''
        };
        let url = cfg.dev;
        if (APP_ENV === 'test') {
            url = cfg.test;
        }

        if (APP_ENV === 'prod') {
            url = cfg.prod;
        }
        return url;
    })()
}