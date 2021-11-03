import defaultLayout from './default/index.vue';
import authLayout from './auth/index.vue';

export const layout = (Vue) => {
  Vue.component('DefaultLayout', defaultLayout);
  Vue.component('AuthLayout', authLayout);
};
