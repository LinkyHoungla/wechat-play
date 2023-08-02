/**
 * 权限管理
 */

import router from '@/router'
import { getToken } from '@/utils/token'
import { Loading } from 'element-ui'

import store from '@/store'

// 路由守卫 前置
router.beforeEach((to, from, next) => {
  console.log(to)

  // 加载进度
  Loading.service()

  // 标题设置
  document.title = to.meta.title

  // 检查目标路由是否存在
  if (to.matched.length === 0) {
    // 跳转到 404 页面
    next('/404')
  }

  // 是否在权限内
  if (to.meta.pid !== 0 && !store.getters.permission.includes(to.meta.pid)) {
    next('/404')
  }

  // 获取登录状态的标识（token）
  const token = getToken()

  if (!token) {
    // 未登录（没有token），跳转到登录页面
    if (to.path !== '/login') {
      next('login')
    } else {
      next()
    }
  } else {
    // 已登录（有token），目标页面是登录页面时，跳转到主页
    if (to.path === '/login') {
      next('/')
    } else {
      next() // 继续导航到目标页面
    }
  }
})

// 后置
router.afterEach(() => {
  // finish progress bar
  Loading.service().close()
})
