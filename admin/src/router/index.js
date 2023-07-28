/**
 * 路由管理
 */

import Vue from 'vue'
import Router from 'vue-router'

import HomeView from '../views/HomeView.vue'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  }
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
