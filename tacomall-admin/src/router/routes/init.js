/*
 * @Author: 码上talk|RC
 * @Date: 2021-05-05 08:09:30
 * @LastEditTime: 2021-10-14 16:48:36
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/router/routes/init.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
export const init = [
  {
    name: '/',
    path: '/',
    redirect: '/index'
  },
  {
    name: 'default',
    path: '/default',
    component: () => import('@/layout/default/index'),
    children: [
      {
        name: 'login',
        path: '/login',
        meta: {
          title: '登录',
          requiresAuth: false
        },
        component: () => import('@/pages/login')
      },
      {
        name: '404',
        path: '/404',
        meta: {
          title: '404',
          requiresAuth: false
        },
        component: () => import('@/pages/404')
      }
    ]
  },
  {
    name: 'auth_init',
    path: '/auth_init',
    component: () => import('@/layout/auth/index'),
    children: [
      {
        name: 'index',
        path: '/index',
        meta: {
          title: '首页',
          requiresAuth: true
        },
        component: () => import('@/pages/index')
      },
      {
        name: 'accountProfile',
        path: '/account/profile',
        meta: {
          title: '个人资料',
          requiresAuth: true
        },
        component: () => import('@/pages/account/profile/index')
      },
      {
        name: 'accountSetting',
        path: '/account/setting',
        meta: {
          title: '账户设置',
          requiresAuth: true
        },
        component: () => import('@/pages/account/setting/index')
      },
      {
        name: '403',
        path: '/403',
        meta: {
          title: '403',
          requiresAuth: true
        },
        component: () => import('@/pages/403')
      },
      {
        name: '500',
        path: '/500',
        meta: {
          title: '500',
          requiresAuth: true
        },
        component: () => import('@/pages/500')
      }
    ]
  }
];
