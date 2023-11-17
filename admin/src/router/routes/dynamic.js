const dynamic = [
  {
    name: "layoutAuthDynamic",
    path: "/layout_auth--dynamic",
    component: () => import("@/layout/auth.vue"),
    children: [
      {
        name: "approveList",
        path: "/approve/list",
        meta: {
          title: "审核列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/approve/list.vue"),
      },
      {
        name: "approve",
        path: "/approve/:id",
        meta: {
          title: "审核详情",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/approve/_id.vue"),
      },
      {
        name: "approveTypeList",
        path: "/approve-type/list",
        meta: {
          title: "审批类型",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/approve/type/list.vue"),
      },
      {
        name: "productList",
        path: "/product/list",
        meta: {
          title: "产品列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/product/list.vue"),
      },
      {
        name: "productAdd",
        path: "/product/add",
        meta: {
          title: "产品添加",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/product/add.vue"),
      },
      {
        name: "formOrderList",
        path: "/form-order/list",
        meta: {
          title: "订单列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/form/order/list.vue"),
      },
      {
        name: "formOrderAdd",
        path: "/form-order/add",
        meta: {
          title: "订单创建",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/form/order/add.vue"),
      },
      {
        name: "formOrder",
        path: "/form-order/:id",
        meta: {
          title: "订单详情",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/form/order/_id.vue"),
      },
      {
        name: "memberList",
        path: "/member/list",
        meta: {
          title: "会员列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/member/list.vue"),
      },
      {
        name: "orgDeptList",
        path: "/org-dept/list",
        meta: {
          title: "部门列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/org/dept/list.vue"),
      },
      {
        name: "orgJobList",
        path: "/org-job/list",
        meta: {
          title: "职位列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/org/job/list.vue"),
      },
      {
        name: "orgStaffList",
        path: "/org-staff/list",
        meta: {
          title: "职员列表",
          requiresAuth: true,
          accessRuleId: 999,
        },
        component: () => import("@/pages/org/staff/list.vue"),
      },
    ],
  },
];

export default dynamic;
