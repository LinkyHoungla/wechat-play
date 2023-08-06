import service from '@/utils/request'

// 获取 列表
export function getCompanionList (pageParam) {
  return service({
    url: '/companion/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addCompanion (data) {
  return service({
    url: '/companion',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateCompanion (data) {
  return service({
    url: '/companion',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除
export function deleteCompanion (id) {
  return service({
    url: `/companion/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}

// 获取 列表
export function getServiceList (pageParam) {
  return service({
    url: '/companion/service/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addService (data) {
  return service({
    url: '/companion/service',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateService (data) {
  return service({
    url: '/companion/service',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除
export function deleteService (id) {
  return service({
    url: `/companion/service/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}

// 获取 列表
export function getGameList (pageParam) {
  return service({
    url: '/companion/game/page',
    method: 'get',
    params: pageParam,
    meta: {
      rid: -1
    }
  })
}

// 添加
export function addGame (data) {
  return service({
    url: '/companion/game',
    method: 'post',
    data,
    meta: {
      rid: -1
    }
  })
}

// 修改
export function updateGame (data) {
  return service({
    url: '/companion/game',
    method: 'put',
    data,
    meta: {
      rid: -1
    }
  })
}

// 删除
export function deleteGame (id) {
  return service({
    url: `/companion/game/${id}`,
    method: 'delete',
    meta: {
      rid: -1
    }
  })
}
