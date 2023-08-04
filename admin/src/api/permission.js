import service from '@/utils/request'

// 权限角色
// 获取
export function getRoleList (pageParam) {
  return service({
    url: '/permission/role',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
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
      rid: -1
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
      rid: -1
    }
  })
}

// 删除
export function deleteRole (id) {
  return service({
    url: `/permission/role/${id}`,
    method: 'delete',
    meta: {
      rid: -1
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
      rid: -1
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
      rid: -1
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
      rid: -1
    }
  })
}

// 删除
export function deletePermission (id) {
  return service({
    url: `/permission/${id}`,
    method: 'delete',
    meta: {
      rid: -1
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
      rid: -1
    }
  })
}

// 权限列表
export function getAuthList (id) {
  return service({
    url: `/permission/rights/${id}`,
    method: 'get',
    meta: {
      rid: -1
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
      rid: -1
    }
  })
}

// 删除授权
export function deleteAuth (rid, pid) {
  return service({
    url: `/permission/auth/${rid}/${pid}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}
