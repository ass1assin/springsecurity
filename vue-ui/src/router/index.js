import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login', // 默认重定向到 /login
  },
  // 登录
  {
    path: '/login',
    component: () => import('../view/common/login'),
    meta: { noLayout: true },
  },

  {
    path: '/sidebar',
    component: () => import('../components/sidebar/sidebar'),
    meta: { noLayout: true },
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router