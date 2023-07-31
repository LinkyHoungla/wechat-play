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
    method: 'get',
    meta: {
      rid: -1
    }
  })
}

// 获取 登录用户信息
export function getCurrent () {
  return service({
    url: '/admin/current',
    method: 'get',
    meta: {
      rid: -1
    }
  })
}
