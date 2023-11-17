import dayjs from "dayjs";
import _ from "lodash";

export function getYear(d) {
  return dayjs(d).year();
}

export function getMonth(d) {
  return dayjs(d).month() + 1;
}

export function getWeek() {
  const week = new Date().getDay();
  let str = "";
  if (week == 0) {
    str = "日";
  } else if (week == 1) {
    str = "一";
  } else if (week == 2) {
    str = "二";
  } else if (week == 3) {
    str = "三";
  } else if (week == 4) {
    str = "四";
  } else if (week == 5) {
    str = "五";
  } else if (week == 6) {
    str = "六";
  }
  return str;
}

export function getServerExceptionMsg(s) {
  if (!s) {
    return "服务端错误~";
  } else {
    const mk = s.split("BizException: ");
    return mk.length === 2 ? mk[1] : "服务端错误~";
  }
}

export function getUniArr(a) {
  return Array.from(new Set(a));
}

export function sumByKey(a, fn) {
  return a.reduce((p, c) => p + fn(c), 0);
}

export function prefixZero(num, n) {
  return (Array(n).join(0) + num).slice(-n);
}

export function joinStr (arr, ) {
  return arr?.filter(i => i)?.join('')
}