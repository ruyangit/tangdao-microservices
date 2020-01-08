import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
  {
    path: '/components',
    name: 'components',
    component: () => import(/* webpackChunkName: "components" */ '../views/Components.vue'),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
