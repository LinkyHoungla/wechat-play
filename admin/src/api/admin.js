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

// 获取 登录者信息
export function getCurrent () {
  return service({
    url: '/admin/current',
    method: 'get',
    meta: {
      rid: -1
    }
  })
}

// 获取 管理员列表
export function getAdminList (pageParam) {
  return service({
    url: '/admin/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加 管理员
export function addAdmin (data) {
  return service({
    url: '/admin',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改 管理员信息
export function updateAdmin (id, data) {
  return service({
    url: `/admin/${id}`,
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除 管理员
export function deleteAdmin (id) {
  return service({
    url: `/admin/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}
