/*
 * @Author: 码上talk|RC
 * @Date: 2021-04-26 11:40:02
 * @LastEditTime: 2021-10-26 14:44:17
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-admin/src/router/routes/dynamic.js
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */

const dynamic = [
  {
    name: 'auth_dynamic',
    path: '/auth_dynamic',
    component: () => import('@/layout/auth/index'),
    children: [
      {
        name: 'goodsList',
        path: '/goods/list',
        meta: {
          title: '商品列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/goods/list')
      },
      {
        name: 'goodsAdd',
        path: '/goods/add',
        meta: {
          title: '商品添加',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/goods/add')
      },
      {
        name: 'goodsUpdate',
        path: '/goods/update/:id',
        meta: {
          title: '商品更新',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/goods/update')
      },
      {
        name: 'goodsInfo',
        path: '/goods/:id',
        meta: {
          title: '商品详情',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/goods/_id')
      },
      {
        name: 'goodsList',
        path: '/goods/list',
        meta: {
          title: '商品列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/goods/list')
      },
      {
        name: 'goodsCategoryList',
        path: '/goods-category/list',
        meta: {
          title: '商品分类列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/goods/category/list')
      },
      {
        name: 'memberList',
        path: '/member/list',
        meta: {
          title: '会员列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/member/list')
      },
      {
        name: 'tmDeptList',
        path: '/tm-dept/list',
        meta: {
          title: '部门列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/tm/dept/list')
      },
      {
        name: 'tmJobList',
        path: '/tm-job/list',
        meta: {
          title: '职位列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/tm/job/list')
      },
      {
        name: 'tmStaffList',
        path: '/tm-staff/list',
        meta: {
          title: '职员列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/tm/staff/list')
      },
      {
        name: 'tmAccessRuleList',
        path: '/tm-access-rule/list',
        meta: {
          title: '权限列表',
          requiresAuth: true,
          accessRuleId: 9999
        },
        component: () => import('@/pages/tm/access-rule/list')
      }
    ]
  }
];

export default dynamic;
