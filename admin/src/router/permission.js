/**
 * 权限管理
 */

import router from '.'
import { getToken } from '@/utils/token'
import store from '@/store'

// 路由守卫
router.beforeEach((to, from, next) => {
  // 检查目标路由是否存在 或 是否在权限内
  if (to.matched.length === 0 || !store.getters.permission.indexOf(to.meta.rid)) {
    // 跳转到 404 页面
    // next('/404')
    console.log('404')
  }

  // 获取登录状态的标识（例如 token）
  const token = getToken()

  // 如果目标页面是登录页面
  if (to.path === '/login') {
    // 如果已经登录过，则跳转到主页
    if (token) {
      if (from.path !== '/') {
        next('/')
      } else {
        next(false) // 中断导航
      }
    } else {
      next() // 继续导航到登录页面
    }
  } else {
    // 如果未登录，则跳转到登录页面
    if (!token) {
      next('/login')
    } else {
      next() // 继续导航到目标页面
    }
  }
})
