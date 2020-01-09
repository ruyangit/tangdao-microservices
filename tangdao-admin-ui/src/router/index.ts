import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/user',
    component: () => import(/* webpackChunkName: "user-layout" */ '../layouts/UserLayout.vue'),
    children: []
  },
  {
    path: '/',
    component: () => import(/* webpackChunkName: "basic-layout" */ '../layouts/BasicLayout.vue'),
    children: [
      { path: "/", redirect: '/dashboard/analysis-one'},
      {
        path: '/dashboard',
        name: 'dashboard',
        component: () => import(/* webpackChunkName: "basic-layout" */ '../layouts/BlankLayout.vue'),
        children: [
          { 
            path: '/dashboard/analysis-one', 
            name: 'analysis-one', 
            component: () => import(/* webpackChunkName: "analysis-one" */ '../views/Components.vue'),
          },
        ]
      },

    ]
  },
];

const router = new VueRouter({
  routes,
});

export default router;
