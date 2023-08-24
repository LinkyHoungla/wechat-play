import service from '@/utils/request'

// 权限角色
// 获取
export function getRoleList (pageParam) {
  return service({
    url: '/permission/role',
    method: 'get',
    params: pageParam,
    meta: {
      rid: 64
    }
  })
}

// 获取
export function getRoles () {
  return service({
    url: '/permission/roles',
    method: 'get',
    meta: {
      rid: 94
    }
  })
}

// 添加
export function addRole (data) {
  return service({
    url: '/permission/role',
    method: 'post',
    data,
    meta: {
      rid: 55
    }
  })
}

// 修改
export function updateRole (id, data) {
  return service({
    url: `/permission/role/${id}`,
    method: 'put',
    data,
    meta: {
      rid: 56
    }
  })
}

// 删除
export function deleteRole (id) {
  return service({
    url: `/permission/role/${id}`,
    method: 'delete',
    meta: {
      rid: 58
    }
  })
}

// 权限
// 分页查询
export function getPermissionTree (pageParam) {
  return service({
    url: '/permission/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: 60
    }
  })
}

// 添加
export function addPermission (data) {
  return service({
    url: '/permission',
    method: 'post',
    data,
    meta: {
      rid: 62
    }
  })
}

// 修改
export function updatePermission (data) {
  return service({
    url: '/permission',
    method: 'put',
    data,
    meta: {
      rid: 63
    }
  })
}

// 删除
export function deletePermission (id) {
  return service({
    url: `/permission/${id}`,
    method: 'delete',
    meta: {
      rid: 61
    }
  })
}

// 授权树
export function getAuthTree (level, params) {
  return service({
    url: `/permission/auth/${level}`,
    method: 'get',
    params: params,
    meta: {
      rid: 95
    }
  })
}

// 权限列表
export function getAuthList (id) {
  return service({
    url: `/permission/rights/${id}`,
    method: 'get',
    meta: {
      rid: 93
    }
  })
}

// 授权
export function updateAuth (id, data) {
  return service({
    url: `/permission/auth/${id}`,
    method: 'put',
    data,
    meta: {
      rid: 57
    }
  })
}

// 删除授权
export function deleteAuth (rid, pid) {
  return service({
    url: `/permission/auth/${rid}/${pid}`,
    method: 'delete',
    meta: {
      rid: 59
    }
  })
}
