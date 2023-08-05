import service from '@/utils/request'

// 登录
export function login (data) {
  return service({
    url: '/user/login',
    method: 'post',
    data
  })
}

// 登出
export function logout () {
  return service({
    url: '/user/logout',
    method: 'get',
    meta: {
      rid: -1
    }
  })
}

// 获取 列表
export function getUserList (pageParam) {
  return service({
    url: '/user/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addUser (data) {
  return service({
    url: '/user',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateUser (data) {
  return service({
    url: '/user',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除
export function deleteUser (id) {
  return service({
    url: `/user/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}
