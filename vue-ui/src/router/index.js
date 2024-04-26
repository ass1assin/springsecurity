import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 静态路由
export const constantRoutes = [
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

//动态路由，基于用户权限动态去加载
export const dynamicRoutes = {

}

const router = new VueRouter({
  mode: 'history',
  routes: constantRoutes
})

export default router