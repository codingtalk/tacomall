
import {
  appConfig
} from '@/config/app'
import {
  localCache
} from './localCache'

const buildStrParams = (params) => {
  let strParams = ''
  let i = 0
  for (let k in params) {
    let q = ''
    if (i === 0) {
      q = '?'
    } else {
      q = '&'
    }
    strParams = strParams + q + k + '=' + params[k]
    i++
  }
  return strParams
}

const doLogin = () => {
  uni.switchTab({
    url: '/pages/index/index',
    success: () => {
      uni.navigateTo({
        url: '/pages/login/index',
        fail: (e) => {
          console.error('doLogin navigateTo fail', e);
        }
      })
    }
  })
}

const send = (url, params, data, config = null) => {
  const defaultConfig = {
    method: 'POST',
    showLoading: true,
    baseUrl: '',
    requireLogin: false,
    forceLogin: true
  }
  const finalConfig = config ? Object.assign(defaultConfig, config) : defaultConfig
  finalConfig.showLoading && uni.showLoading({
    title: '加载中'
  })
  return new Promise((resolve, reject) => {
    if (finalConfig.requireLogin && !localCache.getToekn()) {
      if (finalConfig.forceLogin) {
        doLogin()
      }
      resolve({
        status: false
      })
      return
    }
    uni.request({
      method: finalConfig.method,
      url: finalConfig.baseUrl + url + buildStrParams(params),
      data: data,
      header: (() => {
        const tokeValue = localCache.getToken()
        let config = {
          'Content-Type': 'application/json'
        }
        if (tokeValue) {
          config[appConfig.tokenKey] = tokeValue
        }
        return config
      })(),
      success: (res) => {
        const { code } = res.data
        finalConfig.showLoading && uni.hideLoading()
        if (code === 4200) {
          localCache.clearToken()
          uni.navigateTo({
            url: '/pages/login/index'
          })
          return;
        }
        resolve(res.data);
      },
      complete: () => { },
      fail: () => {
        finalConfig.showLoading && uni.hideLoading()
        reject()
      }
    })
  })
}

export const http = {
  post: (url, params, data, config) => {
    return send(url, params, data, config)
  }
}
