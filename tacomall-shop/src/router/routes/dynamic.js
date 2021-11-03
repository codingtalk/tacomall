/*
 * @Author: 码上talk|RC
 * @Date: 2021-04-26 11:40:02
 * @LastEditTime: 2021-10-14 14:34:01
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-web/src/router/routes/dynamic.js
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
