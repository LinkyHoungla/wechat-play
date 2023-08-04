/**
 * 权限管理
 */

import router from '@/router'
import { getToken } from '@/utils/token'
import { Loading } from 'element-ui'

import store from '@/store'

// 路由守卫 前置
router.beforeEach(async (to, from, next) => {
  // console.log(to)

  // 加载进度
  Loading.service()

  // 标题设置
  document.title = to.meta.title

  // 检查目标路由是否存在
  if (!to.matched || to.matched.length === 0) {
    // 如果当前已经是错误页面，则不再跳转
    next('/error')
  }

  const permission = store.getters.permission
  if (permission.length === 0) {
    await store.dispatch('login/getLoginfo')
  }

  // 是否在权限内
  if (to.meta.pid !== 0 && !store.getters.permission.includes(to.meta.pid)) {
    next('/error/403')
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
