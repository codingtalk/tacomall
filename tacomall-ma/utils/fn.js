/*
 * @Author: 码上talk|RC
 * @Date: 2021-06-22 14:30:48
 * @LastEditTime: 2021-08-11 09:39:57
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-ma/utils/fn.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
import _ from 'lodash';
export const FN = {
  calcDistanceTwoLocation (
    { lng1 = 0, lat1 = 0 } = {},
    { lng2 = 0, lat2 = 0 } = {}
  ) {
    return (
      Math.acos(
        Math.sin(lat1) * Math.sin(lat2) +
        Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng1 - lng2)
      ) *
      6371.004 *
      1000
    );
  },
  bgpsToGps: (bd_lng, bd_lat) => {
    if (!bd_lng && !bd_lat) {
      return {
        lng: '',
        lat: ''
      };
    }
    var X_PI = (Math.PI * 3000.0) / 180.0;
    var x = Number(+bd_lng) - 0.0065;
    var y = Number(+bd_lat) - 0.006;
    var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
    var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
    var gg_lng = z * Math.cos(theta);
    var gg_lat = z * Math.sin(theta);

    return {
      lng: +gg_lng.toFixed(6),
      lat: +gg_lat.toFixed(5)
    };
  },
  gpsToBgps: (gg_lng, gg_lat) => {
    if (!gg_lng && !gg_lat) {
      return {
        lng: '',
        lat: ''
      };
    }
    var X_PI = (Math.PI * 3000.0) / 180.0;
    var x = +gg_lng,
      y = +gg_lat;
    var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * X_PI);
    var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * X_PI);
    var bd_lng = z * Math.cos(theta) + 0.0065;
    var bd_lat = z * Math.sin(theta) + 0.006;
    return {
      lat: +bd_lat.toFixed(6),
      lng: +bd_lng.toFixed(5)
    };
  }
}