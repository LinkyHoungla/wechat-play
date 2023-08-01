/**
 * 路由管理
 */

import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: { title: '首页', icon: 'home' }
      }
    ]
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/error/404.vue'),
    meta: {
      title: '404'
    }
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue'),
    meta: {
      title: '登录'
    }
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
