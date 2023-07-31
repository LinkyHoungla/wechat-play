import service from '@/utils/request'

// 登录
export function login (data) {
  return service({
    url: '/admin/login',
    method: 'post',
    data
  })
}

// 登出
export function logout () {
  return service({
    url: '/admin/logout',
    method: 'get'
  })
}

// 获取 登录用户信息
export function getInfo () {
  return service({
    url: '/admin/info',
    method: 'get'
  })
}

// 获取 侧边栏菜单
export function getMenuList () {
  return service({
    url: '/menu',
    method: 'get'
  })
}
