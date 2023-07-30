/**
 * 路由管理
 */

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/layout/index.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/error/404.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue'),
    meta: { ignoreAuth: true } // 添加元信息 ignoreAuth 来标识登录请求
  },
  { path: '*', redirect: '/404' }
]

const createRouter = () => new Router({
  // 定义路由切换时页面滚动的行为：切换页面时总是滚动到页面顶部。
  scrollBehavior: () => ({ y: 0 }),
  routes
})

export function resetRouter () {
  const newRouter = createRouter()
  // 重置路由：路由匹配器（matcher）是用来匹配路由的核心组件，负责解析和匹配路由配置
  router.matcher = newRouter.matcher
}

const router = createRouter()

export default router
