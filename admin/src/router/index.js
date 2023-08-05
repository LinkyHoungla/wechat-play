/**
 * 路由管理
 */

import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'

Vue.use(Router)

const routes = [
  // 首页
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: { title: '首页', icon: 'home', pid: 0 }
      }
      // {
      //   path: 'admins',
      //   name: 'Admin',
      //   component: () => import('@/views/user/Admin.vue'),
      //   meta: { title: '管理员列表', icon: 'table' }
      // }
    ]
  },

  // error
  {
    path: '/error',
    component: Layout,
    redirect: '/error/404',
    children: [
      {
        path: '404',
        name: '404',
        component: () => import('@/views/error/404.vue'),
        meta: { title: '404', pid: 0 }
      },
      {
        path: '403',
        name: '403',
        component: () => import('@/views/error/403.vue'),
        meta: { title: '403', pid: 0 }
      }
    ]
  },

  // 登录
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue'),
    meta: { title: '登录', pid: 0 }
  },

  // 用户管理
  {
    path: '/user',
    component: Layout,
    redirect: '/user/admins',
    name: 'UserView',
    meta: { title: '用户管理' },
    children: [
      {
        path: 'admins',
        name: 'Admin',
        component: () => import('@/views/user/Admin.vue'),
        meta: { title: '管理员列表', icon: 'table', pid: 10 }
      },
      {
        path: 'users',
        name: 'User',
        component: () => import('@/views/user/User.vue'),
        meta: { title: '用户列表', icon: 'table', pid: 11 }
      }
    ]
  },

  // 权限管理
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/roles',
    name: 'Permission',
    meta: { title: '权限管理' },
    children: [
      {
        path: 'roles',
        name: 'Role',
        component: () => import('@/views/permission/Role.vue'),
        meta: { title: '权限角色', icon: 'table', pid: 12 }
      },
      {
        path: 'list',
        name: 'PermissionList',
        component: () => import('@/views/permission/Right.vue'),
        meta: { title: '权限角色', icon: 'table', pid: 13 }
      }
    ]
  },

  // 店铺管理
  {
    path: '/store',
    component: Layout,
    redirect: '/store/list',
    name: 'Store',
    meta: { title: '店铺管理' },
    children: [
      {
        path: 'list',
        name: 'StoreList',
        component: () => import('@/views/store/Store.vue'),
        meta: { title: '店铺列表', icon: 'table', pid: 14 }
      },
      {
        path: 'prepaid',
        name: 'Balance',
        component: () => import('@/views/store/Balance.vue'),
        meta: { title: '店铺列表', icon: 'table', pid: 15 }
      }
    ]
  },

  { path: '*', redirect: '/error' }
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
