export const routesInit = [
  {
    name: "/",
    path: "/",
    redirect: "/index",
  },
  {
    name: "layoutDefault",
    path: "/layout_default",
    component: () => import("@/layout/default.vue"),
    children: [
      {
        name: "login",
        path: "/login",
        meta: {
          title: "登录",
          requiresAuth: false,
        },
        component: () => import("@/pages/login.vue"),
      },
      {
        name: "404",
        path: "/404",
        meta: {
          title: "404",
          requiresAuth: true,
        },
        component: () => import("@/pages/404.vue"),
      },
    ],
  },
  {
    name: "layoutAuth",
    path: "/layout_auth",
    component: () => import("@/layout/auth.vue"),
    children: [
      {
        name: "index",
        path: "/index",
        meta: {
          title: "首页",
          requiresAuth: true,
        },
        component: () => import("@/pages/index.vue"),
      },
      {
        name: "accountProfile",
        path: "/account/profile",
        meta: {
          title: "个人中心",
          requiresAuth: true,
        },
        component: () => import("@/pages/account/profile/index.vue"),
      },
      {
        name: "accountSetting",
        path: "/account/setting",
        meta: {
          title: "账户安全",
          requiresAuth: true,
        },
        component: () => import("@/pages/account/setting/index.vue"),
      },
      {
        name: "403",
        path: "/403",
        meta: {
          title: "403",
          requiresAuth: true,
        },
        component: () => import("@/pages/403.vue"),
      },
      {
        name: "500",
        path: "/500",
        meta: {
          title: "500",
          requiresAuth: true,
        },
        component: () => import("@/pages/500.vue"),
      },
    ],
  },
];
